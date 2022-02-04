package com.joshuacrotts.microscheme.ast;

import java.util.ArrayList;

public class MSOrNode extends MSSyntaxTree {

    public MSOrNode(ArrayList<MSSyntaxTree> andArguments) {
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
