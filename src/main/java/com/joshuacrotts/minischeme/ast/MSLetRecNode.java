/******************************************************************************
 *  File: MSLetRecNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/27/2022
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSLetRecNode extends MSSyntaxTree {

    /**
     * Number of declarations bound in this LetRec node.
     */
    private final int NUM_DECLARATIONS;

    public MSLetRecNode(ArrayList<MSSyntaxTree> declarationList, MSSyntaxTree body) {
        super(MSNodeType.LETREC);
        this.NUM_DECLARATIONS = declarationList.size();
        declarationList.forEach(this::addChild);
        this.addChild(body);
    }

    public ArrayList<MSSyntaxTree> getDeclarationList() {
        ArrayList<MSSyntaxTree> declarations = new ArrayList<>();
        for (int i = 0; i < this.NUM_DECLARATIONS; i++) {
            declarations.add(this.getChild(i));
        }
        return declarations;
    }

    public ArrayList<MSSyntaxTree> getVariableList() {
        ArrayList<MSSyntaxTree> variableList = new ArrayList<>();
        for (int i = 0; i < this.NUM_DECLARATIONS; i++) {
            variableList.add(this.getChild(i).getChild(0));
        }
        return variableList;
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }
}
