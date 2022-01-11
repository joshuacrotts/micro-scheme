package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.main.LValue.LValueType;
import com.joshuacrotts.minischeme.parser.MSArgumentMismatchException;
import com.joshuacrotts.minischeme.parser.MSSemanticException;
import com.joshuacrotts.minischeme.symbol.SymbolTable;
import com.joshuacrotts.minischeme.symbol.SymbolType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 */
public class MiniSchemeInterpreter {

    /**
     *
     */
    private final SymbolTable symbolTable;

    /**
     *
     */
    private MSSyntaxTree interpreterTree;

    public MiniSchemeInterpreter(final MSSyntaxTree tree) {
        this.interpreterTree = tree;
        this.symbolTable = new SymbolTable();
        this.symbolTable.addEnvironment();
    }

    public MiniSchemeInterpreter() {
        this(null);
    }

    /**
     * @param procDef
     * @param body
     * @param args
     */
    private void replaceParams(final Callable procDef, final MSSyntaxTree body,
                               final ArrayList<MSSyntaxTree> args) {
        for (int i = 0; i < args.size(); i++) {
            this.replaceParamsHelper(procDef, body, args.get(i), i, args);
        }
    }

    /**
     * @param definition
     * @param body
     * @param arg
     * @param replaceIdx
     */
    private void replaceParamsHelper(final Callable definition, final MSSyntaxTree body,
                                     final MSSyntaxTree arg, final int replaceIdx,
                                     final ArrayList<MSSyntaxTree> args) {
        // If the body is null then there's nothing to replace.
        if (body == null) { return; }
        for (int i = 0; i < body.getChildrenSize(); i++) {
            MSSyntaxTree child = body.getChild(i);
            if (child == null) { return; }
            // If it's an ID then we want to replace it.
            if (child.isId()) {
                MSIdentifierNode id = (MSIdentifierNode) child;
                if (definition.getArgumentIndex(id.getIdentifier()) == replaceIdx) {
                    body.setChild(i, arg);
                }

                // If we have a lambda we need to find the correct arg.
                if (body.getChild(i).isExprLambdaDecl()) {
                    this.replaceParams(definition, body.getChild(i), args);
                }
            } else {
                this.replaceParamsHelper(definition, child, arg, replaceIdx, args);
            }
        }
    }

    /**
     * @param
     */
    public void execute() {
        for (MSSyntaxTree ch : this.interpreterTree.getChildren()) {
            LValue lhs;
            if ((lhs = this.interpretTree(ch)) != null) {
                System.out.println(lhs);
            }
        }
    }

    public void setInterpreterTree(final MSSyntaxTree tree) {
        this.interpreterTree = tree;
    }

    /**
     * Interprets a single tree node. This should be recursively defined.
     *
     * @param tree of some node type.
     *
     * @return LValue dependent on the MSNodeType. If tree is null, the LValue
     *         returned is null. If there is no case for the tree MSNodeType,
     *         the returned LValue is a "blank" LValue.
     */
    private LValue interpretTree(final MSSyntaxTree tree) {
        if (tree == null) { return new LValue(LValue.LValueType.NULL); }
        try {
            switch (tree.getNodeType()) {
                case ROOT: return this.interpretTree(tree.getChild(0));
                case LET_DECL: return this.interpretLet((MSLetDeclarationNode) tree);
                case VAR_DECL: return this.interpretVariableDeclaration((MSVariableDeclarationNode) tree);
                case PROC_DECL: return this.interpretProcedureDeclaration((MSProcedureDeclarationNode) tree);
                case LAMBDA_DECL: return this.interpretLambdaDeclaration((MSLambdaDeclarationNode) tree);
                case DECL_READ: return this.interpretDeclarationRead((MSDeclarationReadNode) tree);
                case SET_READ: return this.interpretSetRead((MSSetReadNode) tree);
                case ID: return this.interpretIdentifier((MSIdentifierNode) tree);
                case OP: return this.interpretOperator((MSOpNode) tree);
                case SYMBOL: return this.interpretSymbol((MSSymbolNode) tree);
                case SYMBOL_LIT: return this.interpretSymbolLiteral((MSSymbolLiteralNode) tree);
                case SET: return this.interpretSetOp((MSSetNode) tree);
                case NUM: return this.interpretNumber((MSNumberNode) tree);
                case BOOL: return this.interpretBoolean((MSBooleanNode) tree);
                case CHAR: return this.interpretCharacter((MSCharacterNode) tree);
                case STR: return this.interpretString((MSStringNode) tree);
                case PAIR: return this.interpretPair((MSPairNode) tree);
                case LIST: return this.interpretList((MSPairNode) tree);
                case VECTOR: return this.interpretVector((MSVectorNode) tree);
                case IF: return this.interpretIf((MSIfNode) tree);
                case COND: return this.interpretCond((MSCondNode) tree);
                case DO: return this.interpretDo((MSDoNode) tree);
                case CALL: return this.interpretCall((MSCallNode) tree);
                case EXPR_LAMBDA_DECL_CALL: return this.interpretLambdaDeclCall((MSLambdaDeclarationCallNode) tree);
                default: break;
            }
        } catch (MSSemanticException err) {
            System.out.println(err.getMessage());
        }

        return new LValue();
    }

