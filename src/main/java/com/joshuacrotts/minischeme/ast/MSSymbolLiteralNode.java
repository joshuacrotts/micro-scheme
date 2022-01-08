package com.joshuacrotts.minischeme.ast;

/**
 *
 * @author Joshua Crotts
 * @version ?
 */
public class MSSymbolLiteralNode extends MSSyntaxTree {

    /**
     * String associated with this node.
     */
    private final String value;

    public MSSymbolLiteralNode(final String value) {
        super(MSNodeType.SYMBOL_LIT);
        this.value = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSymbolLiteralNode(this.value);
    }

    @Override
    public String getStringRep() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }
}
