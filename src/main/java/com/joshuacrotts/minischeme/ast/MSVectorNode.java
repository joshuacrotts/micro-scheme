package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSVectorNode extends MSSyntaxTree {

    public MSVectorNode(ArrayList<MSSyntaxTree> elements) {
        super(MSNodeType.VECTOR);
        elements.forEach(this::addChild);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> elementsCopy = new ArrayList<>();
        for (int i = 0; i < this.getChildrenSize(); i++) {
            elementsCopy.add(this.getChild(i).copy());
        }
        return new MSVectorNode(elementsCopy);
    }

    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder("#(");
        for (int i = 0; i < this.getChildrenSize() - 1; i++) {
            sb.append(this.getChild(i).getStringRep());
            sb.append(" ");
        }
        sb.append(this.getChild(this.getChildrenSize() - 1).getStringRep());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }
}
