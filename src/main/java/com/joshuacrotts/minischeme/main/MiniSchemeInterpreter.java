package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.main.LValue.LValueType;
import com.joshuacrotts.minischeme.parser.MSSemanticError;
import com.joshuacrotts.minischeme.symbol.SymbolTable;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class MiniSchemeInterpreter {

    /**
     *
     */
    private final MSSyntaxTree tree;

    /**
     *
     */
    private final SymbolTable symbolTable;

    public MiniSchemeInterpreter(MSSyntaxTree tree) {
        this.tree = tree;
        this.symbolTable = new SymbolTable();
    }

    /**
     * @param procDef
     * @param body
     * @param args
     */
    private static void replaceParams(Callable procDef, MSSyntaxTree body,
                                      ArrayList<MSSyntaxTree> args) {
        for (int i = 0; i < args.size(); i++) {
            replaceParamsHelper(procDef, body, args.get(i), i);
        }
    }

    /**
     * @param definition
     * @param body
     * @param arg
     * @param replaceIdx
     */
    private static void replaceParamsHelper(Callable definition, MSSyntaxTree body,
                                            MSSyntaxTree arg, int replaceIdx) {
        // If the body is null then there's nothing to replace.
        if (body == null) { return; }
        for (int i = 0; i < body.getChildrenSize(); i++) {
            MSSyntaxTree child = body.getChild(i);
            if (child == null) { return; }
            // If it's an ID then we want to replace it.
            if (child.getNodeType() == MSNodeType.ID) {
                MSIdentifierNode id = (MSIdentifierNode) child;
                if (definition.getArgumentIndex(id.getIdentifier()) == replaceIdx) {
                    body.setChild(i, arg);
                }
            }
            else {
                replaceParamsHelper(definition, child, arg, replaceIdx);
            }
        }
    }

    /**
     * @param
     */
    public void execute() {
        for (MSSyntaxTree ch : this.tree.getChildren()) {
            LValue lhs = this.interpretTree(ch);
            switch (lhs.getType()) {
                case NUM:
                case BOOL:
                case PAIR:
                case STR:
                    System.out.println(lhs);
                    break;
                default: // Do nothing for now...
            }
        }
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
        switch (tree.getNodeType()) {
            case ROOT: return this.interpretTree(tree.getChild(0));
            case VAR_DECL: return this.interpretVariableDeclaration(tree);
            case PROC_DECL: return this.interpretProcedureDeclaration(tree);
            case LAMBDA_DECL: return this.interpretLambdaDeclaration(tree);
            case DECL_READ: return this.interpretDeclarationRead(tree);
            case SET_READ: return this.interpretSetRead(tree);
            case ID: return this.interpretIdentifier(tree);
            case OP: return this.interpretOperator(tree);
            case SET: return this.interpretSetOp(tree);
            case NUM: return this.interpretNumber(tree);
            case BOOL: return this.interpretBoolean(tree);
            case STR: return this.interpretString(tree);
            case PAIR: return this.interpretPair(tree);
            case LIST: return this.interpretList(tree);
            case IF: return this.interpretIf(tree);
            case COND: return this.interpretCond(tree);
            case CALL: return this.interpretCall(tree);
            case EXPR_LAMBDA_DECL_CALL: return this.interpretLambdaDeclCall(tree);
        }

        return new LValue();
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretVariableDeclaration(MSSyntaxTree tree) {
        MSVariableDeclarationNode varDecl = (MSVariableDeclarationNode) tree;
        String identifier = varDecl.getIdentifier().getIdentifier();
        this.symbolTable.addVariable(identifier, varDecl);
        return new LValue();
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretProcedureDeclaration(MSSyntaxTree tree) {
        MSProcedureDeclarationNode procDecl = (MSProcedureDeclarationNode) tree;
        String identifier = procDecl.getIdentifier().getIdentifier();
        this.symbolTable.addProcedure(identifier, procDecl);
        return new LValue();
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretLambdaDeclaration(MSSyntaxTree tree) {
        MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) tree;
        String identifier = lambdaDecl.getIdentifier().getIdentifier();
        this.symbolTable.addLambda(identifier, lambdaDecl);
        return new LValue();
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretDeclarationRead(MSSyntaxTree tree) {
        MSDeclarationReadNode declRead = (MSDeclarationReadNode) tree;
        String id = ((MSIdentifierNode) declRead.getIdentifier()).getIdentifier();
        this.symbolTable.setVariable(id, this.interpretReadFn(declRead.getOpType()));
        return new LValue();
    }

    /**
     *
     * @param tree
     */
    private LValue interpretSetRead(MSSyntaxTree tree) {
        MSSetReadNode setRead = (MSSetReadNode) tree;
        String id = ((MSIdentifierNode) setRead.getIdentifier()).getIdentifier();
        this.symbolTable.setVariable(id, this.interpretReadFn(setRead.getOpType()));
        return new LValue();
    }

    /**
     * @param tree
     */
    private LValue interpretNumber(MSSyntaxTree tree) {
        return new LValue(((MSNumberNode) tree));
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretBoolean(MSSyntaxTree tree) {
        return new LValue(((MSBooleanNode) tree));
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretString(MSSyntaxTree tree) {
        return new LValue(tree);
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretPair(MSSyntaxTree tree) {
        MSPairNode pairNode = (MSPairNode) tree;
        // Evaluate the CAR and CDR.
        MSSyntaxTree carNode = LValue.getAstFromLValue(this.interpretTree(pairNode.getCar()));
        MSSyntaxTree cdrNode = LValue.getAstFromLValue(this.interpretTree(pairNode.getCdr()));
        return new LValue(new MSPairNode(MSNodeType.PAIR, carNode, cdrNode));
    }

    /**
     * @param tree
     * @return
     * @throws MSSemanticError
     */
    private LValue interpretList(MSSyntaxTree tree) {
        MSPairNode rootPair = (MSPairNode) tree;
        // We need to evaluate every element of the "list".
        MSSyntaxTree carNode = LValue.getAstFromLValue(this.interpretTree(rootPair.getCar()));
        MSSyntaxTree cdrNode = LValue.getAstFromLValue(this.interpretTree(rootPair.getCdr()));
        return new LValue(new MSPairNode(MSNodeType.LIST, carNode, cdrNode));
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretOperator(MSSyntaxTree tree) {
        int opType = ((MSOpNode) tree).getOpType();
        LValue res = null;
        // Determine if it's a unary operator or nary.
        if (tree.getChildrenSize() == 1) {
            res = this.interpretPrimitiveUnaryOp(this.interpretTree(tree.getChild(0)), opType);
        } else {
            res = this.interpretTree(tree.getChild(0));
            for (int i = 1; i < tree.getChildrenSize(); i++) {
                res = this.interpretPrimitiveBinaryOp(res, opType, this.interpretTree(tree.getChild(i)));
            }
        }
        return res;
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretIdentifier(MSSyntaxTree tree) {
        String id = tree.getStringRep();
        if (this.symbolTable.isVariable(id)) {
            return this.interpretTree(this.symbolTable.getVariable(id).getExpression());
        } else if (this.symbolTable.isProcedure(id)) {
            MSProcedureDeclarationNode procDef = (MSProcedureDeclarationNode) this.symbolTable.getProcedure(id).getProcDef();
            return new LValue(LValueType.PROCCALL, procDef.getIdentifier());
        } else if (this.symbolTable.isLambda(id)) {
            MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) this.symbolTable.getLambda(id).getLambdaDef();
            return new LValue(LValueType.LAMBDACALL, lambdaDecl.getIdentifier());
        } else {
            throw new IllegalArgumentException("ERR cannot identify " + id + " as a variable, procedure, or lambda.");
        }
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretIf(MSSyntaxTree tree) {
        LValue ifCond = this.interpretTree(tree.getChild(0));
        if (ifCond.getType() == LValue.LValueType.BOOL) {
            return ifCond.getBoolValue()
                    ? this.interpretTree(tree.getChild(1))
                    : this.interpretTree(tree.getChild(2));
        }
        return null;
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretCond(MSSyntaxTree tree) {
        int condIdx = 0;
        int bodyIdx = 1;
        boolean execLastBlock = true;

        while (condIdx < tree.getChildrenSize() && bodyIdx < tree.getChildrenSize()) {
            LValue condCond = this.interpretTree(tree.getChild(condIdx));
            // If the condition is true, evaluate that expression.
            if (condCond.getBoolValue()) {
                execLastBlock = false;
                break;
            } else {
                condIdx += 2;
                bodyIdx += 2;
            }
        }

        bodyIdx = execLastBlock ? bodyIdx - 1 : bodyIdx;
        return this.interpretTree(tree.getChild(bodyIdx));
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretCall(MSSyntaxTree tree) {
        MSCallNode callNode = (MSCallNode) tree;
        String id = callNode.getIdentifier().getIdentifier();
        if (this.symbolTable.isProcedure(id)) {
            return this.interpretProcedureCall(tree);
        } else if (this.symbolTable.isLambda(id)) {
            return this.interpretLambdaCall(tree);
        } else {
            throw new IllegalArgumentException("ERR cannot identify " + id + " as procedure or named lambda.");
        }
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretProcedureCall(MSSyntaxTree tree) {
        // Poll the procedure from the symbol table.
        MSCallNode procCall = (MSCallNode) tree;
        String id = procCall.getIdentifier().getIdentifier();
        MSProcedureDeclarationNode procDef = (MSProcedureDeclarationNode) this.symbolTable.getProcedure(id).getProcDef();
        ArrayList<MSSyntaxTree> args = new ArrayList<>();

        for (int i = 0; i < procCall.getProcedureArguments().size(); i++) {
            // If it's a lambda declaration, we can't evaluate it - we pass it forward.
            MSSyntaxTree procCallArg = procCall.getProcedureArguments().get(i);
            if (procCallArg.getNodeType() == MSNodeType.EXPR_LAMBDA_DECL) {
                args.add(procCallArg);
            } else {
                // Otherwise, evaluate the arg.
                LValue lhs = this.interpretTree(procCallArg);
                if (lhs.getType() == LValue.LValueType.NUM) {
                    args.add(new MSNumberNode(lhs.getDoubleValue()));
                } else if (lhs.getType() == LValue.LValueType.BOOL) {
                    args.add(new MSBooleanNode(lhs.getBoolValue()));
                } else if (lhs.getType() == LValueType.STR) {
                    args.add(new MSStringNode(lhs.getStringValue()));
                } else if (lhs.getType() == LValueType.PROCCALL) {
                    args.add(lhs.getTreeValue());
                } else if (lhs.getType() == LValueType.PAIR) {
                    // If it is null, then evaluate the null list.
                    if (lhs.getTreeValue() == null) {
                        args.add(new MSPairNode());
                    } else {
                        args.add(lhs.getTreeValue());
                    }
                } else {
                    throw new IllegalStateException("Interpreter error - proc decl call " +
                            "found an incorrect lvalue. This should never happen...");
                }
            }
        }

        MSSyntaxTree body = procDef.getBody().copy();
        replaceParams(procDef, body, args);
        if (body.getChild(0).getNodeType() == MSNodeType.EXPR_LAMBDA_DECL) {
            MSLambdaDeclarationNode lb = (MSLambdaDeclarationNode) body.getChild(0);
            return this.interpretLambdaDeclCall(new MSLambdaDeclarationCallNode(lb.getLambdaParameters(),
                        lb.getBody(), procCall.getLambdaArguments()));
        }

        return this.interpretTree(body);
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretLambdaCall(MSSyntaxTree tree) {
        MSCallNode lambdaCall = (MSCallNode) tree;
        String id = lambdaCall.getIdentifier().getStringRep();
        MSLambdaDeclarationNode lambdaDecl = (MSLambdaDeclarationNode) this.symbolTable.getLambda(id).getLambdaDef();
        MSLambdaDeclarationCallNode lambdaDeclCall = new MSLambdaDeclarationCallNode(lambdaDecl.getLambdaParameters(), lambdaDecl.getBody(), lambdaCall.getProcedureArguments());
        return this.interpretTree(lambdaDeclCall);
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretLambdaDeclCall(MSSyntaxTree tree) {
        MSLambdaDeclarationCallNode lambdaDeclCall = (MSLambdaDeclarationCallNode) tree;
        ArrayList<MSSyntaxTree> args = new ArrayList<>();
        for (int i = 0; i < lambdaDeclCall.getLambdaArguments().size(); i++) {
            // If it's a lambda declaration, we can't evaluate it - we pass it forward.
            MSSyntaxTree lambdaDeclCallArg = lambdaDeclCall.getLambdaArguments().get(i);
            if (lambdaDeclCallArg.getNodeType() == MSNodeType.EXPR_LAMBDA_DECL) {
                args.add(lambdaDeclCallArg);
            } else {
                // Otherwise, evaluate the arg.
                LValue lhs = this.interpretTree(lambdaDeclCall.getLambdaArguments().get(i));
                if (lhs.getType() == LValue.LValueType.NUM) {
                    args.add(new MSNumberNode(lhs.getDoubleValue()));
                } else if (lhs.getType() == LValue.LValueType.BOOL) {
                    args.add(new MSBooleanNode(lhs.getBoolValue()));
                } else if (lhs.getType() == LValueType.STR) {
                    args.add(new MSStringNode(lhs.getStringValue()));
                } else if (lhs.getType() == LValueType.PROCCALL) {
                    args.add(lhs.getTreeValue());
                } else if (lhs.getType() == LValueType.PAIR) {
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

        MSSyntaxTree body = lambdaDeclCall.getLambdaBody().copy();
        replaceParams(lambdaDeclCall, body, args);
        return this.interpretTree(body);
    }

    /**
     * @param lhs
     * @param opType
     * @param rhs
     * @return
     */
    private LValue interpretPrimitiveBinaryOp(LValue lhs, int opType, LValue rhs) {
        switch (opType) {
            case MiniSchemeParser.PLUS:
                return new LValue(lhs.getDoubleValue() + rhs.getDoubleValue());
            case MiniSchemeParser.MINUS:
                return new LValue(lhs.getDoubleValue() - rhs.getDoubleValue());
            case MiniSchemeParser.STAR:
                return new LValue(lhs.getDoubleValue() * rhs.getDoubleValue());
            case MiniSchemeParser.SLASH:
                return new LValue(lhs.getDoubleValue() / rhs.getDoubleValue());
            case MiniSchemeParser.MODULO:
                return new LValue(lhs.getDoubleValue() % rhs.getDoubleValue());
            case MiniSchemeParser.EXPONENTIATION:
                return new LValue(Math.pow(lhs.getDoubleValue(), rhs.getDoubleValue()));
            case MiniSchemeParser.LOGICAL_AND:
                return new LValue(lhs.getBoolValue() && rhs.getBoolValue());
            case MiniSchemeParser.LOGICAL_OR:
                return new LValue(lhs.getBoolValue() || rhs.getBoolValue());
            case MiniSchemeParser.LOGICAL_EQ:
                return new LValue(lhs.getDoubleValue() == rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_NE:
                return new LValue(lhs.getDoubleValue() != rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_LT:
                return new LValue(lhs.getDoubleValue() < rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_LE:
                return new LValue(lhs.getDoubleValue() <= rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_GT:
                return new LValue(lhs.getDoubleValue() > rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_GE:
                return new LValue(lhs.getDoubleValue() >= rhs.getDoubleValue());
            case MiniSchemeParser.STRING_APPEND:
                return new LValue(lhs.getStringValue() + rhs.getStringValue());
            case MiniSchemeParser.RAND_FN:
                return new LValue(Math.random());
            case MiniSchemeParser.RANDINT_FN:
                return new LValue(MSUtils.randomInt((int) lhs.getDoubleValue(), (int) rhs.getDoubleValue()));
            case MiniSchemeParser.RANDDOUBLE_FN:
                return new LValue(MSUtils.randomDouble(lhs.getDoubleValue(), rhs.getDoubleValue()));
            case MiniSchemeParser.EQ_FN:
                return this.interpretEqFn(lhs, rhs);
            case MiniSchemeParser.EQUAL_FN:
                return this.interpretEqualFn(lhs, rhs);
            default:
                throw new IllegalArgumentException("ERR invalid binop type " + opType);
        }
    }

    /**
     * @param lhs
     * @param opType
     * @return
     */
    private LValue interpretPrimitiveUnaryOp(LValue lhs, int opType) {
        switch (opType) {
            case MiniSchemeParser.DISPLAY:
                System.out.println(lhs.toDisplayString());
                return new LValue(LValueType.DISP);
            case MiniSchemeParser.SIN: return new LValue(Math.sin(lhs.getDoubleValue()));
            case MiniSchemeParser.COS: return new LValue(Math.cos(lhs.getDoubleValue()));
            case MiniSchemeParser.TAN: return new LValue(Math.tan(lhs.getDoubleValue()));
            case MiniSchemeParser.ASIN: return new LValue(Math.asin(lhs.getDoubleValue()));
            case MiniSchemeParser.ACOS: return new LValue(Math.acos(lhs.getDoubleValue()));
            case MiniSchemeParser.ATAN: return new LValue(Math.atan(lhs.getDoubleValue()));
            case MiniSchemeParser.SQRT: return new LValue(Math.sqrt(lhs.getDoubleValue()));
            case MiniSchemeParser.ROUND: return new LValue(Math.round(lhs.getDoubleValue()));
            case MiniSchemeParser.FLOOR: return new LValue(Math.floor(lhs.getDoubleValue()));
            case MiniSchemeParser.CEILING: return new LValue(Math.ceil(lhs.getDoubleValue()));
            case MiniSchemeParser.TRUNCATE: return new LValue((int) lhs.getDoubleValue());
            case MiniSchemeParser.TRUE_FN: return new LValue(lhs.getBoolValue());
            case MiniSchemeParser.FALSE_FN:
            case MiniSchemeParser.LOGICAL_NOT:
                return new LValue(!lhs.getBoolValue());
            case MiniSchemeParser.CAR: return new LValue(((MSPairNode) lhs.getTreeValue()).getCar());
            case MiniSchemeParser.CDR: return new LValue(((MSPairNode) lhs.getTreeValue()).getCdr());
            case MiniSchemeParser.NULL_FN: return new LValue(lhs.getTreeValue() == null || ((MSPairNode) lhs.getTreeValue()).isNull());
            case MiniSchemeParser.NUMBER_FN: return new LValue(lhs.getType() == LValueType.NUM);
            case MiniSchemeParser.BOOL_FN: return new LValue(lhs.getType() == LValueType.BOOL);
            case MiniSchemeParser.STRING_FN: return new LValue(lhs.getType() == LValueType.STR);
            case MiniSchemeParser.PAIR_FN:
                // A "pair" cannot be the empty list.
                return new LValue(lhs.getTreeValue() != null
                        && !((MSPairNode) lhs.getTreeValue()).isNull()
                        && lhs.getType() == LValueType.PAIR);
            case MiniSchemeParser.STRLEN_FN: return new LValue(lhs.getStringValue().length());
            case MiniSchemeParser.NUMTOSTR_FN: return new LValue(new MSStringNode(lhs.toString()));
            case MiniSchemeParser.STRTONUM_FN: return new LValue(new MSNumberNode(Double.parseDouble(lhs.getStringValue())));
            case MiniSchemeParser.TODEG_FN: return new LValue(new MSNumberNode(Math.toDegrees(lhs.getDoubleValue())));
            case MiniSchemeParser.TORAD_FN: return new LValue(new MSNumberNode(Math.toRadians(lhs.getDoubleValue())));
            default:
                throw new IllegalArgumentException("ERR invalid unary type " + opType);
        }
    }

    /**
     *
     */
    private LValue interpretSetOp(MSSyntaxTree tree) {
        MSSetNode setNode = (MSSetNode) tree;
        switch (setNode.getOpType()) {
            case MiniSchemeParser.SETCAR_FN:
                this.interpretSetCarFn(setNode); break;
            case MiniSchemeParser.SETCDR_FN:
                this.interpretSetCdrFn(setNode); break;
            case MiniSchemeParser.SETVAR_FN:
                this.interpretSetVariableFn(setNode); break;
            default:
                throw new IllegalArgumentException("Internal interpreter error "
                        + "- cannot set with operator of type " + tree.getNodeType()
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
        MSPairNode pair = (MSPairNode) this.symbolTable.getVariable(id).getExpression();
        pair.setCar(setNode.getExpression());
        this.symbolTable.setVariable(id, pair);
    }

    /**
     *
     * @param setNode
     */
    private void interpretSetCdrFn(MSSetNode setNode) {
        String id = ((MSIdentifierNode) setNode.getIdentifier()).getIdentifier();
        MSPairNode pair = (MSPairNode) this.symbolTable.getVariable(id).getExpression();
        pair.setCdr(setNode.getExpression());
        this.symbolTable.setVariable(id, pair);
    }

    /**
     *
     * @param setNode
     */
    private void interpretSetVariableFn(MSSetNode setNode) {
        String id = ((MSIdentifierNode) setNode.getIdentifier()).getIdentifier();
        MSSyntaxTree tree = setNode.getExpression();
        this.symbolTable.setVariable(id, tree);
    }

    /**
     * @param lhs
     * @param rhs
     * @return
     */
    private LValue interpretEqualFn(LValue lhs, LValue rhs) {
        if (lhs.getType() == rhs.getType()) {
            switch (lhs.getType()) {
                case NUM:
                    return new LValue(lhs.getDoubleValue() == rhs.getDoubleValue());
                case BOOL:
                    return new LValue(lhs.getBoolValue() == rhs.getBoolValue());
                case STR:
                    return new LValue(lhs.getStringValue().equals(rhs.getStringValue()));
                case PAIR:
                    return new LValue(lhs.toString().equals(rhs.toString()));
                case NULL:
                    return new LValue(true);
                default:
                    throw new UnsupportedOperationException("Internal interpreter error " +
                            "- cannot use equal? procedure on operands of type " + lhs.getType());
            }
        }
        return new LValue(false);
    }

    /**
     * @param lhs
     * @param rhs
     * @return
     */
    private LValue interpretEqFn(LValue lhs, LValue rhs) {
        // If they're the same reference then return true.
        if (lhs == rhs) {
            return new LValue(true);
        } else if (lhs.getType() == rhs.getType()) {
            // Doubles are a special case.
            if (lhs.getType() == LValueType.NUM) {
                return new LValue(lhs.getDoubleValue() == rhs.getDoubleValue());
            }

            // If the identifiers are the same then... we need to return true...
            // but how do we do that without their definition? For now,
            // just return false for everything.
        }

        return new LValue(false);
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