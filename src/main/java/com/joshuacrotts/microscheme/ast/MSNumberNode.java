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

package com.joshuacrotts.microscheme.ast;

import ch.obermuhlner.math.big.BigComplex;
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
    private final BigComplex VALUE;

    public MSNumberNode(final BigComplex bigComplex) {
        super(MSNodeType.NUMBER);
        this.VALUE = bigComplex;
    }

    public MSNumberNode(final BigDecimal bigDecimal) {
        super(MSNodeType.NUMBER);
        this.VALUE = BigComplex.valueOf(bigDecimal);
    }

    public MSNumberNode(final String stringRep) {
        this(new BigDecimal(stringRep));
    }

    public MSNumberNode(final int number) {
        this(new BigDecimal(number));
    }

    public MSNumberNode(final double number) {
        this(new BigDecimal(number));
    }

    /**
     * Constructs a complex number from a string. The user must enter
     * a value in the form a +- bi, where a is the real component and
     * b is the imaginary component.
     *
     * @param number string representation of number.
     * @return BigComplex of string rep.
     */
    public static BigComplex extractComplexFromString(String number) {
        if (number.endsWith("i")) {
            number = number.substring(0, number.length() - 1);
            boolean firstNegative = false;
            boolean secondNegative = false;
            if (number.startsWith("-")) firstNegative = true;
            if (number.substring(1).contains("-")) secondNegative = true;
            // Cases where both cannot be negative.
            BigComplex val;
            if (!firstNegative || !secondNegative) {
                if (firstNegative) {
                    number = number.substring(1);
                }
                String[] parts = number.split("[+-]");
                BigDecimal lhs = new BigDecimal(parts[0]);
                BigDecimal rhs = secondNegative ? new BigDecimal(parts[1]).negate() : new BigDecimal(parts[1]);
                return BigComplex.valueOf(lhs, rhs);
            } else {
                // Case where both are negative.
                number = number.substring(1);
                String[] parts = number.split("-");
                return BigComplex.valueOf(new BigDecimal(parts[0]).negate(), new BigDecimal(parts[1]).negate());
            }
        } else {
            return BigComplex.valueOf(new BigDecimal(number));
        }
    }

    @Override
    public String getStringRep() {
        if (this.isInteger()) {
            return this.VALUE.re.stripTrailingZeros().toPlainString();
        } else if (this.VALUE.isReal()) {
            return this.VALUE.re.toString();
        } else {
            // Remove all spaces and parentheses from the complex number.
            return this.VALUE.toString().replaceAll("[()\\s]", "");
        }
    }

    public BigComplex getValue() {
        return this.VALUE;
    }

    public boolean isInteger() {
        return this.VALUE.isReal() && this.isIntegerValue(this.VALUE.re);
    }

    public boolean isReal() {
        return this.VALUE.isReal();
    }

    public boolean isComplex() {
        return true;
    }

    private boolean isIntegerValue(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }
}
