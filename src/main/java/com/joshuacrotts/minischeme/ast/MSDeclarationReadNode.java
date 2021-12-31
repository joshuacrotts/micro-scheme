package com.joshuacrotts.minischeme.ast;

/**
 * Represents a read from stdin. The type of data depends on what the user enters.
 *
 * CHILD 0: identifier of declaration to store result.
 *
 * @author Joshua Crotts
 * @version 12/31/2021
 */
public class MSDeclarationReadNode extends MSDeclaration {

    /**
     * Keeps track of what type of operator we're reading in from the user.
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
