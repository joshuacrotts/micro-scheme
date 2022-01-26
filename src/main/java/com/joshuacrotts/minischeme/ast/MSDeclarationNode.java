/******************************************************************************
 *  File: MSDeclarationNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

public class MSDeclarationNode extends MSSyntaxTree {

    public MSDeclarationNode(MSSyntaxTree variable, MSSyntaxTree expr) {
        super(MSNodeType.DECLARATION, variable, expr);
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSDeclarationNode(this.getVariable().copy(), this.getExpression().copy());
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
