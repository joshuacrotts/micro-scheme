package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSLambdaCall extends MSSyntaxTree {

    /**
     *
     */
    private int numProcArgs;

    /**
     *
     */
    private int numLambdaArgs;

    public MSLambdaCall(MSSyntaxTree procIdentifier,
                         ArrayList<MSSyntaxTree> procArgs,
                         ArrayList<MSSyntaxTree> lambdaArgs) {
        super(MSNodeType.EXPR_LAMBDA_CALL);
        this.addChild(procIdentifier);
        this.numProcArgs = procArgs.size();
        this.numLambdaArgs = lambdaArgs.size();

        for (int i = 0; i < this.numProcArgs; i++) {
            this.addChild(procArgs.get(i));
        }

        for (int i = 0; i < this.numLambdaArgs; i++) {
            this.addChild(lambdaArgs.get(i));
        }
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree idCopy = this.getChild(0).copy();
        ArrayList<MSSyntaxTree> procArgsCopy = new ArrayList<>();
        for (int i = 0; i < this.numProcArgs; i++) {
            procArgsCopy.add(this.getChild(i + 1).copy());
        }

        ArrayList<MSSyntaxTree> lambdaArgsCopy = new ArrayList<>();
        for (int i = 0; i < this.numLambdaArgs; i++) {
            lambdaArgsCopy.add(this.getChild(i + 1 + this.numProcArgs).copy());
        }

        return new MSLambdaCall(idCopy, procArgsCopy, lambdaArgsCopy);
    }

    @Override
    public String getStringRep() {
        return "";
    }

    @Override
    public String toString() {
        return "LAMBDA_CALL";
    }

    public MSSyntaxTree getIdentifier() {
        return this.getChild(0);
    }

    public ArrayList<MSSyntaxTree> getProcedureArguments() {
        ArrayList<MSSyntaxTree> procArgs = new ArrayList<>();
        for (int i = 0; i < this.numProcArgs; i++) {
            procArgs.add(this.getChild(i + 1));
        }
        return procArgs;
    }

    public ArrayList<MSSyntaxTree> getLambdaArguments() {
        ArrayList<MSSyntaxTree> lambdaArgs = new ArrayList<>();
        for (int i = 0; i < this.numLambdaArgs; i++) {
            lambdaArgs.add(this.getChild(i + 1 + this.numProcArgs));
        }
        return lambdaArgs;
    }
}
