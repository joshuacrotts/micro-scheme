/******************************************************************************
 *  File: MSNumberNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

import ch.obermuhlner.math.big.BigFloat;

import java.math.BigDecimal;
import java.math.MathContext;

public class MSNumberNode extends MSSyntaxTree {

    /**
     *
     */
    public static final MathContext PRECISION = new MathContext(24);

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

    public MSNumberNode(final int number) {
        super(MSNodeType.NUMBER);
        this.VALUE = new BigDecimal(number);
    }

    public MSNumberNode(final double number) {
        super(MSNodeType.NUMBER);
        this.VALUE = new BigDecimal(number);
    }

    @Override
    public String getStringRep() {
        if (this.isIntegerValue(this.VALUE)) {
            return this.VALUE.stripTrailingZeros().toPlainString();
        } else {
            return this.VALUE.toString();
        }
    }

    public BigDecimal getValue() {
        return this.VALUE;
    }

    private boolean isIntegerValue(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }
}
