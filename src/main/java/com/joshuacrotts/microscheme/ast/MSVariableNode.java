/******************************************************************************
 *  File: MSVariableNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

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
