package com.joshuacrotts.minischeme.ast;

/**
 * Defines an identifier in Scheme. An identifier, in this case,
 * just represents the label of a variable or a procedure. The actual
 * data associated with either of these structures is in their
 * separate respective classes.
 */
public class MSIdentifierNode extends MSSyntaxTree {

    /**
     * Identifier to use.
     */
    private final String id;

    public MSIdentifierNode(String id) {
        super (MSNodeType.ID);
        this.id = id;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSIdentifierNode(this.id);
    }

    public String getIdentifier() {
        return this.id;
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
