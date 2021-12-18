package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.parser.MSListener;

import java.util.ArrayList;

public class MiniSchemeInterpreter {

    /**
     *
     */
    private MSSyntaxTree tree;

    public MiniSchemeInterpreter(MSSyntaxTree tree) {
        this.tree = tree;
    }

    protected void execute() {
        for (MSSyntaxTree ch : this.tree.getChildren()) {
            LValue lhs = this.interpretTree(ch);
            if (lhs.type == LValue.LValueType.LVAL_NUM) { System.out.println(lhs); }
        }
    }

    /**
     *
     * @param tree
     * @return
     */
    protected LValue interpretTree(MSSyntaxTree tree) {
        if (tree == null) { return new LValue(LValue.LValueType.LVAL_NULL); }
        if (tree.getNodeType() == MSNodeType.MS_ROOT) { return this.interpretTree(tree.getChild(0)); }
        if (tree.getNodeType() == MSNodeType.MS_NUM) { return new LValue(((MSDoubleLitNode) tree).getValue()); }
        if (tree.getNodeType() == MSNodeType.MS_OP) {
            int opType = ((MSOpExpression) tree).getOpType();
            LValue res = this.interpretTree(tree.getChild(0));
            for (int i = 1; i < tree.getChildrenSize(); i++) {
                res = this.interpretPrimitiveOp(res, opType, this.interpretTree(tree.getChild(i)));
            }
            return res;
        }
        if (tree.getNodeType() == MSNodeType.MS_ID) {
            String id = ((MSIdentifierNode) tree).getStringRep();
            System.out.println(tree);
            MSSyntaxTree expr = MSListener.symbolTable.getVariable(id).getExpression().getChild(1);
            return this.interpretTree(expr);
        }
        if (tree.getNodeType() == MSNodeType.MS_PROCCALL) {
            MSProcedureCallNode procCall = (MSProcedureCallNode) tree;
            String id = procCall.getIdentifier().getStringRep();
            MSProcedureDefinitionNode def = (MSProcedureDefinitionNode) MSListener.symbolTable.getProcedure(id).getProcDef();
            ArrayList<MSSyntaxTree> args = new ArrayList<>();
            for (int i = 0; i < procCall.getArguments().size(); i++) {
                LValue lhs = this.interpretTree(procCall.getArguments().get(i));
                if (lhs.type == LValue.LValueType.LVAL_NUM) {
                    args.add(new MSDoubleLitNode(lhs.dval));
                }
            }

            MSSyntaxTree body = def.getBody().copy();
            replaceParams(def, body, args);
            return this.interpretTree(body);
        }
        return new LValue();
    }

    /**
     *
     * @param lhs
     * @param opType
     * @param rhs
     * @return
     */
    private LValue interpretPrimitiveOp(LValue lhs, int opType, LValue rhs) {
        switch (opType) {
            case MiniSchemeParser.PLUS: return new LValue(lhs.dval + rhs.dval);
            case MiniSchemeParser.MINUS: return new LValue(lhs.dval - rhs.dval);
            case MiniSchemeParser.STAR: return new LValue(lhs.dval * rhs.dval);
            case MiniSchemeParser.SLASH: return new LValue(lhs.dval / rhs.dval);
        }

        throw new IllegalArgumentException("ERR invalid op type " + opType);
    }

    /**
     *
     * @param body
     * @param args
     */
    private static void replaceParams(MSProcedureDefinitionNode procDef,
                                      MSSyntaxTree body, ArrayList<MSSyntaxTree> args) {
        for (int i = 0; i < args.size(); i++) {
            MSSyntaxTree arg = args.get(i);
            replaceParamsHelper(procDef, body, arg, i);
        }
    }

    /**
     *
     * @param body
     * @param arg
     */
    private static void replaceParamsHelper(MSProcedureDefinitionNode procDef,
                                            MSSyntaxTree body, MSSyntaxTree arg, int idx) {
        for (int i = 0; i < body.getChildrenSize(); i++) {
            MSSyntaxTree child = body.getChild(i);
            if (child.getNodeType() == MSNodeType.MS_ID) {
                MSIdentifierNode id = (MSIdentifierNode) child;
                if (procDef.getArgumentLoc(id.getIdentifier()) == idx) {
                    body.setChild(i, arg);
                }
            } else {
                replaceParamsHelper(procDef, child, arg, i);
            }
        }
    }
}