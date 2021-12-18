package com.joshuacrotts.minischeme.ast;

public class MSBooleanLitNode extends MSSyntaxTree {

    /**
     *
     */
    private boolean value;

    public MSBooleanLitNode(String value) {
        super(MSNodeType.MS_BOOL);
        this.value = value.equals("#t");
    }

    public MSBooleanLitNode(boolean value) {
        super(MSNodeType.MS_BOOL);
        this.value = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSBooleanLitNode(this.value);
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public String getStringRep() {
        return Boolean.toString(value);
    }

    @Override
    public String toString() {
        return "(BOOL " + this.value + ")";
    }
}
