package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 */
public class MSDoNode extends MSSyntaxTree {

    /**
     *
     */
    private final int numVarDeclarations;

    /**
     *
     */
    private final int numStepDeclarations;

    /**
     *
     */
    private final int numTrueExprs;

    public MSDoNode(final ArrayList<MSSyntaxTree> varDecls, final ArrayList<MSSyntaxTree> stepDecls,
                    final MSSyntaxTree testExpr, final ArrayList<MSSyntaxTree> trueExprs,
                    final MSSyntaxTree body) {
        super(MSNodeType.DO);
        this.numVarDeclarations = varDecls.size();
        this.numStepDeclarations = stepDecls.size();
        this.numTrueExprs = trueExprs.size();

        varDecls.forEach(this::addChild);
        stepDecls.forEach(this::addChild);
        this.addChild(testExpr);
        trueExprs.forEach(this::addChild);
        this.addChild(body);
    }

    @Override
    public MSSyntaxTree copy() {
        // Copy the variable declarations.
        ArrayList<MSSyntaxTree> varDeclsCopy = new ArrayList<>();
        for (int i = 0; i < this.numVarDeclarations; i++) {
            varDeclsCopy.add(this.getChild(i).copy());
        }

        // Copy the step declarations.
        ArrayList<MSSyntaxTree> stepDeclsCopy = new ArrayList<>();
        for (int i = 0; i < this.numStepDeclarations; i++) {
            stepDeclsCopy.add(this.getChild(i + this.numVarDeclarations).copy());
        }

        // Copy the test expression.
        MSSyntaxTree testExprCopy = this.getChild(this.numVarDeclarations + this.numStepDeclarations).copy();

        // Copy the true expressions.
        ArrayList<MSSyntaxTree> trueExpressions = new ArrayList<>();
        for (int i = 0; i < this.numTrueExprs; i++) {
            trueExpressions.add(this.getChild(i + this.numVarDeclarations + this.numStepDeclarations + 1).copy());
        }

        // Finally, copy the body.
        MSSyntaxTree bodyExpr = this.getChild(this.getChildrenSize() - 1).copy();
        return new MSDoNode(varDeclsCopy, stepDeclsCopy, testExprCopy, trueExpressions, bodyExpr);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public ArrayList<MSSyntaxTree> getVariableDeclarations() {
        ArrayList<MSSyntaxTree> varDecls = new ArrayList<>();
        for (int i = 0; i < this.numVarDeclarations; i++) {
            varDecls.add(this.getChild(i));
        }
        return varDecls;
    }

    public ArrayList<MSSyntaxTree> getStepDeclarations() {
        ArrayList<MSSyntaxTree> stepDecls = new ArrayList<>();
        for (int i = 0; i < this.numStepDeclarations; i++) {
            stepDecls.add(this.getChild(i + this.numVarDeclarations));
        }
        return stepDecls;
    }

    public MSSyntaxTree getTestExpression() {
        return this.getChild(this.numVarDeclarations + this.numStepDeclarations);
    }

    public ArrayList<MSSyntaxTree> getTrueExpressions() {
        ArrayList<MSSyntaxTree> trueExpressions = new ArrayList<>();
        for (int i = 0; i < this.numTrueExprs; i++) {
            trueExpressions.add(this.getChild(this.numVarDeclarations + this.numStepDeclarations + 1));
        }
        return trueExpressions;
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }
}
