package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.MiniSchemeParser;

public class MSOpExpression extends MSSyntaxTree {

    /**
     *
     */
    private int opType;

    public MSOpExpression(int opType, MSSyntaxTree ... children) {
        super(MSNodeType.MS_OP, children);
        this.opType = opType;
    }

    private MSOpExpression(int opType) {
        super(MSNodeType.MS_OP);
        this.opType = opType;
    }

    @Override
    public MSSyntaxTree copy() {
        MSOpExpression exp = new MSOpExpression(this.opType);
        for (MSSyntaxTree ch : this.getChildren()) {
            exp.addChild(ch.copy());
        }
        return exp;
    }

    /**
     *
     * @param opType
     * @return
     */
    private String getOpTypeString(int opType) {
        String literalName = MiniSchemeParser.VOCABULARY.getLiteralName(opType);
        return literalName.substring(1, literalName.length() - 1);
    }

    public int getOpType() {
        return this.opType;
    }

    @Override
    public String getStringRep() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(").append(getOpTypeString(this.opType)).append(" ");

        for (int i = 0; i < this.getChildrenSize() - 1; i++) {
            stringBuilder.append(this.getChild(i).getStringRep()).append(" ");
        }

        stringBuilder.append(this.getChild(this.getChildrenSize() - 1).getStringRep()).append(")");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OP " + getOpTypeString(this.opType) + "(");
        for (int i = 0; i < this.getChildrenSize() - 1; i++) {
            sb.append(this.getChild(i).toString()).append(" ");
        }

        sb.append(this.getChild(this.getChildrenSize() - 1).toString()).append(")");
        return sb.toString();
    }
}
