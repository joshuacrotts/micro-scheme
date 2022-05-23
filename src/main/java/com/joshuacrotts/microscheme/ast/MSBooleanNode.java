/******************************************************************************
 *  File: MSBooleanNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *  Represents a boolean literal. Booleans in MicroScheme are #t, #true, #True
 *  for true, and #f, #false, #False for false.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

public final class MSBooleanNode extends MSSyntaxTree {

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
