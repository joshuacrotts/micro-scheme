package com.joshuacrotts.microscheme.ast;

import java.util.ArrayList;

public class MSAndNode extends MSSyntaxTree {

    public MSAndNode(ArrayList<MSSyntaxTree> andArguments) {
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
