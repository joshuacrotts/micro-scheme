/******************************************************************************
 *  File: MSCharacterNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  MSCharacterNodes are single characters designated via a pound/octothorpe/hashtag and a backslash.
 *  Example: #\a is the literal 'a'.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

public final class MSCharacterNode extends MSSyntaxTree {

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
