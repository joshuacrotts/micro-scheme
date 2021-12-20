package com.joshuacrotts.minischeme.ast;

/**
 * Defines an if expression node. An if expression has the condition, the expression to evaluate
 * when the condition is true, then the false expression.
 *
 * CHILD 0: if condition expression.
 * CHILD 1: expression to be evaluated when CHILD 0 is true.
 * CHILD 2: expression to be evaluated when CHILD 0 is false.
 *
 * @author Joshua Crotts
 * @version 12/20/2021
 */
public class MSIfNode extends MSSyntaxTree {

    public MSIfNode(MSSyntaxTree ifCond, MSSyntaxTree ifBody, MSSyntaxTree ifElse) {
        super(MSNodeType.IF);
        this.addChild(ifCond);
        this.addChild(ifBody);
        this.addChild(ifElse);
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree ifCondCopy = this.getChild(0).copy();
        MSSyntaxTree ifBodyCopy = this.getChild(1).copy();
        MSSyntaxTree ifElseCopy = this.getChild(2).copy();
        return new MSIfNode(ifCondCopy, ifBodyCopy, ifElseCopy);
    }

    @Override
    public String getStringRep() {
        return "(IF " + this.getChild(0).getStringRep()
            + " ? " + this.getChild(1).getStringRep()
            + " : " + this.getChild(2).getStringRep() + ")";
    }

    @Override
    public String toString() {
        return this.getStringRep();
    }
}
