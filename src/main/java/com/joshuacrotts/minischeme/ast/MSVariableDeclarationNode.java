package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public class MSVariableDeclarationNode extends MSDeclaration {

    public MSVariableDeclarationNode(MSSyntaxTree identifier, MSSyntaxTree expr) {
        super(MSNodeType.VAR_DECL);
        this.addChild(identifier);
        this.addChild(expr);
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSVariableDeclarationNode(this.getChild(0).copy(), this.getChild(1).copy());
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSIdentifierNode getIdentifier() {
        return (MSIdentifierNode) this.getChild(0);
    }

    public MSSyntaxTree getExpression() {
        return this.getChild(1);
    }
}
