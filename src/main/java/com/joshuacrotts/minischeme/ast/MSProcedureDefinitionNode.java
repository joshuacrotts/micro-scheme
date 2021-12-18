package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSProcedureDefinitionNode extends MSSyntaxTree {

    /**
     *
     */
    private ArrayList<MSSyntaxTree> body;

    /**
     *
     */
    private ArrayList<MSSyntaxTree> parameters;

    /**
     *
     */
    private MSSyntaxTree identifier;

    public MSProcedureDefinitionNode(MSSyntaxTree identifier, ArrayList<MSSyntaxTree> params, ArrayList<MSSyntaxTree> body) {
        super(MSNodeType.MS_PROCDECL);
        this.identifier = identifier;
        this.parameters = params;
        this.body = body;
    }

    public ArrayList<MSSyntaxTree> getBody() {
        return this.body;
    }

    /**
     *
     * @return
     */
    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifier + ": ");
        for (MSSyntaxTree params : this.parameters) {
            sb.append(params.getStringRep());
            sb.append(" ");
        }

        for (MSSyntaxTree expr : this.body) {
            sb.append(expr.getStringRep());
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROCDECL " + this.identifier + ": ");
        for (MSSyntaxTree params : this.parameters) {
            sb.append("(PARAM " + params.toString());
            sb.append(") ");
        }

        for (MSSyntaxTree expr : this.body) {
            sb.append(expr.toString());
            sb.append(" ");
        }
        sb.append(")");
        return sb.toString();
    }
}
