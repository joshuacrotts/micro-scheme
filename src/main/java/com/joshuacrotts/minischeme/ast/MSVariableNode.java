package com.joshuacrotts.minischeme.ast;

public class MSVariableNode extends MSSyntaxTree {

    public MSVariableNode(MSSyntaxTree identifier, MSSyntaxTree expr) {
        super(MSNodeType.MS_VAR);
        this.addChild(identifier);
        this.addChild(expr);
    }

    public String getStringRep() {
        return this.getChild(0).getStringRep() + ": " + this.getChild(1).getStringRep();
    }

    public String toString() {
        return "(VAR " + this.getChild(0).toString() + ": " + this.getChild(1).toString() + ")";
    }
}
