package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSLambdaDeclarationCallNode extends MSSyntaxTree {

    /**
     *
     */
    private int numParams;

    /**
     *
     */
    private int numArgs;

    public MSLambdaDeclarationCallNode(ArrayList<MSSyntaxTree> lambdaParams,
                                       MSSyntaxTree lambdaBody,
                                       ArrayList<MSSyntaxTree> lambdaArgs) {
        super(MSNodeType.EXPR_LAMBDA_DECL_CALL);
        this.numParams = lambdaParams.size();
        this.numArgs = lambdaArgs.size();
        // First add the params.
        for (int i = 0; i < this.numParams; i++) { this.addChild(lambdaParams.get(i)); }

        // Then add the body.
        this.addChild(lambdaBody);

        // Finally, add the arguments to the called lambda.
        for (int i = 0; i < this.numArgs; i++) { this.addChild(lambdaArgs.get(i)); }
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.numParams);
    }

    public ArrayList<MSSyntaxTree> getParameters() {
        throw new UnsupportedOperationException("Cannot support lambda declaration call nodes yet");
    }

    public ArrayList<MSSyntaxTree> getArguments() {
        throw new UnsupportedOperationException("Cannot support lambda declaration call nodes yet");
    }

    @Override
    public MSSyntaxTree copy() {
        throw new UnsupportedOperationException("Cannot support lambda declaration call nodes yet");
    }

    @Override
    public String getStringRep() {
        throw new UnsupportedOperationException("Cannot support lambda declaration call nodes yet");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Cannot support lambda declaration call nodes yet");
    }
}
