/******************************************************************************
 *  File: MSAndNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  MSAndNodes are constructed when the "and" keyword is used. It uses
 *  short-circuiting wherein if a condition is false, the rest are not evaluated.
 *  If all the expressions are true then it is true, false otherwise.
 *  Ex. (and ...)
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

import java.util.ArrayList;

public final class MSAndNode extends MSSyntaxTree {

    public MSAndNode(final ArrayList<MSSyntaxTree> andArguments) {
        super(MSNodeType.AND);
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
