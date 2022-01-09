package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 */
public class MSDoNode extends MSSyntaxTree {

    /**
     *
     */
    private final int numDeclarations;

    /**
     *
     */
    private final int numStepDeclarations;

    public MSDoNode(final ArrayList<MSSyntaxTree> declarations, final ArrayList<MSSyntaxTree> stepDeclarations,
                    final MSSyntaxTree doTest, final MSSyntaxTree doBody) {
        super(MSNodeType.DO);
        this.numDeclarations = declarations.size();
        this.numStepDeclarations = stepDeclarations.size();
        declarations.forEach(this::addChild);
        stepDeclarations.forEach(this::addChild);
        this.addChild(doTest);
        this.addChild(doBody);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> declarationsCopy = new ArrayList<>();
        for (int i = 0; i < this.numDeclarations; i++) {
            declarationsCopy.add(this.getChild(i).copy());
        }
        ArrayList<MSSyntaxTree> stepDeclarationsCopy = new ArrayList<>();
        for (int i = 0; i < this.numStepDeclarations; i++) {
            stepDeclarationsCopy.add(this.getChild(i + this.numDeclarations).copy());
        }

        MSSyntaxTree doTestCopy = this.getChild(this.numDeclarations + this.numStepDeclarations).copy();
        MSSyntaxTree doBodyCopy = this.getChild(this.numDeclarations + this.numStepDeclarations + 1).copy();
        return new MSDoNode(declarationsCopy, stepDeclarationsCopy, doTestCopy, doBodyCopy);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public ArrayList<MSSyntaxTree> getDeclarations() {
        ArrayList<MSSyntaxTree> declarationsCopy = new ArrayList<>();
        for (int i = 0; i < this.numDeclarations; i++) {
            declarationsCopy.add(this.getChild(i));
        }
        return declarationsCopy;
    }

    public ArrayList<MSSyntaxTree> getStepDeclarations() {
        ArrayList<MSSyntaxTree> stepDeclarationsCopy = new ArrayList<>();
        for (int i = 0; i < this.numStepDeclarations; i++) {
            stepDeclarationsCopy.add(this.getChild(i + this.numDeclarations));
        }
        return stepDeclarationsCopy;
    }

    public MSSyntaxTree getTest() {
        return this.getChild(this.getChildrenSize() - 2);
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }
}
