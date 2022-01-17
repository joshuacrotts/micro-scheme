package com.joshuacrotts.minischeme.ast;

/**
 * Represents a variable declaration. Variable declarations occur in two
 * places: in (define ...) expressions, and (let ...) expressions.
 * <p>
 * CHILD 0: identifier of variable.
 * CHILD 1: expression of variable.
 *
 * @author Joshua Crotts
 * @version 12/31/2021
 */
public class MSVariableDeclarationNode extends MSDeclaration {

    public MSVariableDeclarationNode(final MSSyntaxTree identifier, final MSSyntaxTree expr) {
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
