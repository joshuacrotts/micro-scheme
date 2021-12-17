package com.joshuacrotts.minischeme.ast;

public class MSDoubleLitNode extends MSSyntaxTree {

    /**
     *
     */
    private double value;

    public MSDoubleLitNode(String value) {
        super(MSNodeType.MS_NUM);
        this.value = Double.parseDouble(value);
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
