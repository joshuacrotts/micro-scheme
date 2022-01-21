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
    private final boolean VALUE;

    public MSBooleanNode(final String value) {
        super(MSNodeType.BOOLEAN);
        this.VALUE = value.equals("#t");
    }

    public MSBooleanNode(final boolean value) {
        super(MSNodeType.BOOLEAN);
        this.VALUE = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSBooleanNode(this.VALUE);
    }

    @Override
    public String getStringRep() {
        return this.VALUE ? "#t" : "#f";
    }

    @Override
    public String toString() {
        return "(BOOLEAN " + this.VALUE + ")";
    }

    public boolean getValue() {
        return this.VALUE;
    }
}
