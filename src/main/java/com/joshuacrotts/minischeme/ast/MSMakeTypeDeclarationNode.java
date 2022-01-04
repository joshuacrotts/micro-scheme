package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 */
public class MSMakeTypeDeclarationNode extends MSSyntaxTree {

    /**
     *
     */
    private final int numArgs;

    public MSMakeTypeDeclarationNode(MSSyntaxTree typeId, MSSyntaxTree makeId,
                                     ArrayList<MSSyntaxTree> arguments) {
        super(MSNodeType.MAKETYPE_DECL);
        this.numArgs = arguments.size();
        this.addChild(typeId);
        this.addChild(makeId);
        arguments.forEach(this::addChild);
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree typeIdCopy = this.getChild(0).copy();
        MSSyntaxTree makeIdCopy = this.getChild(1).copy();
        ArrayList<MSSyntaxTree> argsCopy = new ArrayList<>();
        for (int i = 0; i < this.numArgs; i++) {
            argsCopy.add(this.getChild(i + 2).copy());
        }

        return new MSMakeTypeDeclarationNode(typeIdCopy, makeIdCopy, argsCopy);
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
