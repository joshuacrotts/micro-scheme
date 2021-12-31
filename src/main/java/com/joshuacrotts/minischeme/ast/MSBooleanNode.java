package com.joshuacrotts.minischeme.ast;

/**
 * Represents a boolean literal. Booleans in MiniScheme are #t for
 * true, and #f for false.
 *
 * @author Joshua Crotts
 * @version 12/31/2021
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
