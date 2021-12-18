package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSProcedureCallNode extends MSSyntaxTree {

    /**
     *
     */
    private ArrayList<MSSyntaxTree> args;

    /**
     *
     */
    private MSSyntaxTree identifier;

    public MSProcedureCallNode(MSSyntaxTree identifier, ArrayList<MSSyntaxTree> args) {
        super(MSNodeType.MS_PROCCALL);
        this.identifier = identifier;
        this.args = args;
    }

    public MSSyntaxTree getIdentifier() {
        return this.identifier;
    }

    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder(this.identifier + " ");
        sb.append(this.identifier.getStringRep());
        for (MSSyntaxTree args : this.args) {
            sb.append(args.getStringRep());
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(PROCCALL ");
        sb.append(this.identifier.toString());
        for (MSSyntaxTree args : this.args) {
            sb.append("(ARG " + args.toString());
            sb.append(") ");
        }
        sb.append(")");
        return sb.toString();
    }
}
