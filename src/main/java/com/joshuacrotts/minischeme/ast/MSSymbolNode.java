package com.joshuacrotts.minischeme.ast;

/**
 *
 * @author Joshua Crotts
 * @version 01/19/2022
 */
public class MSSymbolNode extends MSSyntaxTree {

    /**
     * Symbol text associated with this node.
     */
    private final String VALUE;

    public MSSymbolNode(final String symbol) {
        super(MSNodeType.SYMBOL);
        this.VALUE = symbol;
    }

    @Override
    public String getStringRep() {
        return this.VALUE;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSymbolNode(this.VALUE);
    }

    public String getValue() {
        return this.VALUE;
    }
}
