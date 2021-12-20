package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public class MSNumberNode extends MSSyntaxTree {

    /**
     *
     */
    private final double value;

    public MSNumberNode(String value) {
        this(Double.parseDouble(value));
    }

    public MSNumberNode(double value) {
        super(MSNodeType.NUM);
        this.value = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSNumberNode(this.value);
    }

    @Override
    public String getStringRep() {
        return ((int) this.value) == this.value
               ? Integer.toString((int) this.value)
               : Double.toString(this.value);
    }

    @Override
    public String toString() {
        return "(NUMBER " + this.getStringRep() + ")";
    }

    public double getValue() {
        return this.value;
    }
}
