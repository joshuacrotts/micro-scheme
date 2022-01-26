/******************************************************************************
 *  File: MSLambdaNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSLambdaNode extends MSSyntaxTree {

    /**
     *
     */
    private final int NUM_LAMBDA_PARAMETERS;

    public MSLambdaNode(ArrayList<MSSyntaxTree> lambdaParameters,
                        MSSyntaxTree lambdaBody) {
        super(MSNodeType.LAMBDA);
        this.NUM_LAMBDA_PARAMETERS = lambdaParameters.size();
        lambdaParameters.forEach(this::addChild);
        this.addChild(lambdaBody);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> lambdaParametersCopy = new ArrayList<>();
        for (int i = 0; i < this.NUM_LAMBDA_PARAMETERS; i++) {
            lambdaParametersCopy.add(this.getChild(i).copy());
        }
        MSSyntaxTree lambdaBodyCopy = this.getChild(this.getChildrenSize() - 1).copy();
        return new MSLambdaNode(lambdaParametersCopy, lambdaBodyCopy);
    }

    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        sb.append("(lambda");

        ArrayList<MSSyntaxTree> params =this.getLambdaParameters();
        if (!params.isEmpty()) {
            sb.append(" (");
            for (int i = 0; i < params.size() - 1; i++) {
                sb.append(params.get(i).getStringRep());
                sb.append(" ");
            }
            sb.append(params.get(params.size() - 1).getStringRep()).append(")");
        } else {
            sb.append(" ()");
        }
        sb.append(" ");
        sb.append(this.getLambdaBody().getStringRep());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public ArrayList<MSSyntaxTree> getLambdaParameters() {
        ArrayList<MSSyntaxTree> lambdaParameters = new ArrayList<>();
        for (int i = 0; i < this.NUM_LAMBDA_PARAMETERS; i++) {
            lambdaParameters.add(this.getChild(i));
        }
        return lambdaParameters;
    }

    public MSSyntaxTree getLambdaBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }
}
