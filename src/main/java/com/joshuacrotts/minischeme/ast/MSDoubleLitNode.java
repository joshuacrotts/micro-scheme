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
        super(MSNodeType.MS_NUM);
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
        return Double.toString(value);
    }

    @Override
    public String toString() {
        return "(DOUBLE " + this.value + ")";
    }
}
