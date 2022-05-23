/******************************************************************************
 *  File: MSSequenceNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  SequenceNodes are simply a collection of nodes to evaluate.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

import java.util.ArrayList;

public class MSSequenceNode extends MSSyntaxTree {

    public MSSequenceNode(final ArrayList<MSSyntaxTree> children) {
        super(MSNodeType.SEQUENCE);
        children.forEach(this::addChild);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }
}
