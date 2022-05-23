/******************************************************************************
 *  File: MSDeclarationNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  A declaration consists of the variable and the expression to-be-evaluated.
 *  This variable is stored in the current environment.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

public final class MSDeclarationNode extends MSSyntaxTree {

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
