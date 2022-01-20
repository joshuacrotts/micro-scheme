package com.joshuacrotts.minischeme.ast;

/**
 * Defines an identifier in Scheme.
 *
 * @author Joshua Crotts
 * @version 01/19/2022
 */
public class MSVariableNode extends MSSyntaxTree {

    /**
     * Identifier used to reference this variable.
     */
    private final String IDENTIFIER;

    public MSVariableNode(final String id) {
        super(MSNodeType.VARIABLE);
        this.IDENTIFIER = id;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSVariableNode(this.IDENTIFIER);
    }

    @Override
    public String getStringRep() {
        return this.IDENTIFIER;
    }

    public String toString() {
        return "(VARIABLE " + this.IDENTIFIER + ")";
    }

    public String getIdentifier() {
        return this.IDENTIFIER;
    }
}
