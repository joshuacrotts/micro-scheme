/******************************************************************************
 *  File: MSVariableNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  A variable is just an identifier.
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
