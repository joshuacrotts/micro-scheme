/******************************************************************************
 *  File: MSSymbolNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  Symbols, aka atoms, are any type of variable, operator, or identifier that
 *  can be quoted.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

public class MSSymbolNode extends MSSyntaxTree {

    /**
     * Symbol text associated with this node.
     */
    private final MSSyntaxTree VALUE;

    /**
     * Keeps track of whether this is the @ symbol for quasi-quotes.
     */
    private final boolean IS_QUASI_AT;

    public MSSymbolNode(final MSSyntaxTree symbol) {
        this(symbol, false);
    }

    public MSSymbolNode(final MSSyntaxTree symbol, final boolean isQuasiAt) {
        super(MSNodeType.SYMBOL);
        this.VALUE = symbol;
        this.IS_QUASI_AT = isQuasiAt;
    }

    @Override
    public String getStringRep() {
        return this.VALUE.getStringRep();
    }

    public boolean isQuasiAtSymbol() {
        return this.IS_QUASI_AT;
    }

    public MSSyntaxTree getValue() {
        return this.VALUE;
    }
}
