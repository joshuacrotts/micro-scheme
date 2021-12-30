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
    private int numLambdaParams;

    /**
     * Number of arguments passed to this lambda.
     */
    private int numLambdaArgs;

    public MSLambdaDeclarationCallNode(ArrayList<MSSyntaxTree> lambdaParams,
                                       MSSyntaxTree lambdaBody,
                                       ArrayList<MSSyntaxTree> lambdaArgs) {
        super(MSNodeType.EXPR_LAMBDA_DECL_CALL);
        this.numLambdaParams = lambdaParams.size();
        this.numLambdaArgs = lambdaArgs.size();
        lambdaParams.forEach(this::addChild);
        this.addChild(lambdaBody);
        lambdaArgs.forEach(this::addChild);
    }

    public MSLambdaDeclarationCallNode(MSLambdaDeclarationNode declNode,
                                       MSCallNode callNode) {
        this(declNode.getLambdaParameters(), declNode.getBody(), callNode.getLambdaArguments());
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> paramsCopy = new ArrayList<>();
        ArrayList<MSSyntaxTree> argsCopy = new ArrayList<>();
        MSSyntaxTree bodyCopy = this.getLambdaBody().copy();

        for (int i = 0; i < this.numLambdaParams; i++) { paramsCopy.add(this.getChild(i).copy()); }
        for (int i = 0; i < this.numLambdaArgs; i++) { argsCopy.add(this.getChild(i + 1 + this.numLambdaParams).copy()); }

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
     *
     * @return
     */
    public MSSyntaxTree getLambdaBody() {
        return this.getChild(this.numLambdaParams);
    }

    /**
     *
     * @return
     */
    public ArrayList<MSSyntaxTree> getLambdaParameters() {
        ArrayList<MSSyntaxTree> lambdaParams = new ArrayList<MSSyntaxTree>();
        for (int i = 0; i < this.numLambdaParams; i++) {
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
        for (int i = 0; i < this.numLambdaArgs; i++) {
            lambdaArgs.add(this.getChild(i + 1 + this.numLambdaParams));
        }
        return lambdaArgs;
    }

    /**
     * @param idStr
     * @return
     */
    public int getArgumentIndex(String idStr) {
        for (int i = 0; i < this.numLambdaParams; i++) {
            MSIdentifierNode id = (MSIdentifierNode) this.getChild(i);
            if (id.getIdentifier().equals(idStr)) {
                return i;
            }
        }
        return -1;
    }
}