    /**
     * Interprets a variable declaration. A variable declaration is stored as a k/v pair
     * in the symbol table, with the key being the string literal identifier, and the value
     * being a VariableDeclaration. The exception to this rule is when we want to assign a
     * variable to another term. For example,
     *
     * (define x 5)
     * (define y x)
     *
     * With this code, x is assigned 5, and we copy that assignment into y. So, there is a MSNumberNode
     * associated with y. This is *not* the same object as x in memory, though.
     *
     * @param varDecl variable declaration AST node.
     *
     * @return blank LValue object.
     */
    private LValue interpretVariableDeclaration(final MSVariableDeclarationNode varDecl) {
        String identifier = varDecl.getIdentifier().getIdentifier();
        // If we run into an identifier, we can just copy that identifier's expression over in the symbol table.
        if (varDecl.getExpression().isId()) {
            this.symbolTable.addSymbol(identifier, (MSIdentifierNode) varDecl.getExpression());
        } else {
            this.symbolTable.addSymbol(identifier, SymbolType.VARIABLE, varDecl);
        }

        return new LValue();
    }

    /**
     * Interprets a procedure declaration. A procedure declaration is
     * preceded by an identifier in a (define ...) call. The procedure
     * declaration encompasses the define and identifier. Following the
     * identifier is 0 or more parameters.
     *
     * (define (square x) (* x x))
     *
     * @param procDecl procedure declaration AST node.
     *
     * @return blank LValue object.
     */
    private LValue interpretProcedureDeclaration(final MSProcedureDeclarationNode procDecl) {
        String identifier = procDecl.getIdentifier().getIdentifier();
        this.symbolTable.addSymbol(identifier, SymbolType.PROCEDURE, procDecl);
        return new LValue();
    }

    /**
     * Interprets a lambda declaration. A lambda declaration, in this case,
     * is preceded by an identifier in a (define ...) or (let... ) call. These
     * specific lambda declarations cannot be anonymous.
     *
     * @param lambdaDecl lambda declaration AST node.
     *
     * @return blank LValue object.
     */
    private LValue interpretLambdaDeclaration(final MSLambdaDeclarationNode lambdaDecl) {
        String identifier = lambdaDecl.getIdentifier().getIdentifier();
        this.symbolTable.addSymbol(identifier, SymbolType.LAMBDA, lambdaDecl);
        return new LValue();
    }

    /**
     * Interprets a "let". Casts and sends the let node type forward
     * to other interpreter methods. This does not include the named
     * let declaration.
     *
     * @param letDecl let declaration AST node.
     *
     * @return LValue of let evaluated.
     */
    private LValue interpretLet(final MSLetDeclarationNode letDecl) {
        switch (letDecl.getLetType()) {
            case LET: return this.interpretLetDeclaration(letDecl);
            case LET_STAR: return this.interpretLetStarDeclaration(letDecl);
            case LET_REC: return this.interpretLetRecDeclaration(letDecl);
            case LET_NAMED: return this.interpretLetNamedDeclaration(letDecl);
            default:
                throw new IllegalArgumentException("Internal interpreter error " +
                        "- cannot interpret let of type " + letDecl.getLetType() + ".");
        }
    }

    /**
     * Interprets a "let" declaration. This is the standard Scheme
     * "let". Declares variables as (let ([var1 expr1] ... [varn exprn]) body).
     * This form of "let" does not store the variables into the environment
     * until all have been evaluated (this differs from let*).
     *
     * @param letDecl let declaration AST node.
     *
     * @return LValue of body of let.
     */
    private LValue interpretLetDeclaration(final MSLetDeclarationNode letDecl) {
        ArrayList<MSSyntaxTree> decls = letDecl.getDeclarations();
        Map<MSIdentifierNode, MSSyntaxTree> results = new HashMap<>();

        // Iterate over the declarations and evaluate their expressions.
        // If we find a variable in the let decl that's not global, it's an error.
        for (MSSyntaxTree t : decls) {
            MSVariableDeclarationNode vd = (MSVariableDeclarationNode) t;
            switch (vd.getExpression().getNodeType()) {
                case EXPR_LAMBDA_DECL:
                case PROC_DECL:
                case LET_DECL:
                    results.put(vd.getIdentifier(), vd.getExpression());
                    break;
                default:
                    results.put(vd.getIdentifier(), LValue.getAstFromLValue(this.interpretTree(vd.getExpression())));
            }
        }

        // Now push a new environment.
        this.symbolTable.addEnvironment();

        // Add all K/V results to the current table/environment.
        for (Map.Entry<MSIdentifierNode, MSSyntaxTree> entry : results.entrySet()) {
            MSIdentifierNode idNode = entry.getKey();
            MSSyntaxTree exprNode = entry.getValue();
            // If the expr is an identifier, we need to copy its value over.
            if (exprNode.isId()) {
                this.symbolTable.addSymbol(idNode.getIdentifier(), (MSIdentifierNode) exprNode);
            } else {
                // If we encounter a lambda declaration, we need to make it non-anonymous.
                if (exprNode.isExprLambdaDecl()) {
                    exprNode = MSLambdaDeclarationNode.createNonAnonymous(idNode, (MSLambdaDeclarationNode) exprNode);
                }
                this.symbolTable.addSymbol(idNode.getIdentifier(), SymbolType.getSymbolTypeFromNodeType(exprNode.getNodeType()), exprNode);
            }
        }

        LValue letVal = this.interpretTree(letDecl.getBody());
        this.symbolTable.popEnvironment();
        return letVal;
    }

