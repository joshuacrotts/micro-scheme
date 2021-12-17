package com.joshuacrotts.minischeme.ast;

public class MSVariableNode extends MSSyntaxTree {

    /**
     *
     */
    private MSSyntaxTree expr;

    /**
     *
     */
    private String identifier;

    public MSVariableNode(String identifier, MSSyntaxTree expr) {
        this.identifier = identifier;
        this.expr = expr;
    }

    public String getStringRep() {
        return this.identifier + ": " + this.expr.getStringRep();
    }

    public String toString() {
        return "(VAR " + this.expr.toString() + ")";
    }
}
