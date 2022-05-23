/******************************************************************************
 *  File: MSOrNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  MSOrNodes are constructed when the "or" keyword is used. It uses
 *  short-circuiting wherein if a condition is true, the rest are not evaluated.
 *  If all the expressions are false then it is false, true otherwise.
 *  Ex. (or ...)
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

import java.util.ArrayList;

public final class MSOrNode extends MSSyntaxTree {

    public MSOrNode(final ArrayList<MSSyntaxTree> andArguments) {
        super(MSNodeType.OR);
        andArguments.forEach(this::addChild);
    }

    @Override
    public String getStringRep() {
        return this.getStringNodeType();
    }

    @Override
    public String toString() {
        return this.getStringNodeType();
    }
}
