//package com.joshuacrotts.minischeme.ast;
//
//import java.util.ArrayList;
//
///**
// * Represents the calling of a procedure-based lambda. A procedure-based
// * lambda is a procedure that has a lambda as its body.
// * <p>
// * CHILD 0: procedure identifier.
// * CHILD 1...m: procedure arguments.
// * CHILD m + 1...n: lambda arguments.
// */
//public class MSCallNode extends MSSyntaxTree {
//
//    /**
//     * Number of arguments passed to the procedure being called, if any.
//     */
//    private final int NUM_PROC_ARGS;
//
//    /**
//     * Number of arguments passed to the lambda being called, if any.
//     */
//    private final int NUM_LAMBDA_ARGS;
//
//    public MSCallNode(final MSSyntaxTree procIdentifier, final ArrayList<MSSyntaxTree> procArgs,
//                      final ArrayList<MSSyntaxTree> lambdaArgs) {
//        super(MSNodeType.CALL);
//        this.addChild(procIdentifier);
//        this.NUM_PROC_ARGS = procArgs.size();
//        this.NUM_LAMBDA_ARGS = lambdaArgs.size();
//
//        for (int i = 0; i < this.NUM_PROC_ARGS; i++) {
//            this.addChild(procArgs.get(i));
//        }
//
//        for (int i = 0; i < this.NUM_LAMBDA_ARGS; i++) {
//            this.addChild(lambdaArgs.get(i));
//        }
//    }
//
//    @Override
//    public MSSyntaxTree copy() {
//        MSSyntaxTree idCopy = this.getChild(0).copy();
//        ArrayList<MSSyntaxTree> procArgsCopy = new ArrayList<>();
//        for (int i = 0; i < this.NUM_PROC_ARGS; i++) {
//            procArgsCopy.add(this.getChild(i + 1).copy());
//        }
//
//        ArrayList<MSSyntaxTree> lambdaArgsCopy = new ArrayList<>();
//        for (int i = 0; i < this.NUM_LAMBDA_ARGS; i++) {
//            lambdaArgsCopy.add(this.getChild(i + 1 + this.NUM_PROC_ARGS).copy());
//        }
//
//        return new MSCallNode(idCopy, procArgsCopy, lambdaArgsCopy);
//    }
//
//    @Override
//    public String getStringRep() {
//        return this.getNodeType().toString();
//    }
//
//    @Override
//    public String toString() {
//        return this.getNodeType().toString();
//    }
//
//    public MSIdentifierNode getIdentifier() {
//        return (MSIdentifierNode) this.getChild(0);
//    }
//
//    public ArrayList<MSSyntaxTree> getProcedureArguments() {
//        ArrayList<MSSyntaxTree> procArgs = new ArrayList<>();
//        for (int i = 0; i < this.NUM_PROC_ARGS; i++) {
//            procArgs.add(this.getChild(i + 1));
//        }
//        return procArgs;
//    }
//
//    public ArrayList<MSSyntaxTree> getLambdaArguments() {
//        ArrayList<MSSyntaxTree> lambdaArgs = new ArrayList<>();
//        for (int i = 0; i < this.NUM_LAMBDA_ARGS; i++) {
//            lambdaArgs.add(this.getChild(i + 1 + this.NUM_PROC_ARGS));
//        }
//        return lambdaArgs;
//    }
//
//    public int getProcedureArgumentCount() {
//        return this.NUM_PROC_ARGS;
//    }
//
//    public int getLambdaArgumentCount() {
//        return this.NUM_LAMBDA_ARGS;
//    }
//}
