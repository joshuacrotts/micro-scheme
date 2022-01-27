/******************************************************************************
 *  File: MSCharacterNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

/**
 *
 * @author Joshua Crotts
 * @version 01/19/2022
 */
public class MSCharacterNode extends MSSyntaxTree {

    /**
     * Char value associated with this character node.
     */
    private final char VALUE;

    public MSCharacterNode(final String charInput) {
        super(MSNodeType.CHARACTER);
        this.VALUE = charInput.charAt(2);
    }

    public MSCharacterNode(final char charInput) {
        super(MSNodeType.CHARACTER);
        this.VALUE = charInput;
    }

    @Override
    public String getStringRep() {
        return String.valueOf(this.VALUE);
    }

    @Override
    public String toString() {
        return "(" + this.getNodeType().toString() + " " + this.VALUE + ")";
    }

    public char getValue() {
        return this.VALUE;
    }
}
