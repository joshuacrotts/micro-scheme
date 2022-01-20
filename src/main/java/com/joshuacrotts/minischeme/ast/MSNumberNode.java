package com.joshuacrotts.minischeme.ast;

import java.math.BigDecimal;

/**
 *
 * @author Joshua Crotts
 * @version 01/19/2022
 */
public class MSNumberNode extends MSSyntaxTree {

    /**
     * Number associated with this node.
     */
    private final BigDecimal VALUE;

    public MSNumberNode(final String stringRep) {
        super(MSNodeType.NUMBER);
        this.VALUE = new BigDecimal(stringRep);
    }

    public MSNumberNode(final BigDecimal bigDecimal) {
        super(MSNodeType.NUMBER);
        this.VALUE = bigDecimal;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSNumberNode(this.VALUE);
    }

    @Override
    public String getStringRep() {
        return this.VALUE.toString();
    }

    public BigDecimal getNumber() {
        return this.VALUE;
    }
}
