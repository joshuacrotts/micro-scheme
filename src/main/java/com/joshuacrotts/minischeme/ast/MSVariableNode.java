package com.joshuacrotts.minischeme.ast;

/**
 * Defines a Scheme variable. Variables have two components: the identifier (i.e., the name of the
 * variable), and the expression which that variable represents.
 *
 * CHILD 0: identifier of variable.
 * CHILD 1: expression that binds to the variable.
 */
public class MSVariableNode extends MSSyntaxTree {

    public MSVariableNode(MSSyntaxTree identifier, MSSyntaxTree expr) {
        super(MSNodeType.VAR);
        this.addChild(identifier);
        this.addChild(expr);
    }

    private MSVariableNode() {
        super(MSNodeType.VAR);
    }

    @Override
    public MSSyntaxTree copy() {
        MSVariableNode varCopy = new MSVariableNode();
        for (MSSyntaxTree ch : this.getChildren()) {
            varCopy.addChild(ch.copy());
        }
        return varCopy;
    }

    @Override
    public String getStringRep() {
        return this.getChild(0).getStringRep() + ": " + this.getChild(1).getStringRep();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }
}
