package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public class MSSetReadNode extends MSSyntaxTree {

    /**
     * Set/read operation type.
     */
    private final int OP_TYPE;

    public MSSetReadNode(final int opType, final MSSyntaxTree identifier) {
        super(MSNodeType.SET_READ, identifier);
        this.OP_TYPE = opType;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSetReadNode(this.OP_TYPE, this.getChild(0).copy());
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
        return this.OP_TYPE;
    }
}
