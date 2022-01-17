package com.joshuacrotts.minischeme.ast;

/**
 * @author Joshua Crotts
 * @version ?
 */
public class MSSymbolLiteralNode extends MSSyntaxTree {

    /**
     * String associated with this node.
     */
    private final String VALUE;

    public MSSymbolLiteralNode(final String value) {
        super(MSNodeType.SYMBOL_LIT);
        this.VALUE = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSymbolLiteralNode(this.VALUE);
    }

    @Override
    public String getStringRep() {
        return this.VALUE;
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }
}