    /**
     * A let* evaluates its variable declaration expressions and stores them in the
     * relevant environment immediately upon seeing them. This means that any variables
     * declared prior to another in the let declarations is visible in that environment.
     *
     * @param letStarDecl let star declaration AST node.
     *
     * @return LValue of let body evaluation.
     */
    private LValue interpretLetStarDeclaration(final MSLetDeclarationNode letStarDecl) {
        ArrayList<MSSyntaxTree> varDecls = letStarDecl.getDeclarations();

        // Add a new environment before anything else.
        this.symbolTable.addEnvironment();

        // Iterate over the variable declarations and evaluate their expressions.
        // If we find a variable in the let decl that's not global, it's an error.
        for (MSSyntaxTree t : varDecls) {
            MSVariableDeclarationNode vd = (MSVariableDeclarationNode) t;
            MSSyntaxTree resultExpr = null;
            switch (vd.getExpression().getNodeType()) {
                case EXPR_LAMBDA_DECL:
                case PROC_DECL:
                case LET_DECL:
                    resultExpr = vd.getExpression();
                    break;
                default:
                    resultExpr = LValue.getAstFromLValue(this.interpretTree(vd.getExpression()));
            }

            // Only copy over values if the resultExpr is non-null.
            if (resultExpr != null) {
                // If the expr is an identifier, we need to copy its value over.
                if (resultExpr.isId()) {
                    this.symbolTable.addSymbol(vd.getIdentifier().getIdentifier(), (MSIdentifierNode) vd.getExpression());
                } else {
                    // If we encounter a lambda declaration, we need to make it non-anonymous.
                    if (resultExpr.isExprLambdaDecl()) {
                        resultExpr = MSLambdaDeclarationNode.createNonAnonymous(vd.getIdentifier(), (MSLambdaDeclarationNode) resultExpr);
                    }
                    this.symbolTable.addSymbol(vd.getIdentifier().getIdentifier(),
                            SymbolType.getSymbolTypeFromNodeType(vd.getExpression().getNodeType()), resultExpr);
                }
            }
        }

        // Evaluate the body of the let then pop the environment.
        LValue letVal = this.interpretTree(letStarDecl.getBody());
        this.symbolTable.popEnvironment();
        return letVal;
    }

    /**
     *
     * @param letRecDecl
     * @return
     */
    private LValue interpretLetRecDeclaration(final MSLetDeclarationNode letRecDecl) {
        throw new UnsupportedOperationException("Cannot support letrec yet!");
    }

    /**
     * Interprets a named "let" declaration. This is the standard Scheme
     * "let". Declares variables as (let [id] ([var1 expr1] ... [varn exprn]) body).
     * This form of "let" does not store the variables into the environment
     * until all have been evaluated (this differs from let*).
     *
     * @param letNamedDecl let named declaration AST node.
     *
     * @return LValue of body of let.
     */
    private LValue interpretLetNamedDeclaration(final MSLetDeclarationNode letNamedDecl) {
        ArrayList<MSSyntaxTree> decls = letNamedDecl.getDeclarations();
        Map<MSIdentifierNode, MSSyntaxTree> results = new HashMap<>();

        // Iterate over the declarations and evaluate their expressions.
        // If we find a variable in the let decl that's not global, it's an error.
        for (MSSyntaxTree t : decls) {
            MSVariableDeclarationNode vd = (MSVariableDeclarationNode) t;
            switch (vd.getExpression().getNodeType()) {
                case EXPR_LAMBDA_DECL:
                case PROC_DECL:
                case LET_DECL:
                    results.put(vd.getIdentifier(), vd.getExpression());
                    break;
                default:
                    results.put(vd.getIdentifier(), LValue.getAstFromLValue(this.interpretTree(vd.getExpression())));
            }
        }

        // Now push a new environment.
        this.symbolTable.addEnvironment();

        // Push the "procedure" defined by this let.
        this.symbolTable.addSymbol(letNamedDecl.getIdentifier().getIdentifier(), SymbolType.PROCEDURE,
                letNamedDecl.createProcedureDeclaration());

        // Add all K/V results to the current table/environment.
        for (Map.Entry<MSIdentifierNode, MSSyntaxTree> entry : results.entrySet()) {
            MSIdentifierNode idNode = entry.getKey();
            MSSyntaxTree exprNode = entry.getValue();
            // If the expr is an identifier, we need to copy its value over.
            if (exprNode.isId()) {
                this.symbolTable.addSymbol(idNode.getIdentifier(), (MSIdentifierNode) exprNode);
            } else {
                // If we encounter a lambda declaration, we need to make it non-anonymous.
                if (exprNode.isExprLambdaDecl()) {
                    exprNode = MSLambdaDeclarationNode.createNonAnonymous(idNode, (MSLambdaDeclarationNode) exprNode);
                }
                this.symbolTable.addSymbol(idNode.getIdentifier(), SymbolType.getSymbolTypeFromNodeType(exprNode.getNodeType()), exprNode);
            }
        }

        LValue letVal = this.interpretTree(letNamedDecl.getBody());
        this.symbolTable.popEnvironment();
        return letVal;
    }

