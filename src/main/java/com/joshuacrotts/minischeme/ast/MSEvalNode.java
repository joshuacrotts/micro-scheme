package com.joshuacrotts.minischeme.ast;

public class MSEvalNode extends MSSyntaxTree {

    public MSEvalNode(final MSSyntaxTree expression) {
        super(MSNodeType.EVAL, expression);
    }

    public MSSyntaxTree getExpression() {
        return this.getChild(0);
    }
}
