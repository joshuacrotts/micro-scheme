package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 */
public class MSDoNode extends MSSyntaxTree {

    /**
     *
     */
    private final int NUM_VAR_DECLARATIONS;

    /**
     *
     */
    private final int NUM_STEP_DECLARATIONS;

    /**
     *
     */
    private final int NUM_TRUE_EXPRESSIONS;

    public MSDoNode(final ArrayList<MSSyntaxTree> varDecls, final ArrayList<MSSyntaxTree> stepDecls,
                    final MSSyntaxTree testExpr, final ArrayList<MSSyntaxTree> trueExprs,
                    final MSSyntaxTree body) {
        super(MSNodeType.DO);
        this.NUM_VAR_DECLARATIONS = varDecls.size();
        this.NUM_STEP_DECLARATIONS = stepDecls.size();
        this.NUM_TRUE_EXPRESSIONS = trueExprs.size();

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
        for (int i = 0; i < this.NUM_VAR_DECLARATIONS; i++) {
            varDeclsCopy.add(this.getChild(i).copy());
        }

        // Copy the step declarations.
        ArrayList<MSSyntaxTree> stepDeclsCopy = new ArrayList<>();
        for (int i = 0; i < this.NUM_STEP_DECLARATIONS; i++) {
            stepDeclsCopy.add(this.getChild(i + this.NUM_VAR_DECLARATIONS).copy());
        }

        // Copy the test expression.
        MSSyntaxTree testExprCopy = this.getChild(this.NUM_VAR_DECLARATIONS + this.NUM_STEP_DECLARATIONS).copy();

        // Copy the true expressions.
        ArrayList<MSSyntaxTree> trueExpressions = new ArrayList<>();
        for (int i = 0; i < this.NUM_TRUE_EXPRESSIONS; i++) {
            trueExpressions.add(this.getChild(i + this.NUM_VAR_DECLARATIONS + this.NUM_STEP_DECLARATIONS + 1).copy());
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
        for (int i = 0; i < this.NUM_VAR_DECLARATIONS; i++) {
            varDecls.add(this.getChild(i));
        }
        return varDecls;
    }

    public ArrayList<MSSyntaxTree> getStepDeclarations() {
        ArrayList<MSSyntaxTree> stepDecls = new ArrayList<>();
        for (int i = 0; i < this.NUM_STEP_DECLARATIONS; i++) {
            stepDecls.add(this.getChild(i + this.NUM_VAR_DECLARATIONS));
        }
        return stepDecls;
    }

    public MSSyntaxTree getTestExpression() {
        return this.getChild(this.NUM_VAR_DECLARATIONS + this.NUM_STEP_DECLARATIONS);
    }

    public ArrayList<MSSyntaxTree> getTrueExpressions() {
        ArrayList<MSSyntaxTree> trueExpressions = new ArrayList<>();
        for (int i = 0; i < this.NUM_TRUE_EXPRESSIONS; i++) {
            trueExpressions.add(this.getChild(this.NUM_VAR_DECLARATIONS + this.NUM_STEP_DECLARATIONS + 1));
        }
        return trueExpressions;
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }
}
