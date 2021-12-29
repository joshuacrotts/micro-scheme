package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public class MSDeclarationReadNode extends MSDeclaration {

    /**
     *
     */
    private int opType;

    public MSDeclarationReadNode(int opType, MSSyntaxTree identifier) {
        super(MSNodeType.DECL_READ);
        this.addChild(identifier);
        this.opType = opType;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSDeclarationReadNode(this.opType, this.getChild(0).copy());
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
