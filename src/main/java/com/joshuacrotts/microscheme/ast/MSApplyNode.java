/******************************************************************************
 *  File: MSApplyNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  MSApplyNode takes a procedure proc and a list of arguments l, and applies
 *  the procedure to the list, returning the applied list.
 *
 *  For example: (apply + '(1 2 3 4 5)) -> (2 3 4 5 6)
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

public final class MSApplyNode extends MSSyntaxTree {

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
