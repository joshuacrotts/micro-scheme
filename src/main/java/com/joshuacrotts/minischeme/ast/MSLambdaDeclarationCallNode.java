package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 *
 * @author Joshua Crotts
 * @version 12/23/2021
 */
public class MSLambdaDeclarationCallNode extends MSSyntaxTree implements Callable {

    /**
     * Number of parameters required to call this lambda.
     */
    private final int NUM_LAMBDA_PARAMS;

    /**
     * Number of arguments passed to this lambda.
     */
    private final int NUM_LAMBDA_ARGS;

    public MSLambdaDeclarationCallNode(final ArrayList<MSSyntaxTree> lambdaParams,
                                       final MSSyntaxTree lambdaBody,
                                       final ArrayList<MSSyntaxTree> lambdaArgs) {
        super(MSNodeType.EXPR_LAMBDA_DECL_CALL);
        this.NUM_LAMBDA_PARAMS = lambdaParams.size();
        this.NUM_LAMBDA_ARGS = lambdaArgs.size();
        lambdaParams.forEach(this::addChild);
        this.addChild(lambdaBody);
        lambdaArgs.forEach(this::addChild);
    }

    public MSLambdaDeclarationCallNode(final MSLambdaDeclarationNode declNode,
                                       final MSCallNode callNode) {
        this(declNode.getLambdaParameters(), declNode.getBody(), callNode.getLambdaArguments());
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> paramsCopy = new ArrayList<>();
        ArrayList<MSSyntaxTree> argsCopy = new ArrayList<>();
        MSSyntaxTree bodyCopy = this.getBody().copy();

        for (int i = 0; i < this.NUM_LAMBDA_PARAMS; i++) { paramsCopy.add(this.getChild(i).copy()); }
        for (int i = 0; i < this.NUM_LAMBDA_ARGS; i++) { argsCopy.add(this.getChild(i + 1 + this.NUM_LAMBDA_PARAMS).copy()); }

        return new MSLambdaDeclarationCallNode(paramsCopy, bodyCopy, argsCopy);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    /**
     * @param idStr
     * @return
     */
    public int getArgumentIndex(String idStr) {
        for (int i = 0; i < this.NUM_LAMBDA_PARAMS; i++) {
            MSIdentifierNode id = (MSIdentifierNode) this.getChild(i);
            if (id.getIdentifier().equals(idStr)) {
                return i;
            }
        }
        return -1;
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.NUM_LAMBDA_PARAMS);
    }

    public ArrayList<MSSyntaxTree> getLambdaParameters() {
        ArrayList<MSSyntaxTree> lambdaParams = new ArrayList<MSSyntaxTree>();
        for (int i = 0; i < this.NUM_LAMBDA_PARAMS; i++) {
            lambdaParams.add(this.getChild(i));
        }
        return lambdaParams;
    }

    /**
     *
     * @return
     */
    public ArrayList<MSSyntaxTree> getLambdaArguments() {
        ArrayList<MSSyntaxTree> lambdaArgs = new ArrayList<MSSyntaxTree>();
        for (int i = 0; i < this.NUM_LAMBDA_ARGS; i++) {
            lambdaArgs.add(this.getChild(i + 1 + this.NUM_LAMBDA_PARAMS));
        }
        return lambdaArgs;
    }

    public int getLambdaParameterCount() {
        return this.NUM_LAMBDA_PARAMS;
    }

    public int getLambdaArgumentCount() {
        return this.NUM_LAMBDA_ARGS;
    }
}
