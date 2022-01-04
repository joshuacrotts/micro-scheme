package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 */
public class MSTypeDeclarationNode extends MSDeclaration {

    /**
     *
     */
    private final int numParams;

    public MSTypeDeclarationNode(MSSyntaxTree typeId,
                                 ArrayList<MSSyntaxTree> typeParams) {
        super(MSNodeType.TYPE_DECL);
        this.numParams = typeParams.size();
        this.addChild(typeId);
        typeParams.forEach(this::addChild);
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree idCopy = this.getChild(0).copy();
        ArrayList<MSSyntaxTree> paramsCopy = new ArrayList<>();
        for (int i = 0; i < this.numParams; i++) {
            paramsCopy.add(this.getChild(i + 1).copy());
        }
        return new MSTypeDeclarationNode(idCopy, paramsCopy);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public int getParameterCount() {
        return this.numParams;
    }

    public MSIdentifierNode getTypeId() {
        return (MSIdentifierNode) this.getChild(0);
    }

    public ArrayList<MSSyntaxTree> getTypeParameters() {
        ArrayList<MSSyntaxTree> typeParams = new ArrayList<>();
        for (int i = 0; i < this.numParams; i++) {
            typeParams.add(this.getChild(i + 1));
        }
        return typeParams;
    }
}
