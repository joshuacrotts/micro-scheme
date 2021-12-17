package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.MSDoubleLitNode;
import com.joshuacrotts.minischeme.ast.MSNodeType;
import com.joshuacrotts.minischeme.ast.MSOpExpression;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

public class MiniSchemeInterpreter {

    /**
     *
     */
    private MSSyntaxTree tree;

    public MiniSchemeInterpreter(MSSyntaxTree tree) {
        this.tree = tree;
    }

    public double interpretTree(MSSyntaxTree tree) {
        if (tree.getNodeType() == MSNodeType.MS_ROOT) {
            return interpretTree(tree.getChild(0));
        } else if (tree.getNodeType() == MSNodeType.MS_NUM) {
            return ((MSDoubleLitNode) tree).getValue();
        } else if (tree.getNodeType() == MSNodeType.MS_OP) {
            int opType = ((MSOpExpression) tree).getOpType();
            double res = this.interpretTree(tree.getChild(0));
            for (int i = 1; i < tree.getChildrenSize(); i++) {
                res = this.interpretPrimitiveOp(res, opType, this.interpretTree(tree.getChild(i)));
            }
            return res;
        }

        return 0;
    }

    /**
     *
     * @param lhs
     * @param opType
     * @param rhs
     * @return
     */
    private double interpretPrimitiveOp(double lhs, int opType, double rhs) {
        switch (opType) {
            case MiniSchemeParser.PLUS: return lhs + rhs;
            case MiniSchemeParser.MINUS: return lhs - rhs;
            case MiniSchemeParser.STAR: return lhs * rhs;
            case MiniSchemeParser.SLASH: return lhs / rhs;
        }

        throw new IllegalArgumentException("ERR invalid op type " + opType);
    }
}