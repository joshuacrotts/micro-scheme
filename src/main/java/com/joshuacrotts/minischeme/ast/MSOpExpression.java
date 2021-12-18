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

    private String getOpTypeString(int opType) {
        switch (opType) {
            case MiniSchemeParser.PLUS: return "+";
            case MiniSchemeParser.MINUS: return "-";
            case MiniSchemeParser.STAR: return "*";
            case MiniSchemeParser.SLASH: return "/";
            default:
                throw new IllegalArgumentException("ERR invalid operator " + opType);
        }
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
        StringBuilder sb = new StringBuilder("(OP " + getOpTypeString(this.opType));

        for (int i = 0; i < this.getChildrenSize() - 1; i++) {
            sb.append(this.getChild(i));
            sb.append(", ");
        }
        sb.append(this.getChild(this.getChildrenSize() - 1).toString()).append(")");
        return sb.toString();
    }
}
