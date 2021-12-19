package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 * Defines a call to a Scheme procedure. A procedure is defined via
 * (define (proc param1 param2...) (expr)). The children of a
 * MSProcedureCall node are the identifier (i.e., the procedure being
 * called) and its arguments.
 */
public class MSProcedureCallNode extends MSSyntaxTree {

    public MSProcedureCallNode(MSSyntaxTree identifier, ArrayList<MSSyntaxTree> args) {
        super(MSNodeType.MS_PROCCALL);
        this.addChild(identifier);
        args.forEach(this::addChild);
    }

    @Override
    public MSSyntaxTree copy() {
        // Just copy the identifier and the children over.
        MSSyntaxTree identifierCopy = this.getChild(0).copy();
        ArrayList<MSSyntaxTree> argsCopy = new ArrayList<>();
        for (int i = 1; i < this.getChildrenSize(); i++) { argsCopy.add(this.getChild(i).copy()); }
        return new MSProcedureCallNode(identifierCopy, argsCopy);
    }

    public MSSyntaxTree getIdentifier() {
        return this.getChild(0);
    }

    public ArrayList<MSSyntaxTree> getArguments() {
        ArrayList<MSSyntaxTree> args = new ArrayList<>();
        for (int i = 1; i < this.getChildrenSize(); i++) { args.add(this.getChild(i)); }
        return args;
    }

    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder(this.getChild(0) + " ");
        sb.append(this.getChild(0).getStringRep());
        for (MSSyntaxTree args : this.getArguments()) {
            sb.append(args.getStringRep());
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(PROCCALL ");
        sb.append(this.getChild(0).toString());
        for (MSSyntaxTree args : this.getArguments()) {
            sb.append("(ARG ").append(args.toString());
            sb.append(") ");
        }
        sb.append(")");
        return sb.toString();
    }
}
