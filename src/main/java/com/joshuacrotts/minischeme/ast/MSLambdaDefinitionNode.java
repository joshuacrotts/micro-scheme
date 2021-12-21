package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSLambdaDefinitionNode extends MSSyntaxTree {

    /**
     *
     */
    private final MSSyntaxTree body;

    /**
     *
     */
    private final ArrayList<MSSyntaxTree> parameters;

    /**
     *
     */
    private final MSSyntaxTree identifier;

    public MSLambdaDefinitionNode(ArrayList<MSSyntaxTree> parameters, MSSyntaxTree body) {
        super(MSNodeType.EXPR_LAMBDA_DECL);
        this.parameters = parameters;
        this.body = body;
        this.identifier = null;
        throw new UnsupportedOperationException("Lambdas not supported yet...");
    }

    public MSLambdaDefinitionNode(MSSyntaxTree identifier, ArrayList<MSSyntaxTree> parameters,
                                  MSSyntaxTree body) {
        super(MSNodeType.LAMBDA_DECL);
        this.identifier = identifier;
        this.parameters = parameters;
        this.body = body;
        throw new UnsupportedOperationException("Lambdas not supported yet...");
    }
}
