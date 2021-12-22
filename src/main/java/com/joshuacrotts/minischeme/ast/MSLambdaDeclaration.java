package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSLambdaDeclaration extends MSSyntaxTree {

    /**
     *
     */
    private int numParams;

    public MSLambdaDeclaration(ArrayList<MSSyntaxTree> lambdaParams,
                               MSSyntaxTree lambdaBody) {
        super(MSNodeType.EXPR_LAMBDA_DECL);
        this.numParams = lambdaParams.size();
        for (int i = 0; i < this.numParams; i++) {
            this.addChild(lambdaParams.get(i));
        }
        this.addChild(lambdaBody);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> lambdaParamsCopy = new ArrayList<>();
        for (int i = 0; i < this.numParams; i++) {
            lambdaParamsCopy.add(this.getChild(i).copy());
        }
        MSSyntaxTree bodyCopy = this.getChild(this.getChildrenSize() - 1).copy();
        return new MSLambdaDeclaration(lambdaParamsCopy, bodyCopy);
    }

    @Override
    public String getStringRep() {
        return "LAMBDA_DECL " + this.getLambdaBody().getStringRep();
    }

    @Override
    public String toString() {
        return "LAMBDA_DECL";
    }

    public ArrayList<MSSyntaxTree> getLambdaParameters() {
        ArrayList<MSSyntaxTree> lambdaParams = new ArrayList<>();
        for (int i = 0; i < this.numParams; i++) {
            lambdaParams.add(this.getChild(i));
        }
        return lambdaParams;
    }

    public MSSyntaxTree getLambdaBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }
}