    /**
     * Interprets a "declaration read" from stdin. A declaration read stores the
     * result of reading from stdin into a define.
     *
     * @param declRead MSDeclarationReadNode abstract syntax tree node.
     *
     * @return Blank LValue (nothing to return).
     */
    private LValue interpretDeclarationRead(final MSDeclarationReadNode declRead) {
        String id = ((MSIdentifierNode) declRead.getIdentifier()).getIdentifier();
        this.symbolTable.setSymbol(id, this.interpretReadFn(declRead.getOpType()));
        return new LValue();
    }

    /**
     * Interprets a "set read" from stdin. A set read stores the result of
     * reading from stdin into a variable.
     *
     * @param setRead MSSetReadNode abstract syntax tree node.
     *
     * @return Blank LValue (nothing to return).
     */
    private LValue interpretSetRead(final MSSetReadNode setRead) {
        String id = ((MSIdentifierNode) setRead.getIdentifier()).getIdentifier();
        this.symbolTable.setSymbol(id, this.interpretReadFn(setRead.getOpType()));
        return new LValue();
    }

    /**
     * Interprets a number literal.
     *
     * @param numberNode - MSNumberNode ast.
     *
     * @return LValue with number node.
     */
    private LValue interpretNumber(final MSNumberNode numberNode) {
        return new LValue(numberNode);
    }

    /**
     * Interprets a boolean literal.
     *
     * @param booleanNode - MSBooleanNode ast.
     *
     * @return LValue with boolean node.
     */
    private LValue interpretBoolean(final MSBooleanNode booleanNode) {
        return new LValue(booleanNode);
    }

    /**
     * Interprets a character.
     *
     * @param characterNode MSCharacterNode abstract syntax tree node.
     *
     * @return LValue with char node.
     */
    private LValue interpretCharacter(final MSCharacterNode characterNode) {
        return new LValue(characterNode);
    }

    /**
     * Interprets a String literal.
     *
     * @param stringNode MSStringNode ast.
     *
     * @return LValue with string node.
     */
    private LValue interpretString(final MSStringNode stringNode) {
        return new LValue(stringNode);
    }

    /**
     * Interprets a symbol. A symbol is really just an encapsulated
     * expression that should not be evaluated.
     *
     * @param symbolNode AST symbol node.
     *
     * @return LValue with symbol's expression.
     */
    private LValue interpretSymbol(final MSSymbolNode symbolNode) {
        return new LValue(symbolNode.getExpression());
    }

    /**
     * Interprets a symbol literal. A symbol occurs when we quote a list, then
     * use a non-quoted symbol inside the list (e.g., '(x + z)). x, +, and z are
     * all symbol literals and must be treated differently from their default
     * types (x and z being identifiers, and + being an operator).
     *
     * @param symbolLiteralNode MSSymbolLiteralNode abstract syntax tree node.
     *
     * @return LValue from interpreting the symbol literal.
     */
    private LValue interpretSymbolLiteral(final MSSymbolLiteralNode symbolLiteralNode) {
        return new LValue(symbolLiteralNode);
    }

    /**
     * Interprets a cons pair. The car and cdr are evaluated before
     * constructing the pair.
     *
     * @param pairNode MSPairNode ast.
     *
     * @return LValue with pair node.
     */
    private LValue interpretPair(final MSPairNode pairNode) {
        // Evaluate the CAR and CDR.
        MSSyntaxTree carNode = LValue.getAstFromLValue(this.interpretTree(pairNode.getCar()));
        MSSyntaxTree cdrNode = LValue.getAstFromLValue(this.interpretTree(pairNode.getCdr()));
        return new LValue(new MSPairNode(MSNodeType.PAIR, carNode, cdrNode));
    }

    /**
     * Interprets a Scheme list. A list is just a collection of pairs with
     * the empty list as the cdr. Just like pairs, we evaluate the car and cdr,
     * with the exception that this recursively evaluates each element in the cdr.
     *
     * @param rootPair MSPairNode ast as a list.
     *
     * @return LValue with list node.
     */
    private LValue interpretList(final MSPairNode rootPair) {
        // We need to evaluate every element of the "list".
        MSSyntaxTree carNode = LValue.getAstFromLValue(this.interpretTree(rootPair.getCar()));
        MSSyntaxTree cdrNode = LValue.getAstFromLValue(this.interpretTree(rootPair.getCdr()));
        return new LValue(new MSPairNode(MSNodeType.LIST, carNode, cdrNode));
    }

    /**
     * Interprets a vector.
     *
     * @param vectorNode MSVectorNode abstract syntax tree node.
     *
     * @return LValue from interpreting the vector.
     */
    private LValue interpretVector(final MSVectorNode vectorNode) {
        return new LValue(vectorNode);
    }

