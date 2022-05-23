/******************************************************************************
 *  File: MSEvalNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  eval is a function similar to apply, except this takes an expression to evaluate
 *  and evaluates it!
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

public final class MSEvalNode extends MSSyntaxTree {

    public MSEvalNode(final MSSyntaxTree expression) {
        super(MSNodeType.EVAL, expression);
    }

    public MSSyntaxTree getExpression() {
        return this.getChild(0);
    }
}
