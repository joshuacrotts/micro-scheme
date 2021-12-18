package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSProcedureDefinitionNode extends MSSyntaxTree {

    /**
     *
     */
    private MSSyntaxTree body;

    /**
     *
     */
    private ArrayList<MSSyntaxTree> parameters;

    /**
     *
     */
    private MSSyntaxTree identifier;

    public MSProcedureDefinitionNode(MSSyntaxTree identifier, ArrayList<MSSyntaxTree> params, MSSyntaxTree body) {
        super(MSNodeType.MS_PROCDECL);
        this.identifier = identifier;
        this.parameters = params;
        this.body = body;
    }


    public int getArgumentLoc(String idStr) {
        for (int i = 0; i < this.parameters.size(); i++) {
            MSIdentifierNode id = (MSIdentifierNode) this.parameters.get(i);
            if (id.getIdentifier().equals(idStr)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public MSSyntaxTree copy() {
        return null;
    }

    public MSSyntaxTree getBody() {
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

        sb.append(this.body.getStringRep());
        sb.append(" ");
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

        sb.append(this.body.toString());
        sb.append(" ");
        sb.append(")");
        return sb.toString();
    }
}