    /**
     * Interprets a "primitive operator". A primitive operation is one that is defined
     * in the MiniScheme grammar. These have arities from 1 to n, and are controlled
     * by how many children the AST node has. The definitions of each available operator
     * are in MiniSchemeOperatorInterpreter.java.
     *
     * @param opNode MSOpNode abstract syntax tree node.
     *
     * @return LValue from interpreting the operator.
     */
    private LValue interpretOperator(final MSOpNode opNode) throws MSSemanticException {
        int opType = opNode.getOpType();
        if (opNode.isUnary()) {
            return MiniSchemeOperatorInterpreter.interpretPrimitiveUnaryOperator(
                    opType, this.interpretTree(opNode.getChild(0)));
        } else if (opNode.isBinary()) {
            return MiniSchemeOperatorInterpreter.interpretPrimitiveBinaryOperator(
                    opType, this.interpretTree(opNode.getChild(0)),
                    this.interpretTree(opNode.getChild(1)));
        } else if (opNode.isTernary()) {
            return MiniSchemeOperatorInterpreter.interpretPrimitiveTernaryOperator(
                    opType, this.interpretTree(opNode.getChild(0)),
                    this.interpretTree(opNode.getChild(1)),
                    this.interpretTree(opNode.getChild(2)));
        } else {
            LValue res = this.interpretTree(opNode.getChild(0));
            for (int i = 1; i < opNode.getChildrenSize(); i++) {
                res = MiniSchemeOperatorInterpreter.interpretPrimitiveNaryOperator(
                        opType, res, this.interpretTree(opNode.getChild(i)));
            }
            return res;
        }
    }

