package com.joshuacrotts.minischeme.ast;

/**
 *
 * @author Joshua Crotts
 * @version 01/19/2022
 */
public class MSStringNode extends MSSyntaxTree {

    /**
     * String associated with this node.
     */
    private final String VALUE;

    public MSStringNode(final String value) {
        super(MSNodeType.STRING);
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
        return "(STRING " + this.VALUE + ")";
    }

    public String getValue() {
        return this.VALUE;
    }

    public int length() {
        return this.getStringRep().length();
    }
}
