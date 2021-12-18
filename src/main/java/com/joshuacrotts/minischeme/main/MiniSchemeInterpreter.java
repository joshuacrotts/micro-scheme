package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.parser.MSListener;

public class MiniSchemeInterpreter {

    /**
     *
     */
    private MSSyntaxTree tree;

    public MiniSchemeInterpreter(MSSyntaxTree tree) {
        this.tree = tree;
    }

    /**
     *
     * @param tree
     * @return
     */
    public LValue interpretTree(MSSyntaxTree tree) {
        if (tree == null) { return new LValue(LValue.LValueType.LVAL_NULL); }
        if (tree.getNodeType() == MSNodeType.MS_ROOT) { return this.interpretTree(tree.getChild(0)); }
        if (tree.getNodeType() == MSNodeType.MS_NUM) { return new LValue(((MSDoubleLitNode) tree).getValue()); } else if (tree.getNodeType() == MSNodeType.MS_OP) {
            int opType = ((MSOpExpression) tree).getOpType();
            LValue res = this.interpretTree(tree.getChild(0));
            for (int i = 1; i < tree.getChildrenSize(); i++) {
                res = this.interpretPrimitiveOp(res, opType, this.interpretTree(tree.getChild(i)));
            }
            return res;
        }
        if (tree.getNodeType() == MSNodeType.MS_ID) {
            String id = ((MSIdentifierNode) tree).getStringRep();
            MSSyntaxTree expr = MSListener.symbolTable.getVariable(id).getExpression().getChild(1);
            return this.interpretTree(expr);
        }
        if (tree.getNodeType() == MSNodeType.MS_PROCCALL) {
            String id = ((MSProcedureCallNode) tree).getIdentifier().getStringRep();
            MSProcedureDefinitionNode def = (MSProcedureDefinitionNode) MSListener.symbolTable.getProcedure(id).getProcDef();
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
     */
    private static class LValue {

        /**
         *
         */
        private LValueType type;

        /**
         *
         */
        private double dval;

        protected LValue(LValueType type) {
            this.type = type;
        }

        protected LValue(double dval) {
            this(LValueType.LVAL_NUM);
            this.dval = dval;
        }

        protected LValue() {
            this(LValueType.LVAL_NULL);
        }

        private enum LValueType {
            LVAL_NUM, LVAL_DEF, LVAL_NULL
        }

        public String toString() {
            switch (this.type) {
                case LVAL_NUM: return Double.toString(this.dval);
            }
            return "";
        }
    }
}