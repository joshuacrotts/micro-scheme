package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.MSBooleanLitNode;
import com.joshuacrotts.minischeme.ast.MSDoubleLitNode;
import com.joshuacrotts.minischeme.ast.MSIdentifierNode;
import com.joshuacrotts.minischeme.ast.MSNodeType;
import com.joshuacrotts.minischeme.ast.MSOpExpression;
import com.joshuacrotts.minischeme.ast.MSPairNode;
import com.joshuacrotts.minischeme.ast.MSProcedureCallNode;
import com.joshuacrotts.minischeme.ast.MSProcedureDefinitionNode;
import com.joshuacrotts.minischeme.ast.MSStringLitNode;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.main.LValue.LValueType;
import com.joshuacrotts.minischeme.parser.MSListener;
import com.joshuacrotts.minischeme.parser.MSSemanticError;
import java.util.ArrayList;

public class MiniSchemeInterpreter {

    /**
     *
     */
    private final MSSyntaxTree tree;

    public MiniSchemeInterpreter(MSSyntaxTree tree) {
        this.tree = tree;
    }

    /**
     *
     */
    public void execute() {
        for (MSSyntaxTree ch : this.tree.getChildren()) {
            LValue lhs = this.interpretTree(ch);
            switch (lhs.type) {
                case NUM:
                case BOOL:
                case PAIR:
                    System.out.println(lhs);
            }
        }
    }

    /**
     * @param procDef
     * @param body
     * @param args
     */
    private static void replaceParams(MSProcedureDefinitionNode procDef,
        MSSyntaxTree body, ArrayList<MSSyntaxTree> args) {
        for (int i = 0; i < args.size(); i++) {
            replaceParamsHelper(procDef, body, args.get(i), i);
        }
    }

    /**
     * @param procDef
     * @param body
     * @param arg
     * @param replaceIdx
     */
    private static void replaceParamsHelper(MSProcedureDefinitionNode procDef,
        MSSyntaxTree body, MSSyntaxTree arg, int replaceIdx) {
        for (int i = 0; i < body.getChildrenSize(); i++) {
            MSSyntaxTree child = body.getChild(i);
            if (child.getNodeType() == MSNodeType.ID) {
                MSIdentifierNode id = (MSIdentifierNode) child;
                if (procDef.getArgumentLoc(id.getIdentifier()) == replaceIdx) {
                    body.setChild(i, arg);
                }
            } else {
                replaceParamsHelper(procDef, child, arg, replaceIdx);
            }
        }
    }

    /**
     * @param tree
     * @return
     */
    protected LValue interpretTree(MSSyntaxTree tree) {
        if (tree == null) {
            return new LValue(LValue.LValueType.NULL);
        }
        try {
            switch (tree.getNodeType()) {
                case ROOT:
                    return this.interpretTree(tree.getChild(0));
                case ID:
                    return this.interpretIdentifier(tree);
                case OP:
                    return this.interpretOperator(tree);
                case NUM:
                    return this.interpretNumber(tree);
                case BOOL:
                    return this.interpretBoolean(tree);
                case PAIR:
                    return this.interpretPair(tree);
                case LIST:
                    return this.interpretList(tree);
                case IF:
                    return this.interpretIf(tree);
                case COND:
                    return this.interpretCond(tree);
                case PROCCALL:
                    return this.interpretProcCall(tree);
            }
        } catch (MSSemanticError err) {
            System.err.println(err.getMessage());
        }

        return new LValue();
    }