    /**
     * Interprets an identifier by consulting the symbol table. If a variable
     * is bound in scope, its value is returned. Identifiers are variables,
     * procedures, or lambdas.
     *
     * @param idNode MSIdentifierNode ast.
     *
     * @return LValue when interpreting identifier.
     */
    private LValue interpretIdentifier(final MSIdentifierNode idNode) throws MSSemanticException {
        String id = idNode.getIdentifier();
        if (this.symbolTable.isVariable(id)) {
            return this.interpretTree(this.symbolTable.getVariable(id));
        } else if (this.symbolTable.isProcedure(id)) {
            MSProcedureDeclarationNode procDecl = (MSProcedureDeclarationNode) this.symbolTable.getSymbolEntry(id).getSymbolData();
            return new LValue(LValueType.PROCCALL, procDecl.getIdentifier());
        } else if (this.symbolTable.isLambda(id)) {
            MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) this.symbolTable.getSymbolEntry(id).getSymbolData();
            return new LValue(LValueType.LAMBDACALL, lambdaDecl.getIdentifier());
        } else {
            throw new MSSemanticException("undefined identifier '" + id + "'");
        }
    }

    /**
     * Interprets an if expression.
     *
     * @param ifNode MSIfNode ast.
     *
     * @return LValue of if expression body evaluated.
     */
    private LValue interpretIf(final MSIfNode ifNode) throws MSSemanticException {
        LValue ifCond = this.interpretTree(ifNode.getCondition());
        if (ifCond.getType() == LValue.LValueType.BOOL) {
            return ifCond.getBoolValue()
                    ? this.interpretTree(ifNode.getConsequent())
                    : this.interpretTree(ifNode.getAlternative());
        } else {
            throw new MSSemanticException("cannot evaluate if statement condition;"
                    + " expected predicate or procedure");
        }
    }

    /**
     * Interprets a COND node. We evaluate each condition until
     * we find one that is true, and then evaluate that expression. No
     * other expressions or predicates in the COND are evaluated afterwards.
     *
     * @param condNode - MSCondNode ast.
     *
     * @return LValue of cond body expression.
     */
    private LValue interpretCond(final MSCondNode condNode) throws MSSemanticException {
        int condIdx = 0;
        int bodyIdx = 1;
        boolean execLastBlock = true;

        while (condIdx < condNode.getChildrenSize() && bodyIdx < condNode.getChildrenSize()) {
            LValue condCond = this.interpretTree(condNode.getChild(condIdx));
            if (condCond.getType() != LValueType.BOOL) {
                throw new MSSemanticException("cannot evaluate cond statement condition;"
                        + " expected predicate or true/false");
            } else {
                if (condCond.getBoolValue()) {
                    // If the condition is true, evaluate that expression.
                    execLastBlock = false;
                    break;
                } else {
                    // Otherwise, iterate to the next condition and expr.
                    condIdx += 2;
                    bodyIdx += 2;
                }
            }
        }

        bodyIdx = execLastBlock ? bodyIdx - 1 : bodyIdx;
        return this.interpretTree(condNode.getChild(bodyIdx));
    }

    /**
     * Interprets a do loop.
     *
     * @param doNode MSDoNode abstract syntax tree node.
     *
     * @return LValue from do expression once the "test" expression is true.
     *
     * @throws MSSemanticException if the test expression is not a predicate/true/false value.
     */
    private LValue interpretDo(final MSDoNode doNode) throws MSSemanticException {
        // First set up the variable declarations.
        ArrayList<MSSyntaxTree> decls = doNode.getVariableDeclarations();
        Map<MSIdentifierNode, MSSyntaxTree> results = new HashMap<>();

        // Iterate over the declarations and evaluate their expressions.
        // If we find a variable in the let decl that's not global, it's an error.
        for (MSSyntaxTree t : decls) {
            MSVariableDeclarationNode vd = (MSVariableDeclarationNode) t;
            switch (vd.getExpression().getNodeType()) {
                case EXPR_LAMBDA_DECL:
                case PROC_DECL:
                case LET_DECL:
                    results.put(vd.getIdentifier(), vd.getExpression());
                    break;
                default:
                    results.put(vd.getIdentifier(), LValue.getAstFromLValue(this.interpretTree(vd.getExpression())));
            }
        }

        this.symbolTable.addEnvironment();
        // Add all K/V results to the current table/environment.
        for (Map.Entry<MSIdentifierNode, MSSyntaxTree> entry : results.entrySet()) {
            MSIdentifierNode idNode = entry.getKey();
            MSSyntaxTree exprNode = entry.getValue();
            // If the expr is an identifier, we need to copy its value over.
            if (exprNode.isId()) {
                this.symbolTable.addSymbol(idNode.getIdentifier(), (MSIdentifierNode) exprNode);
            } else {
                // If we encounter a lambda declaration, we need to make it non-anonymous.
                if (exprNode.isExprLambdaDecl()) {
                    exprNode = MSLambdaDeclarationNode.createNonAnonymous(idNode, (MSLambdaDeclarationNode) exprNode);
                }
                this.symbolTable.addSymbol(idNode.getIdentifier(), SymbolType.getSymbolTypeFromNodeType(exprNode.getNodeType()), exprNode);
            }
        }

        // Now execute the test statement and body if the test is true.
        while (true) {
            LValue testLhs = this.interpretTree(doNode.getTestExpression());
            if (!testLhs.isLBool()) {
                throw new MSArgumentMismatchException("do expression", "predicate/boolean", testLhs.getType().toString());
            } else if (testLhs.getBoolValue()) {
                break;
            } else {
                // Interpret body. The LValue returned is superfluous.
                this.interpretTree(doNode.getBody());

                // After the body run the step declarations.
                for (MSSyntaxTree stepDecl : doNode.getStepDeclarations()) {
                    this.interpretTree(stepDecl);
                }
            }
        }

        // Finally, interpret the "true" expressions and return the final.
        LValue trueLhs = new LValue();
        for (MSSyntaxTree trueExpr : doNode.getTrueExpressions()) {
            trueLhs = this.interpretTree(trueExpr);
        }

        this.symbolTable.popEnvironment();
        return trueLhs;
    }

    /**
     * Interprets a generic "call" node. A call node is either a procedure call or a
     * lambda call. If the first child is an expression lambda declaration, we can convert
     * this into a lambda declaration call and evaluate it then by passing the parameters
     * forward.
     *
     * @param callNode MSCallNode abstract syntax tree.
     *
     * @return LValue from evaluating the body of the call.
     *
     * @throws MSSemanticException if the identifier found is unknown.
     */
    private LValue interpretCall(final MSCallNode callNode) throws MSSemanticException {
        // First, check to see if child 0 is an expr lambda decl. If so, do a lambda decl call.
        if (callNode.getChild(0).isExprLambdaDecl()) {
            MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) callNode.getChild(0);
            return this.interpretTree(new MSLambdaDeclarationCallNode(lambdaDecl.getLambdaParameters(),
                    lambdaDecl.getBody(), callNode.getProcedureArguments()));
        } else {
            // Otherwise, determine if it's a stored procedure or lambda.
            String id = callNode.getIdentifier().getIdentifier();

            if (this.symbolTable.isVariable(id)) {
                return this.interpretProcedureCall(callNode);
            } else if (this.symbolTable.isProcedure(id)) {
                return this.interpretProcedureCall(callNode);
            } else if (this.symbolTable.isLambda(id)) {
                return this.interpretLambdaCall(callNode);
            } else {
                throw new MSSemanticException("undefined identifier '" + id + "'");
            }
        }
    }

    /**
     * Interprets a procedure call. A procedure call has a name, procedure arguments, and
     * lambda arguments. These all need to be passed forward and evaluated.
     *
     * @param procCall MSCallNode abstract syntax tree node.
     *
     * @return LValue when interpreting the procedure.
     *
     * @throws MSSemanticException if the procedure call arity does not match the definition.
     */
    private LValue interpretProcedureCall(final MSCallNode procCall) throws MSSemanticException {
        // Poll the procedure from the symbol table.
        String id = procCall.getIdentifier().getIdentifier();
        MSProcedureDeclarationNode procDef = (MSProcedureDeclarationNode) this.symbolTable.getSymbolEntry(id).getSymbolData();

        // Before anything, check to make sure the procedure parameters and argument sizes match.
        if (procCall.getProcedureArgumentCount() != procDef.getParameterCount()) {
            throw new MSSemanticException(id + ": procedure arity mismatch; expected "
                    + procDef.getParameterCount() + " arguments but got "
                    + procCall.getProcedureArgumentCount());
        }

        // Now, bind the arguments to parameters.
        ArrayList<MSSyntaxTree> args = new ArrayList<>();
        for (int i = 0; i < procCall.getProcedureArguments().size(); i++) {
            // If it's a lambda declaration, we can't evaluate it - we pass it forward.
            MSSyntaxTree procCallArg = procCall.getProcedureArguments().get(i);
            if (procCallArg.isExprLambdaDecl()) {
                args.add(procCallArg);
            } else {
                // Otherwise, evaluate the arg.
                LValue lhs = this.interpretTree(procCallArg);
                if (lhs.isLNumber()) { args.add(new MSNumberNode(lhs.getDoubleValue())); }
                else if (lhs.isLBool()) { args.add(new MSBooleanNode(lhs.getBoolValue())); }
                else if (lhs.isLChar()) { args.add(new MSCharacterNode(lhs.getCharValue())); }
                else if (lhs.isLString()) { args.add(new MSStringNode(lhs.getStringValue())); }
                else if (lhs.isLProcCall() || lhs.isLSymbol() || lhs.isLLambdaCall()) { args.add(lhs.getTreeValue()); }
                else if (lhs.isLPair() || lhs.isLVector()) {
                    // If it is null, then evaluate the null list.
                    if (lhs.getTreeValue() == null) {
                        args.add(new MSPairNode());
                    } else {
                        args.add(lhs.getTreeValue());
                    }
                } else {
                    throw new IllegalArgumentException("Internal interpreter error -" +
                            " proc decl call found an incorrect lvalue: " + lhs.getType());
                }
            }
        }

        // Replace the parameters with the arguments.
        MSSyntaxTree body = procDef.getBody().copy();
        replaceParams(procDef, body, args);

        // If the body is a lambda declaration, we need to call it with arguments.
        if (body.isExprLambdaDecl()) {
            body = new MSLambdaDeclarationCallNode((MSLambdaDeclarationNode) body, procCall);
        }

        return this.interpretTree(body);
    }

    /**
     * Interprets a lambda call. A lambda call is a definition assigned as a lambda e.g.,
     * (define x (lambda ...)).
     *
     * @param lambdaCall MSCallNode abstract syntax tree node.
     *
     * @return LValue when evaluating the body of the lambda.
     *
     * @throws MSSemanticException if the arity of the lambda call does not match its declaration.
     */
    private LValue interpretLambdaCall(final MSCallNode lambdaCall) throws MSSemanticException {
        String id = lambdaCall.getIdentifier().getStringRep();
        MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) this.symbolTable.getSymbolEntry(id).getSymbolData();
        MSLambdaDeclarationCallNode lambdaDeclCall = new MSLambdaDeclarationCallNode(
                lambdaDecl.getLambdaParameters(), lambdaDecl.getBody(), lambdaCall.getProcedureArguments());

        // Check to see if we have enough arguments for the lambda.
        if (lambdaDecl.getLambdaParameterCount() != lambdaCall.getProcedureArgumentCount()) {
            throw new MSSemanticException("lambda arity mismatch; expected "
                    + lambdaDeclCall.getLambdaParameterCount()
                    + " arguments but got " +
                    + lambdaDeclCall.getLambdaArgumentCount());
        }
        return this.interpretTree(lambdaDeclCall);
    }

    /**
     * Interprets a lambda declaration call. A lambda declaration call exists when a lambda
     * is declared and evaluated in the same line e.g., ((lambda (x) (+ x x)) 5).
     *
     * @param lambdaDeclCall MSLambdaDeclarationCallNode abstract syntax tree node.
     *
     * @return LValue when evaluating the body of the lambda.
     *
     * @throws MSSemanticException if one of the other methods throws an exception.
     */
    private LValue interpretLambdaDeclCall(final MSLambdaDeclarationCallNode lambdaDeclCall) throws MSSemanticException {
        ArrayList<MSSyntaxTree> args = new ArrayList<>();
        // Now, bind the arguments for the lambda to its parameters.
        for (int i = 0; i < lambdaDeclCall.getLambdaArguments().size(); i++) {
            // If it's a lambda declaration, we can't evaluate it - we pass it forward.
            MSSyntaxTree lambdaDeclCallArg = lambdaDeclCall.getLambdaArguments().get(i);
            if (lambdaDeclCallArg.isExprLambdaDecl()) {
                args.add(lambdaDeclCallArg);
            } else {
                // Otherwise, evaluate the arg.
                LValue lhs = this.interpretTree(lambdaDeclCall.getLambdaArguments().get(i));
                if (lhs.isLNumber()) { args.add(new MSNumberNode(lhs.getDoubleValue())); }
                else if (lhs.isLBool()) { args.add(new MSBooleanNode(lhs.getBoolValue())); }
                else if (lhs.isLChar()) { args.add(new MSCharacterNode(lhs.getCharValue())); }
                else if (lhs.isLString()) { args.add(new MSStringNode(lhs.getStringValue())); }
                else if (lhs.isLProcCall() || lhs.isLSymbol() || lhs.isLLambdaCall()) { args.add(lhs.getTreeValue()); }
                else if (lhs.isLPair() || lhs.isLVector()) {
                    // If it is null, then evaluate the null list.
                    if (lhs.getTreeValue() == null) {
                        args.add(new MSPairNode());
                    } else {
                        args.add(lhs.getTreeValue());
                    }
                } else {
                    throw new IllegalStateException("Internal interpreter error -" +
                            " lambda decl call found an incorrect lvalue: " + lhs.getType() + "");
                }
            }
        }

        MSSyntaxTree body = lambdaDeclCall.getBody().copy();
        this.replaceParams(lambdaDeclCall, body, args);
        return this.interpretTree(body);
    }

    /**
     * Interprets a set operation. A set operation occurs when we want to set a variable
     * to a value, set the car of a list, set the cdr of a list, or set a vector element.
     * This is subject to change.
     *
     * @param setNode MSSetNode abstract syntax tree node.
     *
     * @return Blank LValue.
     *
     * @throws MSSemanticException if any of the other called methods throw one.
     */
    private LValue interpretSetOp(final MSSetNode setNode) throws MSSemanticException {
        switch (setNode.getOpType()) {
            case MiniSchemeParser.SETVAR_FN:
                this.interpretSetVariableFn(setNode);
                break;
            case MiniSchemeParser.SETCAR_FN:
                this.interpretSetCarFn(setNode);
                break;
            case MiniSchemeParser.SETCDR_FN:
                this.interpretSetCdrFn(setNode);
                break;
            case MiniSchemeParser.SETVEC_FN:
                this.interpretSetVectorFn(setNode);
                break;
            default:
                throw new IllegalArgumentException("Internal interpreter error "
                        + "- cannot set with operator of type " + setNode.getNodeType() + ".");
        }

        return new LValue();
    }

    /**
     *
     * @param setNode
     */
    private void interpretSetCarFn(final MSSetNode setNode) throws MSSemanticException {
        String id = ((MSIdentifierNode) setNode.getIdentifier()).getIdentifier();
        MSPairNode pair = (MSPairNode) this.symbolTable.getVariable(id);
        ArrayList<MSSyntaxTree> setData = setNode.getData();
        if (setData.size() > 1) {
            throw new MSSemanticException("set! expected 2 arguments but got " + setData.size() + 2);
        }
        pair.setCar(setData.get(0));
        this.symbolTable.setSymbol(id, pair);
    }

    /**
     *
     * @param setNode
     */
    private void interpretSetCdrFn(final MSSetNode setNode) throws MSSemanticException {
        String id = ((MSIdentifierNode) setNode.getIdentifier()).getIdentifier();
        MSPairNode pair = (MSPairNode) this.symbolTable.getVariable(id);
        ArrayList<MSSyntaxTree> setData = setNode.getData();
        if (setData.size() > 1) {
            throw new MSSemanticException("set! expected 2 arguments but got " + setData.size() + 2);
        }
        pair.setCdr(setData.get(0));
        this.symbolTable.setSymbol(id, pair);
    }

    /**
     *
     * @param setNode
     *
     * @return void.
     *
     * @throws MSSemanticException
     */
    private void interpretSetVariableFn(final MSSetNode setNode) throws MSSemanticException {
        String id = ((MSIdentifierNode) setNode.getIdentifier()).getIdentifier();
        ArrayList<MSSyntaxTree> setData = setNode.getData();
        if (setData.size() > 1) {
            throw new MSSemanticException("set! expected 2 arguments but got " + setData.size() + 2);
        }

        // First check to see if we need to evaluate the setData.
        MSSyntaxTree setDataVal = setData.get(0);
        if (!setDataVal.isTerminalType()) {
            setDataVal = LValue.getAstFromLValue(this.interpretTree(setDataVal));
        }
        this.symbolTable.setSymbol(id, setDataVal);
    }

    /**
     *
     * @param setNode
     *
     * @return void.
     *
     * @throws MSSemanticException
     */
    private void interpretSetVectorFn(final MSSetNode setNode) throws MSSemanticException {
        String id = ((MSIdentifierNode) setNode.getIdentifier()).getIdentifier();
        MSVectorNode vector = (MSVectorNode) this.symbolTable.getVariable(id);
        ArrayList<MSSyntaxTree> setData = setNode.getData();
        MSSyntaxTree idxNode = LValue.getAstFromLValue(this.interpretTree(setData.get(0)));

        // Check to make sure the node is a number.
        if (idxNode == null || !idxNode.isNumber()) {
            throw new MSSemanticException("attempt to access vector with non-index " + setData.get(0).getStringRep());
        }

        // Pull the index and element out. Check the idx for bounds.
        MSNumberNode numIdxNode = (MSNumberNode) setData.get(0);
        if (!numIdxNode.isInteger()) {
            throw new MSSemanticException("index " + numIdxNode.getStringRep() + " is not an integer");
        }

        int idx = Integer.parseInt(numIdxNode.getStringRep());
        if (idx >= vector.size() || idx < 0) {
            throw new MSSemanticException("index " + idx + " out of bounds of vector " + id);
        }

        MSSyntaxTree expr = LValue.getAstFromLValue(this.interpretTree(setData.get(1)));
        vector.setChild(idx, expr);
        this.symbolTable.setSymbol(id, vector);
    }

    /**
     *
     *
     * @param opType
     *
     * @return
     */
    private MSSyntaxTree interpretReadFn(final int opType) {
        Scanner in = new Scanner(System.in);
        switch (opType) {
            case MiniSchemeParser.READNUMBER_FN: return new MSNumberNode(in.nextDouble());
            case MiniSchemeParser.READLINE_FN: return new MSStringNode(in.nextLine());
            default:
                throw new IllegalArgumentException("Internal interpreter error - could not read from stdin");
        }
    }
}