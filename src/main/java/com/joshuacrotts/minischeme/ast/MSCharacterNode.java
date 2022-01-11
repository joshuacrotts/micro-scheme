package com.joshuacrotts.minischeme.ast;

/**
 *
 * @author Joshua Crotts
 * @version 01/10/2021
 */
public class MSCharacterNode extends MSSyntaxTree {

    /**
     *
     */
    private final char value;

    public MSCharacterNode(String charInput) {
        super(MSNodeType.CHAR);
        this.value = charInput.charAt(2);
    }

    public MSCharacterNode(char charInput) {
        super(MSNodeType.CHAR);
        this.value = charInput;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSCharacterNode(this.value);
    }

    @Override
    public String getStringRep() {
        return String.valueOf(this.value);
    }

    @Override
    public String toString() {
        return "(" + this.getNodeType().toString() + " " + this.value + ")";
    }

    public char getValue() {
        return this.value;
    }
}
