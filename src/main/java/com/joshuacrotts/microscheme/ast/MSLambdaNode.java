/******************************************************************************
 *  File: MSLambdaNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  MSLambdaNode is a procedure - it takes the form (lambda (<arg>)* <body>). Named
 *  procedures are syntactic sugar for lambdas. let and let* are also syntactic sugar
 *  for lambdas.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

import java.util.ArrayList;

public final class MSLambdaNode extends MSSyntaxTree {

    /**
     * Number of parameters that this lambda takes in.
     */
    private final int NUM_LAMBDA_PARAMETERS;

    /**
     * Do we use variable arguments for this procedure?
     */
    private final boolean VAR_ARGS;

    public MSLambdaNode(final ArrayList<MSSyntaxTree> lambdaParameters,
                        final MSSyntaxTree lambdaBody) {
        this(lambdaParameters, lambdaBody, false);
    }

    public MSLambdaNode(final ArrayList<MSSyntaxTree> lambdaParameters,
                        final MSSyntaxTree lambdaBody, final boolean isVarArguments) {
        super(MSNodeType.LAMBDA);
        this.VAR_ARGS = isVarArguments;
        this.NUM_LAMBDA_PARAMETERS = lambdaParameters.size();
        lambdaParameters.forEach(this::addChild);
        this.addChild(lambdaBody);
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

    public boolean isVariableArguments() {
        return this.VAR_ARGS;
    }
}
