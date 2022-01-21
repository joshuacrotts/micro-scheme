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
    private final MSSyntaxTree VALUE;

    public MSSymbolNode(final MSSyntaxTree symbol) {
        super(MSNodeType.SYMBOL);
        this.VALUE = symbol;
    }

    @Override
    public String getStringRep() {
        return this.VALUE.getStringRep();
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSymbolNode(this.VALUE.copy());
    }

    public MSSyntaxTree getValue() {
        return this.VALUE;
    }
}
