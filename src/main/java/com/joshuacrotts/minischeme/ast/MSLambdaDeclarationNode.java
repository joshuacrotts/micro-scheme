package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.parser.MSInterpreterException;

import java.util.ArrayList;

/**
 * @author Joshua Crotts
 * @version 12/23/2021
 */
public class MSLambdaDeclarationNode extends MSDeclaration implements Callable {

    /**
     * Number of parameters required to call this lambda.
     */
    private final int NUM_PARAMS;

    public MSLambdaDeclarationNode(final ArrayList<MSSyntaxTree> lambdaParams,
                                   final MSSyntaxTree lambdaBody) {
        super(MSNodeType.EXPR_LAMBDA_DECL);
        this.NUM_PARAMS = lambdaParams.size();
        for (int i = 0; i < this.NUM_PARAMS; i++) {
            this.addChild(lambdaParams.get(i));
        }
        this.addChild(lambdaBody);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> lambdaParamsCopy = new ArrayList<>();
        for (int i = 0; i < this.NUM_PARAMS; i++) {
            lambdaParamsCopy.add(this.getChild(i).copy());
        }

        return new MSLambdaDeclarationNode(lambdaParamsCopy, this.getChild(this.getChildrenSize() - 1).copy());
    }

    @Override
    public String getStringRep() {
        return this.getNodeType() + " " + this.getBody().getStringRep();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public ArrayList<MSSyntaxTree> getParameters() {
        ArrayList<MSSyntaxTree> lambdaParams = new ArrayList<>();
        for (int i = 0; i < this.NUM_PARAMS; i++) {
            lambdaParams.add(this.getChild(i));
        }
        return lambdaParams;
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }

    public int getArgumentIndex(String idStr) {
        for (int i = 0; i < this.NUM_PARAMS; i++) {
            MSIdentifierNode id = (MSIdentifierNode) this.getChild(i);
            if (id.getIdentifier().equals(idStr)) {
                return i;
            }
        }
        return -1;
    }

    public int getLambdaParameterCount() {
        return this.NUM_PARAMS;
    }
}
