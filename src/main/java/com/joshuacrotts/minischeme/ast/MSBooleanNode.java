package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public class MSBooleanNode extends MSSyntaxTree {

    /**
     * Boolean associated with this node.
     */
    private final boolean value;

    public MSBooleanNode(String value) {
        super(MSNodeType.BOOL);
        this.value = value.equals("#t");
    }

    public MSBooleanNode(boolean value) {
        super(MSNodeType.BOOL);
        this.value = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSBooleanNode(this.value);
    }

    @Override
    public String getStringRep() {
        return Boolean.toString(value);
    }

    @Override
    public String toString() {
        return "(BOOL " + this.value + ")";
    }

    public boolean getValue() {
        return this.value;
    }
}
