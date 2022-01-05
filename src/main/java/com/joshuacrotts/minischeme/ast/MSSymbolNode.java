package com.joshuacrotts.minischeme.ast;

/**
 * A symbol is an expression that evaluates to an identifier. Thus, this node cannot be accurately
 * used without interpreting the child.
 *
 * @CHILD 0: expression that we want to say is a symbol.
 */
public class MSSymbolNode extends MSSyntaxTree {

    public MSSymbolNode(MSSyntaxTree expr) {
        super(MSNodeType.SYMBOL, expr);
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSymbolNode(this.getChild(0).copy());
    }

    @Override
    public String getStringRep() {
        return this.getExpression().getStringRep();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getExpression() {
        return this.getChild(0);
    }
}
