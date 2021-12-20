package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public class MSStringLitNode extends MSSyntaxTree {

    /**
     *
     */
    private final String value;

    public MSStringLitNode(String value) {
        super(MSNodeType.STR);
        this.value = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSStringLitNode(this.value);
    }

    @Override
    public String getStringRep() {
        if (this.value.startsWith("\"")
            && this.value.endsWith("\"")
            && this.value.length() >= 2) {
            return this.value.substring(1, this.value.length() - 1);
        }
        return this.value;
    }

    @Override
    public String toString() {
        return "(STR " + this.value + ")";
    }

    public String getValue() {
        return this.value;
    }
}
