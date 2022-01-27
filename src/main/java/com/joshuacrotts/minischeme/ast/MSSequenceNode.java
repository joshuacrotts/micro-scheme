/******************************************************************************
 *  File: MSSequenceNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

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
