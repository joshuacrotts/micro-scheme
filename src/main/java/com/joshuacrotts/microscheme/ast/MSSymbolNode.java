/******************************************************************************
 *  File: MSSymbolNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

public class MSSymbolNode extends MSSyntaxTree {

    /**
     * Symbol text associated with this node.
     */
    private final MSSyntaxTree VALUE;

    /**
     *
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
