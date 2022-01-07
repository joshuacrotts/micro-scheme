package com.joshuacrotts.minischeme.ast;

/**
 * Represents a String literal. Fairly straightforward.
 *
 * @author Joshua Crotts
 * @version 12/23/2021
 */
public class MSSymbolLiteralNode extends MSSyntaxTree {

    /**
     * String associated with this node.
     */
    private final String value;

    public MSSymbolLiteralNode(String value) {
        super(MSNodeType.SYMBOL_LIT);
        this.value = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSymbolLiteralNode(this.value);
    }

    @Override
    public String getStringRep() {
        return this.value;
    }

    @Override
    public String toString() {
        return "(STR " + this.value + ")";
    }

    public String getValue() {
        return this.value;
    }
}
