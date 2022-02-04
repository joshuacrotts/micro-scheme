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

package com.joshuacrotts.microscheme.ast;

public class MSDeclarationNode extends MSSyntaxTree {

    public MSDeclarationNode(final MSSyntaxTree variable, final MSSyntaxTree expr) {
        super(MSNodeType.DECLARATION, variable, expr);
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
