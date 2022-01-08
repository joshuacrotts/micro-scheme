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

    public MSIfNode(final MSSyntaxTree ifCond, final MSSyntaxTree ifBody, final MSSyntaxTree ifElse) {
        super(MSNodeType.IF);
        this.addChild(ifCond);
        this.addChild(ifBody);
        this.addChild(ifElse);
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree ifCondCopy = this.getCondition().copy();
        MSSyntaxTree ifBodyCopy = this.getConsequent().copy();
        MSSyntaxTree ifElseCopy = this.getAlternative().copy();
        return new MSIfNode(ifCondCopy, ifBodyCopy, ifElseCopy);
    }

    @Override
    public String getStringRep() {
        return "(IF " + this.getCondition().getStringRep()
            + " ? " + this.getConsequent().getStringRep()
            + " : " + this.getAlternative().getStringRep() + ")";
    }

    public MSSyntaxTree getCondition() {
        return this.getChild(0);
    }

    public MSSyntaxTree getConsequent() {
        return this.getChild(1);
    }

    public MSSyntaxTree getAlternative() {
        return this.getChild(2);
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }
}
