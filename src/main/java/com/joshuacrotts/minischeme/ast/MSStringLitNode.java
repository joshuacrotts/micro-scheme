package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public class MSStringLitNode extends MSSyntaxTree {

    /**
     *
     */
    private String value;

    public MSStringLitNode(String value) {
        super(MSNodeType.MS_STR);
        this.value = value;
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public MSSyntaxTree copy() {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public String getStringRep() {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Unsupported");
    }
}
