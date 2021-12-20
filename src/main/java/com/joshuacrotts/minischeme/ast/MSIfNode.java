package com.joshuacrotts.minischeme.ast;

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
