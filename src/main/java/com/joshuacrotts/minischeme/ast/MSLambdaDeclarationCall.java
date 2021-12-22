package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 */
public class MSLambdaDeclarationCall extends MSSyntaxTree implements MSCallable {

    /**
     *
     */
    private int numLambdaParams;

    /**
     *
     */
    private int numLambdaArgs;

    public MSLambdaDeclarationCall(ArrayList<MSSyntaxTree> lambdaParams,
                                   MSSyntaxTree lambdaBody,
                                   ArrayList<MSSyntaxTree> lambdaArgs) {
        super(MSNodeType.EXPR_LAMBDA_DECL_CALL);
        this.numLambdaParams = lambdaParams.size();
        this.numLambdaArgs = lambdaArgs.size();

        for (int i = 0; i < this.numLambdaParams; i++) {
            this.addChild(lambdaParams.get(i));
        }

        this.addChild(lambdaBody);

        for (int i = 0; i < this.numLambdaArgs; i++) {
            this.addChild(lambdaArgs.get(i));
        }
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> paramsCopy = new ArrayList<>();
        ArrayList<MSSyntaxTree> argsCopy = new ArrayList<>();
        MSSyntaxTree bodyCopy = this.getLambdaBody().copy();

        for (int i = 0; i < this.numLambdaParams; i++) {
            paramsCopy.add(this.getChild(i).copy());
        }

        for (int i = 0; i < this.numLambdaArgs; i++) {
            argsCopy.add(this.getChild(i + 1 + this.numLambdaParams).copy());
        }

        return new MSLambdaDeclarationCall(paramsCopy, bodyCopy, argsCopy);
    }

    @Override
    public String getStringRep() {
        return "";
    }

    @Override
    public String toString() {
        return "LAMBDA_DECL_CALL";
    }

    public MSSyntaxTree getLambdaBody() {
        return this.getChild(this.numLambdaParams);
    }

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
    public int getArgumentLoc(String idStr) {
        // Offset to account for the identifier and body being children.
        for (int i = 0; i < this.numLambdaParams; i++) {
            MSIdentifierNode id = (MSIdentifierNode) this.getChild(i);
            if (id.getIdentifier().equals(idStr)) {
                return i;
            }
        }
        return -1;
    }
}
