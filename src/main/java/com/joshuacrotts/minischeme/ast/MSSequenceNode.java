package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSSequenceNode extends MSSyntaxTree {

    public MSSequenceNode(ArrayList<MSSyntaxTree> children) {
        super(MSNodeType.SEQ);
        children.forEach(this::addChild);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> childrenCopy = new ArrayList<>();
        for (MSSyntaxTree child : this.getChildren()) {
            childrenCopy.add(child.copy());
        }
        return new MSSequenceNode(childrenCopy);
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
