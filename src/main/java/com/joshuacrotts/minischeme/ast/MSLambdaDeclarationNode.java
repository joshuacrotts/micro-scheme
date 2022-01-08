package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 *
 * @author Joshua Crotts
 * @version 12/23/2021
 */
public class MSLambdaDeclarationNode extends MSDeclaration {

    /**
     * Number of parameters required to call this lambda.
     */
    private int numParams;

    /**
     * If this lambda is bound to an identifier, it is not anonymous.
     */
    private final boolean isAnonymous;

    public MSLambdaDeclarationNode(ArrayList<MSSyntaxTree> lambdaParams,
                                   MSSyntaxTree lambdaBody) {
        super(MSNodeType.EXPR_LAMBDA_DECL);
        this.isAnonymous = true;
        this.numParams = lambdaParams.size();
        for (int i = 0; i < this.numParams; i++) {
            this.addChild(lambdaParams.get(i));
        }
        this.addChild(lambdaBody);
    }

    public MSLambdaDeclarationNode(MSSyntaxTree id, ArrayList<MSSyntaxTree> lambdaParams,
                                   MSSyntaxTree lambdaBody) {
        super(MSNodeType.LAMBDA_DECL);
        this.isAnonymous = false;
        this.addChild(id);
        this.numParams = lambdaParams.size();
        for (int i = 0; i < this.numParams; i++) {
            this.addChild(lambdaParams.get(i));
        }
        this.addChild(lambdaBody);
    }

    /**
     * Creates a non-anonymous lambda from an anonymous lambda expression.
     * This is useful for binding a lambda to an identifier in a let declaration.
     *
     * @param id - identifier to bind lambda to.
     * @param expr - anonymous MSLambdaDeclarationNode ast.
     *
     * @return new MSLambdaDeclarationNode with bound identifier.
     */
    public static MSLambdaDeclarationNode createNonAnonymous(MSSyntaxTree id, MSLambdaDeclarationNode expr) {
        if (!expr.isAnonymous()) {
            throw new IllegalArgumentException("Internal interpreter error - this lambda is already anonymous!");
        }

        return new MSLambdaDeclarationNode(id, expr.getLambdaParameters(), expr.getBody());
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> lambdaParamsCopy = new ArrayList<>();
        int offset = this.isAnonymous ? 0 : 1;
        for (int i = offset; i < this.numParams + offset; i++) {
            lambdaParamsCopy.add(this.getChild(i).copy());
        }

        return offset == 1 ? new MSLambdaDeclarationNode(this.getChild(0).copy(),
                                lambdaParamsCopy, this.getChild(this.getChildrenSize() - 1).copy())
                           : new MSLambdaDeclarationNode(lambdaParamsCopy, this.getChild(this.getChildrenSize() - 1).copy());
    }

    @Override
    public String getStringRep() {
        return this.getNodeType() + " " + this.getBody().getStringRep();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public ArrayList<MSSyntaxTree> getLambdaParameters() {
        ArrayList<MSSyntaxTree> lambdaParams = new ArrayList<>();
        int offset = this.isAnonymous ? 0 : 1;
        for (int i = offset; i < this.numParams + offset; i++) {
            lambdaParams.add(this.getChild(i));
        }
        return lambdaParams;
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }

    public MSIdentifierNode getIdentifier() {
        if (!this.isAnonymous) {
            return (MSIdentifierNode) this.getChild(0);
        }
        throw new IllegalStateException("ERR lambda not bound to identifier");
    }

    public int getLambdaParameterCount() {
        return this.numParams;
    }

    public boolean isAnonymous() {
        return this.isAnonymous;
    }
}
