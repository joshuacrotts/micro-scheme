package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public class MSSetReadNode extends MSSyntaxTree {

    /**
     *
     */
    private int opType;

    public MSSetReadNode(int opType, MSSyntaxTree identifier) {
        super(MSNodeType.SET_READ, identifier);
        this.opType = opType;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSetReadNode(this.opType, this.getChild(0).copy());
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getIdentifier() {
        return this.getChild(0);
    }

    public int getOpType() {
        return this.opType;
    }
}
