package com.joshuacrotts.minischeme.ast;

/**
 * Defines an identifier in Scheme. An identifier, in this case, just represents the label of a
 * variable or a procedure. The actual data associated with either of these structures is in their
 * separate respective classes.
 *
 * @author Joshua Crotts
 *
 * @version 12/23/2021
 */
public class MSIdentifierNode extends MSSyntaxTree {

    /**
     * Identifier to use.
     */
    private final String IDENTIFIER;

    public MSIdentifierNode(final String id) {
        super(MSNodeType.ID);
        this.IDENTIFIER = id;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSIdentifierNode(this.IDENTIFIER);
    }

    @Override
    public String getStringRep() {
        return this.IDENTIFIER;
    }

    @Override
    public String toString() {
        return "(ID " + this.IDENTIFIER + ")";
    }

    public String getIdentifier() {
        return this.IDENTIFIER;
    }
}
