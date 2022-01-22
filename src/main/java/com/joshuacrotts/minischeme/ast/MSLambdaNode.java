package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.main.Environment;

import java.util.ArrayList;

/**
 *
 * @author Joshua Crotts
 * @version 01/20/2022
 */
public class MSLambdaNode extends MSSyntaxTree {

    /**
     *
     */
    private final int NUM_LAMBDA_PARAMETERS;

    /**
     *
     */
    private Environment closureEnvironment;

    /**
     *
     */
    private boolean isClosure;

    public MSLambdaNode(ArrayList<MSSyntaxTree> lambdaParameters,
                        MSSyntaxTree lambdaBody) {
        super(MSNodeType.LAMBDA);
        this.closureEnvironment = new Environment();
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
        return this.getNodeType().toString();
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

    public Environment getClosureEnvironment() { return this.closureEnvironment; }

    public void setClosureEnvironment(final Environment closureEnvironment) {
        this.closureEnvironment = closureEnvironment;
    }

}
