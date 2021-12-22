package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSLambdaCallNode extends MSSyntaxTree {

    public MSLambdaCallNode(MSSyntaxTree identifier, ArrayList<MSSyntaxTree> procArgs, ArrayList<MSSyntaxTree> args) {
        super(MSNodeType.EXPR_LAMBDA_CALL);
        throw new UnsupportedOperationException("Lambdas not supported yet...");
    }

    @Override
    public MSSyntaxTree copy() {
        return null;
    }

    @Override
    public String getStringRep() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
