package com.joshuacrotts.minischeme.ast;

/**
 * Represents a String literal. Fairly straightforward.
 *
 * @author Joshua Crotts
 * @version 12/23/2021
 */
public class MSStringNode extends MSSyntaxTree {

    /**
     * String associated with this node.
     */
    private final String VALUE;

    public MSStringNode(final String value) {
        super(MSNodeType.STR);
        this.VALUE = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSStringNode(this.VALUE);
    }

    @Override
    public String getStringRep() {
        if (this.VALUE.startsWith("\"")
                && this.VALUE.endsWith("\"")
                && this.VALUE.length() >= 2) {
            return this.VALUE.substring(1, this.VALUE.length() - 1);
        }
        return this.VALUE;
    }

    @Override
    public String toString() {
        return "(STR " + this.VALUE + ")";
    }

    public String getValue() {
        return this.VALUE;
    }
}
