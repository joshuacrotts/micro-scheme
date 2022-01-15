package com.joshuacrotts.minischeme.ast;

/**
 *
 * @author Joshua Crotts
 * @version 01/10/2021
 */
public class MSCharacterNode extends MSSyntaxTree {

    /**
     * Char value associated with this character node.
     */
    private final char VALUE;

    public MSCharacterNode(String charInput) {
        super(MSNodeType.CHAR);
        this.VALUE = charInput.charAt(2);
    }

    public MSCharacterNode(char charInput) {
        super(MSNodeType.CHAR);
        this.VALUE = charInput;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSCharacterNode(this.VALUE);
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
