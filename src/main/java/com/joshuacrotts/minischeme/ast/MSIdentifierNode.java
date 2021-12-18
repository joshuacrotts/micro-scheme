package com.joshuacrotts.minischeme.ast;

public class MSIdentifierNode extends MSSyntaxTree {

    /**
     *
     */
    private final String id;

    public MSIdentifierNode(String id) {
        super (MSNodeType.MS_ID);
        this.id = id;
    }

    @Override
    public String getStringRep() {
        return this.id;
    }

    @Override
    public String toString() {
        return "(ID " + this.id + ")";
    }
}
