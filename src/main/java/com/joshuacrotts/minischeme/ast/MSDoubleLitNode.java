package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public class MSDoubleLitNode extends MSSyntaxTree {

    /**
     *
     */
    private double value;

    public MSDoubleLitNode(String value) {
        this(Double.parseDouble(value));
    }

    public MSDoubleLitNode(double value) {
        super(MSNodeType.NUM);
        this.value = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSDoubleLitNode(this.value);
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getStringRep() {
        return ((int) this.value) == this.value
                    ? Integer.toString((int) this.value)
                    : Double.toString(this.value);
    }

    @Override
    public String toString() {
        return "(DOUBLE " + this.getStringRep() + ")";
    }
}
