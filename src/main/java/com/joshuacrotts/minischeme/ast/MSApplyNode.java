package com.joshuacrotts.minischeme.ast;

public class MSApplyNode extends MSSyntaxTree {

    public MSApplyNode(final MSSyntaxTree procedure, final MSSyntaxTree argumentList) {
        super(MSNodeType.APPLY, procedure, argumentList);
    }

    public MSSyntaxTree getProcedure() {
        return this.getChild(0);
    }

    public MSSyntaxTree getArgumentList() {
        return this.getChild(1);
    }
}
