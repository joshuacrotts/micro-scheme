package com.joshuacrotts.minischeme.ast;

/**
 * A symbol is an expression that evaluates to an identifier. Thus, this node cannot be accurately
 * used without interpreting the child.
 *
 * @CHILD 0: expression that we want to say is a symbol.
 *
 * @author Joshua Crotts
 *
 * @version 01/15/2022
 */
public class MSSymbolNode extends MSSyntaxTree {

    public MSSymbolNode(final MSSyntaxTree expr) {
        super(MSNodeType.SYMBOL, expr);
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSymbolNode(this.getChild(0).copy());
    }

    @Override
    public String getStringRep() {
        MSNodeType exprType = this.getExpression().getNodeType();
        // If it's a list/pair we don't need the quote. Otherwise, we do.
        if (exprType == MSNodeType.LIST) {
            return this.getExpression().getStringRep();
        }

        // The quote in this case is PURELY decorative, so to speak.
        // If it were evaluated, we could simply evaluate the SymbolNode.
        return "'" + this.getExpression().getStringRep();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getExpression() {
        return this.getChild(0);
    }
}
