package com.joshuacrotts.minischeme.ast;

public class MSDeclaration extends MSSyntaxTree {

    public MSDeclaration(MSSyntaxTree variable, MSSyntaxTree expr) {
        super(MSNodeType.DECLARATION, variable, expr);
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSDeclaration(this.getVariable().copy(), this.getExpression().copy());
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getVariable() {
        return this.getChild(0);
    }

    public MSSyntaxTree getExpression() {
        return this.getChild(1);
    }
}
