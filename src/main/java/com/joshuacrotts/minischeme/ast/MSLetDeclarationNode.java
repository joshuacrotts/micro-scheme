package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 *
 * @author Joshua Crotts
 * @version 12/30/2021
 */
public class MSLetDeclarationNode extends MSDeclaration {

    /**
     * Number of variables declared in this let block.
     */
    private int numDeclarations;

    public MSLetDeclarationNode(ArrayList<MSSyntaxTree> variableDeclarations,
                                MSSyntaxTree letBody) {
        super(MSNodeType.LET_DECL);
        this.numDeclarations = variableDeclarations.size();
        variableDeclarations.forEach(this::addChild);
        this.addChild(letBody);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> variableDeclarations = new ArrayList<>();
        for (int i = 0; i < this.numDeclarations; i++) {
            variableDeclarations.add(this.getChild(i).copy());
        }
        MSSyntaxTree letBodyCopy = this.getChild(this.getChildrenSize() - 1).copy();
        return new MSLetDeclarationNode(variableDeclarations, letBodyCopy);
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