    /**
     * @param tree
     */
    private LValue interpretNumber(MSSyntaxTree tree) {
        return new LValue(((MSDoubleLitNode) tree).getValue());
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretBoolean(MSSyntaxTree tree) {
        return new LValue(((MSBooleanLitNode) tree).getValue());
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretPair(MSSyntaxTree tree) throws MSSemanticError {
        MSPairNode pairNode = (MSPairNode) tree;
        // Evaluate the CAR and CDR.
        LValue carEval = this.interpretTree(pairNode.getCar());
        LValue cdrEval = this.interpretTree(pairNode.getCdr());

        MSSyntaxTree carNode = null;
        MSSyntaxTree cdrNode = null;
        switch (carEval.type) {
            case NUM:
                carNode = new MSDoubleLitNode(carEval.dval);
                break;
            case BOOL:
                carNode = new MSBooleanLitNode(carEval.bval);
                break;
            case PAIR:
                carNode = carEval.tval;
                break;
            case NULL:
                break;
            default:
                throw new UnsupportedOperationException(
                    "Cannot make a pair out of datatype " + carEval.type + " yet.");
        }

        switch (cdrEval.type) {
            case NUM:
                cdrNode = new MSDoubleLitNode(cdrEval.dval);
                break;
            case BOOL:
                cdrNode = new MSBooleanLitNode(cdrEval.bval);
                break;
            case PAIR:
                cdrNode = cdrEval.tval;
                break;
            case NULL:
                break;
            default:
                throw new UnsupportedOperationException(
                    "Cannot make a pair out of datatype " + cdrEval.type + " yet.");
        }

        return new LValue(new MSPairNode(MSNodeType.PAIR, carNode, cdrNode));
    }

    /**
     * @param tree
     * @return
     * @throws MSSemanticError
     */
    private LValue interpretList(MSSyntaxTree tree) throws MSSemanticError {
        MSPairNode rootPair = (MSPairNode) tree;
        // We need to evaluate every element of the "list".
        LValue carEval = this.interpretTree(rootPair.getCar());
        LValue cdrEval = this.interpretTree(rootPair.getCdr());

        MSSyntaxTree carNode = null;
        MSSyntaxTree cdrNode = null;
        switch (carEval.type) {
            case NUM:
                carNode = new MSDoubleLitNode(carEval.dval);
                break;
            case BOOL:
                carNode = new MSBooleanLitNode(carEval.bval);
                break;
            case PAIR:
                carNode = carEval.tval;
                break;
            case NULL:
                break;
            default:
                throw new UnsupportedOperationException(
                    "Cannot make a list out of datatype " + carEval.type + " yet.");
        }

        switch (cdrEval.type) {
            case NUM:
                cdrNode = new MSDoubleLitNode(cdrEval.dval);
                break;
            case BOOL:
                cdrNode = new MSBooleanLitNode(cdrEval.bval);
                break;
            case PAIR:
                cdrNode = cdrEval.tval;
                break;
            case NULL:
                break;
            default:
                throw new UnsupportedOperationException(
                    "Cannot make a list out of datatype " + cdrEval.type + " yet.");
        }
        return new LValue(new MSPairNode(MSNodeType.LIST, carNode, cdrNode));
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretOperator(MSSyntaxTree tree) {
        int opType = ((MSOpExpression) tree).getOpType();
        LValue res = null;
        // Determine if it's a unary operator or nary.
        if (tree.getChildrenSize() == 1) {
            res = this.interpretPrimitiveUnaryOp(this.interpretTree(tree.getChild(0)), opType);
        } else {
            res = this.interpretTree(tree.getChild(0));
            for (int i = 1; i < tree.getChildrenSize(); i++) {
                res = this
                    .interpretPrimitiveBinaryOp(res, opType, this.interpretTree(tree.getChild(i)));
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
        MSSyntaxTree expr = MSListener.symbolTable.getVariable(id).getExpression().getChild(1);
        return this.interpretTree(expr);
    }

    /**
     * @param tree
     * @return
     */
    private LValue interpretIf(MSSyntaxTree tree) {
        LValue ifCond = this.interpretTree(tree.getChild(0));
        if (ifCond.type == LValue.LValueType.BOOL) {
            return ifCond.bval ? this.interpretTree(tree.getChild(1))
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

        while (condIdx < tree.getChildrenSize()
            && bodyIdx < tree.getChildrenSize()) {
            LValue condCond = this.interpretTree(tree.getChild(condIdx));
            // If the condition is true, evaluate that expression.
            if (condCond.bval) {
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
     * @param tree
     * @return
     */
    private LValue interpretProcCall(MSSyntaxTree tree) {
        MSProcedureCallNode procCall = (MSProcedureCallNode) tree;
        String id = procCall.getIdentifier().getStringRep();
        MSProcedureDefinitionNode def = (MSProcedureDefinitionNode)
            MSListener.symbolTable.getProcedure(id).getProcDef();
        ArrayList<MSSyntaxTree> args = new ArrayList<>();
        for (int i = 0; i < procCall.getArguments().size(); i++) {
            LValue lhs = this.interpretTree(procCall.getArguments().get(i));
            if (lhs.type == LValue.LValueType.NUM) {
                args.add(new MSDoubleLitNode(lhs.dval));
            } else if (lhs.type == LValue.LValueType.BOOL) {
                args.add(new MSBooleanLitNode(lhs.bval));
            } else if (lhs.type == LValueType.STR) {
                args.add(new MSStringLitNode(lhs.sval));
            } else if (lhs.type == LValueType.PAIR) {
                // If it is null, then evaluate the null list.
                if (lhs.tval == null) {
                    args.add(new MSPairNode());
                } else {
                    MSPairNode pair = (MSPairNode) lhs.tval.copy();
                    args.add(lhs.tval.copy());
                }
            }
        }

        MSSyntaxTree body = def.getBody().copy();
        replaceParams(def, body, args);
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
                return new LValue(lhs.dval + rhs.dval);
            case MiniSchemeParser.MINUS:
                return new LValue(lhs.dval - rhs.dval);
            case MiniSchemeParser.STAR:
                return new LValue(lhs.dval * rhs.dval);
            case MiniSchemeParser.SLASH:
                return new LValue(lhs.dval / rhs.dval);
            case MiniSchemeParser.MODULO:
                return new LValue(lhs.dval % rhs.dval);
            case MiniSchemeParser.EXPONENTIATION:
                return new LValue(Math.pow(lhs.dval, rhs.dval));
            case MiniSchemeParser.LOGICAL_EQ:
                return new LValue(lhs.dval == rhs.dval);
            case MiniSchemeParser.LOGICAL_NE:
                return new LValue(lhs.dval != rhs.dval);
            case MiniSchemeParser.LOGICAL_LT:
                return new LValue(lhs.dval < rhs.dval);
            case MiniSchemeParser.LOGICAL_LE:
                return new LValue(lhs.dval <= rhs.dval);
            case MiniSchemeParser.LOGICAL_GT:
                return new LValue(lhs.dval > rhs.dval);
            case MiniSchemeParser.LOGICAL_GE:
                return new LValue(lhs.dval >= rhs.dval);
        }

        throw new IllegalArgumentException("ERR invalid binop type " + opType);
    }

    /**
     * @param lhs
     * @param opType
     * @return
     */
    private LValue interpretPrimitiveUnaryOp(LValue lhs, int opType) {
        switch (opType) {
            case MiniSchemeParser.SIN:
                return new LValue(Math.sin(lhs.dval));
            case MiniSchemeParser.COS:
                return new LValue(Math.cos(lhs.dval));
            case MiniSchemeParser.TAN:
                return new LValue(Math.tan(lhs.dval));
            case MiniSchemeParser.ASIN:
                return new LValue(Math.asin(lhs.dval));
            case MiniSchemeParser.ACOS:
                return new LValue(Math.acos(lhs.dval));
            case MiniSchemeParser.ATAN:
                return new LValue(Math.atan(lhs.dval));
            case MiniSchemeParser.SQRT:
                return new LValue(Math.sqrt(lhs.dval));
            case MiniSchemeParser.NOT:
                return new LValue(!lhs.bval);
            case MiniSchemeParser.CAR:
                return new LValue(((MSPairNode) lhs.tval).getCar());
            case MiniSchemeParser.CDR:
                return new LValue(((MSPairNode) lhs.tval).getCdr());
            case MiniSchemeParser.NULL_FN:
                return new LValue(lhs.tval == null || ((MSPairNode) lhs.tval).isNull());
            case MiniSchemeParser.ZERO_FN:
                return new LValue(lhs.dval == 0 && lhs.type == LValueType.NUM);
        }

        throw new IllegalArgumentException("ERR invalid unary type " + opType);
    }
}