package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.main.LValue.LValueType;
import com.joshuacrotts.minischeme.parser.MSArgumentMismatchException;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;
import com.joshuacrotts.minischeme.parser.MSSemanticException;
import com.joshuacrotts.minischeme.symbol.Environment;
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
     * @param
     */
    public void execute() {
        for (MSSyntaxTree ch : this.interpreterTree.getChildren()) {
            LValue lhs;
            if ((lhs = this.interpretTree(ch)) != null && !lhs.toString().isEmpty()) {
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
     * @return LValue dependent on the MSNodeType. If tree is null, the LValue
     * returned is null. If there is no case for the tree MSNodeType,
     * the returned LValue is a "blank" LValue.
     */
    private LValue interpretTree(final MSSyntaxTree tree) {
        if (tree == null) { return new LValue(LValue.LValueType.NULL); }
        try {
            switch (tree.getNodeType()) {
                case ROOT: return this.interpretTree(tree.getChild(0));
                case SEQ: return this.interpretSequence((MSSequenceNode) tree);
                case LET_DECL: return this.interpretLet((MSLetDeclarationNode) tree);
                case VAR_DECL: return this.interpretVariableDeclaration((MSVariableDeclarationNode) tree);
                case PROC_DECL: return this.interpretProcedureDeclaration((MSProcedureDeclarationNode) tree);
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
                case LIST: return this.interpretList((MSListNode) tree);
                case VECTOR: return this.interpretVector((MSVectorNode) tree);
                case IF: return this.interpretIf((MSIfNode) tree);
                case COND: return this.interpretCond((MSCondNode) tree);
                case DO: return this.interpretDo((MSDoNode) tree);
                case EXPR_LAMBDA_DECL: return new LValue(tree);
                case APPLICATION: return this.interpretApplication((MSApplicationNode) tree);
                case CLOSURE: return this.interpretClosure((MSClosureNode) tree);
                default: throw new MSInterpreterException("Cannot support AST type " + tree.getNodeType());
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
     * <p>
     * (define x 5)
     * (define y x)
     * <p>
     * With this code, x is assigned 5, and we copy that assignment into y. So, there is a MSNumberNode
     * associated with y. This is *not* the same object as x in memory, though.
     *
     * @param varDecl variable declaration AST node.
     * @return blank LValue object.
     */
    private LValue interpretVariableDeclaration(final MSVariableDeclarationNode varDecl) {
        String identifier = varDecl.getIdentifier().getIdentifier();

        if (varDecl.getExpression().isId()) {
            this.symbolTable.addSymbol(identifier, (MSIdentifierNode) varDecl.getExpression());
        } else if (varDecl.getExpression().isApplication()) {
            LValue exprVal = this.interpretTree(varDecl.getExpression());
            this.symbolTable.addSymbol(identifier, SymbolType.VARIABLE, LValue.getAstFromLValue(exprVal));
        } else if (varDecl.getExpression().isExprLambdaDecl()) {
            LValue exprVal = this.interpretTree(varDecl.getExpression());
            this.symbolTable.addSymbol(identifier, SymbolType.LAMBDA, LValue.getAstFromLValue(exprVal));
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
     * <p>
     * (define (square x) (* x x))
     *
     * @param procDecl procedure declaration AST node.
     * @return blank LValue object.
     */
    private LValue interpretProcedureDeclaration(final MSProcedureDeclarationNode procDecl) {
        String identifier = procDecl.getIdentifier().getIdentifier();
        this.symbolTable.addSymbol(identifier, SymbolType.PROCEDURE, procDecl);
        return new LValue();
    }

    /**
     * Interprets a sequence. A sequence is just a collection of expressions to be
     * evaluated. The sequence "returns" the LValue of the last expression evaluated.
     *
     * @param sequenceNode MSSequenceNode AST.
     * @return LValue of last expression evaluated in the sequence.
     */
    private LValue interpretSequence(final MSSequenceNode sequenceNode) {
        LValue lhs = null;
        for (MSSyntaxTree expr : sequenceNode.getChildren()) {
            lhs = this.interpretTree(expr);
        }
        return lhs;
    }

    /**
     * Interprets a "let". Casts and sends the let node type forward
     * to other interpreter methods. This does not include the named
     * let declaration.
     *
     * @param letDecl let declaration AST node.
     * @return LValue of let evaluated.
     */
    private LValue interpretLet(final MSLetDeclarationNode letDecl) throws MSInterpreterException {
        switch (letDecl.getLetType()) {
            case LET: return this.interpretLetDeclaration(letDecl);
            case LET_STAR: return this.interpretLetStarDeclaration(letDecl);
            case LET_REC: return this.interpretLetRecDeclaration(letDecl);
            case LET_NAMED: return this.interpretLetNamedDeclaration(letDecl);
            default:
                throw new MSInterpreterException("Cannot interpret let of type " + letDecl.getLetType());
        }
    }

    /**
     * Interprets a "let" declaration. This is the standard Scheme
     * "let". Declares variables as (let ([var1 expr1] ... [varn exprn]) body).
     * This form of "let" does not store the variables into the environment
     * until all have been evaluated (this differs from let*).
     *
     * @param letDecl let declaration AST node.
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
                this.symbolTable.addSymbol(idNode.getIdentifier(), SymbolType.getSymbolTypeFromNodeType(exprNode.getNodeType()), exprNode);
            }
        }

        LValue letVal = this.interpretTree(letDecl.getBody().copy());
        this.symbolTable.popEnvironment();
        return letVal;
    }

    /**
     * A let* evaluates its variable declaration expressions and stores them in the
     * relevant environment immediately upon seeing them. This means that any variables
     * declared prior to another in the let declarations is visible in that environment.
     *
     * @param letStarDecl let star declaration AST node.
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
                    this.symbolTable.addSymbol(vd.getIdentifier().getIdentifier(),
                            SymbolType.getSymbolTypeFromNodeType(vd.getExpression().getNodeType()), resultExpr);
                }
            }
        }

        // Evaluate the body of the let then pop the environment.
        LValue letVal = this.interpretTree(letStarDecl.getBody().copy());
        this.symbolTable.popEnvironment();
        return letVal;
    }

    /**
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
                this.symbolTable.addSymbol(idNode.getIdentifier(), SymbolType.getSymbolTypeFromNodeType(exprNode.getNodeType()), exprNode);
            }
        }

        LValue letVal = this.interpretTree(letNamedDecl.getBody().copy());
        this.symbolTable.popEnvironment();
        return letVal;
    }

    /**
     * Interprets a "declaration read" from stdin. A declaration read stores the
     * result of reading from stdin into a define.
     *
     * @param declRead MSDeclarationReadNode abstract syntax tree node.
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
     * @return Blank LValue (nothing to return).
     */
    private LValue interpretSetRead(final MSSetReadNode setRead) {
        String id = ((MSIdentifierNode) setRead.getExpression()).getIdentifier();
        this.symbolTable.setSymbol(id, this.interpretReadFn(setRead.getOpType()));
        return new LValue();
    }

    /**
     * Interprets a number literal.
     *
     * @param numberNode - MSNumberNode ast.
     * @return LValue with number node.
     */
    private LValue interpretNumber(final MSNumberNode numberNode) {
        return new LValue(numberNode);
    }

    /**
     * Interprets a boolean literal.
     *
     * @param booleanNode - MSBooleanNode ast.
     * @return LValue with boolean node.
     */
    private LValue interpretBoolean(final MSBooleanNode booleanNode) {
        return new LValue(booleanNode);
    }

    /**
     * Interprets a character.
     *
     * @param characterNode MSCharacterNode abstract syntax tree node.
     * @return LValue with char node.
     */
    private LValue interpretCharacter(final MSCharacterNode characterNode) {
        return new LValue(characterNode);
    }

    /**
     * Interprets a String literal.
     *
     * @param stringNode MSStringNode ast.
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
     * @return LValue from interpreting the symbol literal.
     */
    private LValue interpretSymbolLiteral(final MSSymbolLiteralNode symbolLiteralNode) {
        return new LValue(symbolLiteralNode);
    }

    /**
     * Interprets a Scheme list. A list is just a collection of pairs with
     * the empty list as the cdr. Just like pairs, we evaluate the car and cdr,
     * with the exception that this recursively evaluates each element in the cdr.
     * This also evaluates cons pairs.
     *
     * @param listNode MSListNode ast as a list.
     * @return LValue with list node.
     */
    private LValue interpretList(final MSListNode listNode) {
        // We need to evaluate every element of the "list".
//        MSSyntaxTree car = LValue.getAstFromLValue(this.interpretTree(listNode.getCar()));
//        MSSyntaxTree cdr = LValue.getAstFromLValue(this.interpretTree(listNode.getCdr()));
//        return new LValue(new MSListNode(car, cdr));
        // We need to evaluate every element of the "list".
        listNode.setCar(LValue.getAstFromLValue(this.interpretTree(listNode.getCar())));
        listNode.setCdr(LValue.getAstFromLValue(this.interpretTree(listNode.getCdr())));
        return new LValue(listNode);
    }

    /**
     * Interprets a vector declaration. This doesn't really do much other than return the LValue.
     *
     * @param vectorNode MSVectorNode abstract syntax tree node.
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
     * @return LValue from interpreting the operator.
     */
    private LValue interpretOperator(final MSOpNode opNode) throws MSSemanticException {
        int opType = opNode.getOpType();
        if (opNode.isUnary()) {
            return MiniSchemeOperatorInterpreter.interpretPrimitiveUnaryOperator(
                    opType, this.interpretTree(opNode.getChild(0)));
        } else if (opNode.isBinary()) {
            return MiniSchemeOperatorInterpreter.interpretPrimitiveBinaryOperator(
                    opType, this.interpretTree(opNode.getChild(0)), this.interpretTree(opNode.getChild(1)));
        } else if (opNode.isTernary()) {
            return MiniSchemeOperatorInterpreter.interpretPrimitiveTernaryOperator(
                    opType, this.interpretTree(opNode.getChild(0)), this.interpretTree(opNode.getChild(1)),
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
     * @return LValue when interpreting identifier.
     */
    private LValue interpretIdentifier(final MSIdentifierNode idNode) throws MSSemanticException {
        String id = idNode.getIdentifier();
        if (this.symbolTable.isVariable(id)) {
            return this.interpretTree(this.symbolTable.getVariable(id));
        } else if (this.symbolTable.isProcedure(id)) {
            MSProcedureDeclarationNode procDecl = (MSProcedureDeclarationNode) this.symbolTable.getSymbolEntry(id).getSymbolData();
            return new LValue(LValueType.APPLICATION, procDecl);
        } else if (this.symbolTable.isLambda(id)) {
            MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) this.symbolTable.getSymbolEntry(id).getSymbolData();
            return new LValue(LValueType.APPLICATION, lambdaDecl);
        } else {
            throw new MSSemanticException("undefined identifier '" + id + "'");
        }
    }

    /**
     * Interprets an if expression.
     *
     * @param ifNode MSIfNode ast.
     * @return LValue of if expression body evaluated.
     */
    private LValue interpretIf(final MSIfNode ifNode) throws MSSemanticException {
        LValue ifCond = this.interpretTree(ifNode.getCondition());
        if (ifCond.getType() == LValue.LValueType.BOOL) {
            return ifCond.getBoolValue() ? this.interpretTree(ifNode.getConsequent()) : this.interpretTree(ifNode.getAlternative());
        } else {
            throw new MSArgumentMismatchException("if statement condition", "predicate/procedure", ifCond.getType().toString());
        }
    }

    /**
     * Interprets a COND node. We evaluate each condition until
     * we find one that is true, and then evaluate that expression. No
     * other expressions or predicates in the COND are evaluated afterwards.
     *
     * @param condNode - MSCondNode ast.
     * @return LValue of cond body expression.
     */
    private LValue interpretCond(final MSCondNode condNode) throws MSSemanticException {
        int condIdx = 0;
        int bodyIdx = 1;
        boolean execLastBlock = true;

        while (condIdx < condNode.getChildrenSize() && bodyIdx < condNode.getChildrenSize()) {
            LValue condCond = this.interpretTree(condNode.getChild(condIdx));
            if (condCond.getType() != LValueType.BOOL) {
                throw new MSArgumentMismatchException("cond", "predicate/procedure", condCond.getType().toString());
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
     * @return LValue from do expression once the "test" expression is true.
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
                this.symbolTable.addSymbol(idNode.getIdentifier(), SymbolType.getSymbolTypeFromNodeType(exprNode.getNodeType()), exprNode);
            }
        }

        // Now, execute the test statement and body if the test is true.
        while (true) {
            LValue testLhs = this.interpretTree(doNode.getTestExpression());
            if (!testLhs.isLBool()) {
                throw new MSArgumentMismatchException("do expression", "predicate/boolean", testLhs.getType().toString());
            } else if (testLhs.getBoolValue()) {
                break;
            } else {
                // Interpret body. The LValue returned is superfluous.
                this.interpretTree(doNode.getBody().copy());
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
     *
     */
    private LValue interpretApplication(final MSApplicationNode applicationNode) throws MSSemanticException {
        Callable def = null;
        if (applicationNode.getExpression().isId()) {
            String id = ((MSIdentifierNode) applicationNode.getExpression()).getIdentifier();
            def = (Callable) this.symbolTable.getSymbolEntry(id).getSymbolData();
        } else if (applicationNode.getExpression().isExprLambdaDecl()) {
            def = (Callable) applicationNode.getExpression();
        } else if (applicationNode.getExpression().isApplication()) {
            LValue lval = this.interpretTree(applicationNode.getExpression());
            if (lval.isLLambdaDecl()) {
                def = (Callable) LValue.getAstFromLValue(lval);
            } else {
                return lval;
            }
        } else {
                throw new UnsupportedOperationException(
                    "Cannot retrieve definition for " + applicationNode.getExpression()
                                                                       .getNodeType());
        }

        Environment env = new Environment();
        ArrayList<MSSyntaxTree> args = applicationNode.getArguments();
        for (int i = 0; i < args.size(); i++) {
            MSIdentifierNode currParam = (MSIdentifierNode) def.getParameters().get(i);
            MSSyntaxTree currArg = args.get(i);
            LValue argLVal = this.interpretTree(currArg);
            MSSyntaxTree evalArg = null;
            if (argLVal.isLList()) {
                // If it is null, then evaluate the null list.
                if (argLVal.getTreeValue() == null) {
                    evalArg = new MSListNode();
                } else {
                    evalArg = argLVal.getTreeValue();
                }
            } else {
                evalArg = LValue.getAstFromLValue(argLVal);
            }

            env.addSymbol(currParam.getIdentifier(), SymbolType.getSymbolTypeFromNodeType(evalArg.getNodeType()), evalArg);
        }

        this.symbolTable.addEnvironment(env);
        LValue bodyLVal = this.interpretTree(def.getBody().copy());
        if (!bodyLVal.isLLambdaDecl())
            this.symbolTable.popEnvironment();
        return bodyLVal;
    }

    /**
     *
     * @param closureNode
     * @return
     */
    private LValue interpretClosure(final MSClosureNode closureNode) {
        this.symbolTable.addEnvironment(closureNode.getEnvironment());
        if (closureNode.getEnvironment().getSymbolTable().isEmpty()) {
            // Iterate over the variable declarations and evaluate their expressions.
            // If we find a variable in the let decl that's not global, it's an error.
            for (MSSyntaxTree t : closureNode.getLetDeclarations()) {
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
                        this.symbolTable.addSymbol(vd.getIdentifier().getIdentifier(),
                                SymbolType.getSymbolTypeFromNodeType(vd.getExpression().getNodeType()), resultExpr);
                    }
                }
            }
        }

        MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) (closureNode.getLambdaDeclaration());
        LValue lval = this.interpretTree(lambdaDecl.getBody().copy());
        this.symbolTable.popEnvironment();
        return lval;
    }

    /**
     * Interprets a set operation. A set operation occurs when we want to set a variable
     * to a value, set the car of a list, set the cdr of a list, or set a vector element.
     * This is subject to change.
     *
     * @param setNode MSSetNode abstract syntax tree node.
     * @return Blank LValue.
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
                throw new MSInterpreterException("Cannot set operator of type " + setNode.getNodeType());
        }

        return new LValue();
    }

    /**
     * Interprets a set-car! procedure call. This sets the head of a list to some value.
     *
     * @param setNode MSSetNode ast.
     * @throws MSArgumentMismatchException if the set expression is not an lvalue or does not
     *                                     reduce to an lvalue. If we pass more than 2 arguments
     *                                     to the function. If we pass a non pair/list as the lvalue.
     */
    private void interpretSetCarFn(final MSSetNode setNode) throws MSSemanticException {
        // If the set expression is not an lvalue, that's an error.
        if (setNode.getExpression().isTerminalType()) {
            throw new MSArgumentMismatchException("set-car!", "lvalue", setNode.getExpression().getNodeType().toString());
        }
        // Check the number of arguments. There should be only one.
        else if (setNode.getData().size() > 1) {
            throw new MSArgumentMismatchException("set-car!", 2, setNode.getData().size() + 2);
        }

        // Check to see if we need to evaluate the id or data.
        MSSyntaxTree expr = setNode.getExpression();
        MSSyntaxTree data = setNode.getData().get(0);
        if (!expr.isTerminalType()) {
            expr = LValue.getAstFromLValue(this.interpretTree(expr));
        }
        if (!data.isTerminalType()) {
            data = LValue.getAstFromLValue(this.interpretTree(data));
        }

        if (expr == null || !expr.isList()) {
            throw new MSArgumentMismatchException("set-car!", "pair/list", (expr == null ? "null" : expr.getNodeType().toString()));
        }

        ((MSListNode) expr).setCar(data);
    }

    /**
     * Interprets a set-cdr! procedure call. This sets the tail of a list to some value.
     *
     * @param setNode MSSetNode ast.
     * @throws MSArgumentMismatchException if the set expression is not an lvalue or does not
     *                                     reduce to an lvalue. If we pass more than 2 arguments
     *                                     to the function. If we pass a non pair/list as the lvalue.
     */
    private void interpretSetCdrFn(final MSSetNode setNode) throws MSSemanticException {
        // If the set expression is not an lvalue, that's an error.
        if (setNode.getExpression().isTerminalType()) {
            throw new MSArgumentMismatchException("set-cdr!", "lvalue", setNode.getExpression().getNodeType().toString());
        }
        // Check the number of arguments. There should be only one.
        else if (setNode.getData().size() > 1) {
            throw new MSArgumentMismatchException("set-cdr!", 2, setNode.getData().size() + 2);
        }

        // Check to see if we need to evaluate the id or data.
        MSSyntaxTree expr = setNode.getExpression();
        MSSyntaxTree data = setNode.getData().get(0);
        if (!expr.isTerminalType()) {
            expr = LValue.getAstFromLValue(this.interpretTree(expr));
        }
        if (!data.isTerminalType()) {
            data = LValue.getAstFromLValue(this.interpretTree(data));
        }

        // Check to make sure that we're setting the cdr of a list.
        if (expr == null || !expr.isList()) {
            throw new MSArgumentMismatchException("set-cdr!", "pair/list", (expr == null ? "null" : expr.getNodeType().toString()));
        }

        ((MSListNode) expr).setCdr(data);
    }

    /**
     * Interprets a set variable function. This, as the name implies, set a variable
     * to some value. This variable must be previously declared or an error
     * will be thrown.
     *
     * @param setNode MSSetNode AST.
     * @throws MSSemanticException if set! does not have two arguments.
     */
    private void interpretSetVariableFn(final MSSetNode setNode) throws MSSemanticException {
        String id = ((MSIdentifierNode) setNode.getExpression()).getIdentifier();
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
     * @param setNode
     * @throws MSSemanticException
     */
    private void interpretSetVectorFn(final MSSetNode setNode) throws MSSemanticException {
        String id = ((MSIdentifierNode) setNode.getExpression()).getIdentifier();
        MSVectorNode vector = (MSVectorNode) this.symbolTable.getVariable(id);
        ArrayList<MSSyntaxTree> setData = setNode.getData();
        MSSyntaxTree idxNode = LValue.getAstFromLValue(this.interpretTree(setData.get(0)));

        // Check to make sure the node is a number.
        if (idxNode == null || !idxNode.isNumber()) {
            throw new MSSemanticException("attempt to access vector with non-index " + setData.get(0).getStringRep());
        }

        // Pull the index and element out.
        MSNumberNode numIdxNode = (MSNumberNode) setData.get(0);
        if (!numIdxNode.isInteger()) {
            throw new MSSemanticException("index " + numIdxNode.getStringRep() + " is not an integer");
        }

        // Check to make sure the index is in the bounds.
        int idx = Integer.parseInt(numIdxNode.getStringRep());
        if (idx >= vector.size() || idx < 0) {
            throw new MSSemanticException("index " + idx + " out of bounds of vector " + id);
        }

        // Evaluate the expression if it's non-terminal and set in table.
        MSSyntaxTree expr = LValue.getAstFromLValue(this.interpretTree(setData.get(1)));
        vector.setChild(idx, expr);
        this.symbolTable.setSymbol(id, vector);
    }

    /**
     * @param opType
     * @return
     */
    private MSSyntaxTree interpretReadFn(final int opType) {
        Scanner in = new Scanner(System.in);
        switch (opType) {
            case MiniSchemeParser.READNUMBER_FN:
                return new MSNumberNode(in.nextDouble());
            case MiniSchemeParser.READLINE_FN:
                return new MSStringNode(in.nextLine());
            default:
                throw new MSInterpreterException("Could not read from stdin");
        }
    }
}