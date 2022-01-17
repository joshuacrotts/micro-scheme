package com.joshuacrotts.minischeme.ast;

/**
 * Represents a literal numeric value. For now, these are only doubles
 * but if I ever plan to increase this to, say, BigDecimal, it should be
 * easy.
 * <p>
 * If the numeric value is able to be represented as an integer (e.g., 5.000 is
 * represented as 5), it will be shown as an integer.
 *
 * @author Joshua Crotts
 * @version 12/31/2021
 */
public class MSNumberNode extends MSSyntaxTree {

    /**
     * Number associated with this node.
     */
    private final double VALUE;

    public MSNumberNode(final String value) {
        this(Double.parseDouble(value));
    }

    public MSNumberNode(final double value) {
        super(MSNodeType.NUM);
        this.VALUE = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSNumberNode(this.VALUE);
    }

    @Override
    public String getStringRep() {
        return ((int) this.VALUE) == this.VALUE
                ? Integer.toString((int) this.VALUE)
                : Double.toString(this.VALUE);
    }

    @Override
    public String toString() {
        return "(NUMBER " + this.getStringRep() + ")";
    }

    public double getValue() {
        return this.VALUE;
    }

    public boolean isInteger() {
        return this.VALUE == (int) this.VALUE;
    }
}
