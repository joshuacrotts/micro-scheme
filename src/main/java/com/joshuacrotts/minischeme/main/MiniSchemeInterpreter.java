package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.main.LValue.LValueType;
import com.joshuacrotts.minischeme.parser.MSSemanticError;
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

    public MiniSchemeInterpreter(MSSyntaxTree tree) {
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
    private void replaceParams(Callable procDef, MSSyntaxTree body,
                               ArrayList<MSSyntaxTree> args) {
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
    private void replaceParamsHelper(Callable definition, MSSyntaxTree body,
                                     MSSyntaxTree arg, int replaceIdx, ArrayList<MSSyntaxTree> args) {
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
                switch (lhs.getType()) {
                    case NUM:
                    case BOOL:
                    case PAIR:
                    case STR:
                    case VECTOR:
                    case SYM:
                    case PROCCALL:
                    case LAMBDACALL:
                        System.out.println(lhs);
                        break;
                    default: // Do nothing for now...
                }
            }
        }
    }

    public void setInterpreterTree(MSSyntaxTree tree) {
        this.interpreterTree = tree;
    }

    /**
     * Interprets a single tree node. This should be recursively defined.
     *
     * @param tree - tree of some node type.
     *
     * @return LValue dependent on the MSNodeType. If tree is null, the LValue
     *         returned is null. If there is no case for the tree MSNodeType,
     *         the returned LValue is a "blank" LValue.
     */
    private LValue interpretTree(MSSyntaxTree tree) {
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
                case STR: return this.interpretString((MSStringNode) tree);
                case PAIR: return this.interpretPair((MSPairNode) tree);
                case LIST: return this.interpretList((MSPairNode) tree);
                case VECTOR: return this.interpretVector((MSVectorNode) tree);
                case IF: return this.interpretIf((MSIfNode) tree);
                case COND: return this.interpretCond((MSCondNode) tree);
                case CALL: return this.interpretCall((MSCallNode) tree);
                case EXPR_LAMBDA_DECL_CALL: return this.interpretLambdaDeclCall((MSLambdaDeclarationCallNode) tree);
                default: break;
            }
        } catch (MSSemanticError err) {
            System.out.println(err.getMessage());
        }

        return new LValue();
    }

    /**
     *
     * @param varDecl
     * @return
     */
    private LValue interpretVariableDeclaration(MSVariableDeclarationNode varDecl) {
        String identifier = varDecl.getIdentifier().getIdentifier();
        this.symbolTable.addSymbol(identifier, SymbolType.VARIABLE, varDecl);
        return new LValue();
    }

    /**
     *
     * @param procDecl
     * @return
     */
    private LValue interpretProcedureDeclaration(MSProcedureDeclarationNode procDecl) {
        String identifier = procDecl.getIdentifier().getIdentifier();
        this.symbolTable.addSymbol(identifier, SymbolType.PROCEDURE, procDecl);
        return new LValue();
    }

    /**
     *
     * @param lambdaDecl
     * @return
     */
    private LValue interpretLambdaDeclaration(MSLambdaDeclarationNode lambdaDecl) {
        String identifier = lambdaDecl.getIdentifier().getIdentifier();
        this.symbolTable.addSymbol(identifier, SymbolType.LAMBDA, lambdaDecl);
        return new LValue();
    }

    /**
     * Interprets a "let". Casts and sends the let node type forward
     * to other interpreter methods.
     *
     * @param letDecl - MSLetDeclarationNode ast.
     *
     * @return LValue of let evaluated.
     */
    private LValue interpretLet(MSLetDeclarationNode letDecl) {
        switch (letDecl.getLetType()) {
            case MiniSchemeParser.LET: return this.interpretLetDeclaration(letDecl);
            case MiniSchemeParser.LETSTAR: return this.interpretLetStarDeclaration(letDecl);
            case MiniSchemeParser.LETREC: return this.interpretLetRecDeclaration(letDecl);
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
     * @param letDecl - MSLetDeclaration node.
     *
     * @return LValue of body of let.
     */
    private LValue interpretLetDeclaration(MSLetDeclarationNode letDecl) {
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
            this.symbolTable.addSymbol(idNode.getIdentifier(),
                    SymbolType.getSymbolTypeFromNodeType(exprNode.getNodeType()),
                    exprNode);
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
     * @param letStarDecl - MSLetDeclarationNode.
     *
     * @return LValue of let body evaluation.
     */
    private LValue interpretLetStarDeclaration(MSLetDeclarationNode letStarDecl) {
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

            this.symbolTable.addSymbol(vd.getIdentifier().getIdentifier(),
                    SymbolType.getSymbolTypeFromNodeType(vd.getExpression().getNodeType()), resultExpr);
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
    private LValue interpretLetRecDeclaration(MSLetDeclarationNode letRecDecl) {
        throw new UnsupportedOperationException("Cannot support letrec yet!");
    }

    /**
     *
     * @param declRead
     * @return
     */
    private LValue interpretDeclarationRead(MSDeclarationReadNode declRead) {
        String id = ((MSIdentifierNode) declRead.getIdentifier()).getIdentifier();
        this.symbolTable.setSymbol(id, this.interpretReadFn(declRead.getOpType()));
        return new LValue();
    }

    /**
     *
     * @param setRead
     */
    private LValue interpretSetRead(MSSetReadNode setRead) {
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
    private LValue interpretNumber(MSNumberNode numberNode) {
        return new LValue(numberNode);
    }

    /**
     * Interprets a boolean literal.
     *
     * @param booleanNode - MSBooleanNode ast.
     *
     * @return LValue with boolean node.
     */
    private LValue interpretBoolean(MSBooleanNode booleanNode) {
        return new LValue(booleanNode);
    }

    /**
     * Interprets a String literal.
     *
     * @param stringNode - MSStringNode ast.
     *
     * @return LValue with string node.
     */
    private LValue interpretString(MSStringNode stringNode) {
        return new LValue(stringNode);
    }

    /**
     *
     * @param symbolNode
     * @return
     */
    private LValue interpretSymbol(MSSymbolNode symbolNode) {
        return new LValue(symbolNode.getExpression());
    }

    /**
     *
     * @param symbolLiteralNode
     * @return
     */
    private LValue interpretSymbolLiteral(MSSymbolLiteralNode symbolLiteralNode) {
        return new LValue(symbolLiteralNode);
    }

    /**
     * Interprets a cons pair. The car and cdr are evaluated before
     * constructing the pair.
     *
     * @param pairNode - MSPairNode ast.
     *
     * @return LValue with pair node.
     */
    private LValue interpretPair(MSPairNode pairNode) {
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
     * @param rootPair - MSPairNode ast as a list.
     *
     * @return LValue with list node.
     */
    private LValue interpretList(MSPairNode rootPair) {
        // We need to evaluate every element of the "list".
        MSSyntaxTree carNode = LValue.getAstFromLValue(this.interpretTree(rootPair.getCar()));
        MSSyntaxTree cdrNode = LValue.getAstFromLValue(this.interpretTree(rootPair.getCdr()));
        return new LValue(new MSPairNode(MSNodeType.LIST, carNode, cdrNode));
    }

    /**
     *
     * @param vectorNode
     * @return
     */
    private LValue interpretVector(MSVectorNode vectorNode) {
        return new LValue(vectorNode);
    }

    /**
     * @param opNode
     * @return
     */
    private LValue interpretOperator(MSOpNode opNode) throws MSSemanticError {
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
     * @param idNode - MSIdentifierNode ast.
     *
     * @return LValue when interpreting identifier.
     */
    private LValue interpretIdentifier(MSIdentifierNode idNode) throws MSSemanticError {
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
            throw new MSSemanticError("undefined identifier '" + id + "'");
        }
    }

    /**
     * Interprets an if statement.
     *
     * @param ifNode - MSIfNode ast.
     *
     * @return LValue of if statement body evaluated.
     */
    private LValue interpretIf(MSIfNode ifNode) throws MSSemanticError {
        LValue ifCond = this.interpretTree(ifNode.getCondition());
        if (ifCond.getType() == LValue.LValueType.BOOL) {
            return ifCond.getBoolValue()
                    ? this.interpretTree(ifNode.getConsequent())
                    : this.interpretTree(ifNode.getAlternative());
        } else {
            throw new MSSemanticError("cannot evaluate if statement condition;"
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
    private LValue interpretCond(MSCondNode condNode) throws MSSemanticError {
        int condIdx = 0;
        int bodyIdx = 1;
        boolean execLastBlock = true;

        while (condIdx < condNode.getChildrenSize() && bodyIdx < condNode.getChildrenSize()) {
            LValue condCond = this.interpretTree(condNode.getChild(condIdx));
            if (condCond.getType() != LValueType.BOOL) {
                throw new MSSemanticError("cannot evaluate cond statement condition;"
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
     *
     * @param callNode
     * @return
     */
    private LValue interpretCall(MSCallNode callNode) throws MSSemanticError {
        // First, check to see if child 0 is an expr lambda decl. If so, do a lambda decl call.
        if (callNode.getChild(0).isExprLambdaDecl()) {
            MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) callNode.getChild(0);
            return this.interpretTree(new MSLambdaDeclarationCallNode(lambdaDecl.getLambdaParameters(),
                    lambdaDecl.getBody(), callNode.getProcedureArguments()));
        } else {
            // Otherwise, determine if it's a stored procedure or lambda.
            String id = callNode.getIdentifier().getIdentifier();

            if (this.symbolTable.isProcedure(id)) {
                return this.interpretProcedureCall(callNode);
            } else if (this.symbolTable.isLambda(id)) {
                return this.interpretLambdaCall(callNode);
            } else {
                throw new MSSemanticError("undefined identifier '" + id + "'");
            }
        }
    }

    /**
     * @param procCall
     * @return
     */
    private LValue interpretProcedureCall(MSCallNode procCall) throws MSSemanticError {
        // Poll the procedure from the symbol table.
        String id = procCall.getIdentifier().getIdentifier();
        MSProcedureDeclarationNode procDef = (MSProcedureDeclarationNode) this.symbolTable.getSymbolEntry(id).getSymbolData();

        // Before anything, check to make sure the procedure parameters and argument sizes match.
        if (procCall.getProcedureArgumentCount() != procDef.getParameterCount()) {
            throw new MSSemanticError(id + ": procedure arity mismatch; expected "
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
                else if (lhs.isLString()) { args.add(new MSStringNode(lhs.getStringValue())); }
                else if (lhs.isLProcCall() || lhs.isLSymbol()) { args.add(lhs.getTreeValue()); }
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
     *
     * @param lambdaCall
     * @return
     */
    private LValue interpretLambdaCall(MSCallNode lambdaCall) throws MSSemanticError {
        String id = lambdaCall.getIdentifier().getStringRep();
        MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) this.symbolTable.getSymbolEntry(id).getSymbolData();
        MSLambdaDeclarationCallNode lambdaDeclCall = new MSLambdaDeclarationCallNode(
                lambdaDecl.getLambdaParameters(), lambdaDecl.getBody(), lambdaCall.getProcedureArguments());

        // Check to see if we have enough arguments for the lambda.
        if (lambdaDecl.getLambdaParameterCount() != lambdaCall.getProcedureArgumentCount()) {
            throw new MSSemanticError("lambda arity mismatch; expected "
                    + lambdaDeclCall.getLambdaParameterCount()
                    + " arguments but got " +
                    + lambdaDeclCall.getLambdaArgumentCount());
        }
        return this.interpretTree(lambdaDeclCall);
    }

    /**
     *
     * @param lambdaDeclCall
     * @return
     */
    private LValue interpretLambdaDeclCall(MSLambdaDeclarationCallNode lambdaDeclCall) throws MSSemanticError {
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
                else if (lhs.isLString()) { args.add(new MSStringNode(lhs.getStringValue())); }
                else if (lhs.isLProcCall() || lhs.isLSymbol()) { args.add(lhs.getTreeValue()); }
                else if (lhs.isLPair() || lhs.isLVector()) {
                    // If it is null, then evaluate the null list.
                    if (lhs.getTreeValue() == null) {
                        args.add(new MSPairNode());
                    } else {
                        args.add(lhs.getTreeValue());
                    }
                } else {
                    throw new IllegalStateException("Interpreter error - lambda decl call " +
                            "found an incorrect lvalue. This should never happen...");
                }
            }
        }

        MSSyntaxTree body = lambdaDeclCall.getBody().copy();
        this.replaceParams(lambdaDeclCall, body, args);
        return this.interpretTree(body);
    }

    /**
     *
     * @param setNode
     * @return
     */
    private LValue interpretSetOp(MSSetNode setNode) {
        switch (setNode.getOpType()) {
            case MiniSchemeParser.SETCAR_FN:
                this.interpretSetCarFn(setNode);
                break;
            case MiniSchemeParser.SETCDR_FN:
                this.interpretSetCdrFn(setNode);
                break;
            case MiniSchemeParser.SETVAR_FN:
                this.interpretSetVariableFn(setNode);
                break;
            default:
                throw new IllegalArgumentException("Internal interpreter error "
                        + "- cannot set with operator of type " + setNode.getNodeType()
                        + ". This should never happen...");
        }

        return new LValue();
    }

    /**
     *
     * @param setNode
     */
    private void interpretSetCarFn(MSSetNode setNode) {
        String id = ((MSIdentifierNode) setNode.getIdentifier()).getIdentifier();
        MSPairNode pair = (MSPairNode) this.symbolTable.getVariable(id);
        pair.setCar(setNode.getExpression());
        this.symbolTable.setSymbol(id, pair);
    }

    /**
     *
     * @param setNode
     */
    private void interpretSetCdrFn(MSSetNode setNode) {
        String id = ((MSIdentifierNode) setNode.getIdentifier()).getIdentifier();
        MSPairNode pair = (MSPairNode) this.symbolTable.getVariable(id);
        pair.setCdr(setNode.getExpression());
        this.symbolTable.setSymbol(id, pair);
    }

    /**
     *
     * @param setNode
     */
    private void interpretSetVariableFn(MSSetNode setNode) {
        String id = ((MSIdentifierNode) setNode.getIdentifier()).getIdentifier();
        MSSyntaxTree tree = setNode.getExpression();
        this.symbolTable.setSymbol(id, tree);
    }

    /**
     *
     * @param opType
     * @return
     */
    private MSSyntaxTree interpretReadFn(int opType) {
        Scanner in = new Scanner(System.in);
        switch (opType) {
            case MiniSchemeParser.READNUMBER_FN: return new MSNumberNode(in.nextDouble());
            case MiniSchemeParser.READLINE_FN: return new MSStringNode(in.nextLine());
            default:
                throw new IllegalArgumentException("Internal interpreter error with reading input " +
                        "- this should never happen...");
        }
    }
}