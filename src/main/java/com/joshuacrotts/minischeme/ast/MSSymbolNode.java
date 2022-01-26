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

package com.joshuacrotts.minischeme.ast;

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
