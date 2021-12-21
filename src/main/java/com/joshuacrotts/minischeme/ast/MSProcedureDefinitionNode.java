package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 * Defines a Scheme procedure definition. A Scheme procedure consists of three primary components:
 * the identifier (i.e., the name of the procedure), its parameters (which may be empty), and then
 * an expression defining the body of a procedure. Note that the body expression can be as complex
 * as it ought to be since expressions are recursive.
 */
public class MSProcedureDefinitionNode extends MSSyntaxTree {

    /**
     *
     */
    private final MSSyntaxTree body;

    /**
     *
     */
    private final ArrayList<MSSyntaxTree> parameters;

    /**
     *
     */
    private final MSSyntaxTree identifier;

    public MSProcedureDefinitionNode(MSSyntaxTree identifier, ArrayList<MSSyntaxTree> params,
        MSSyntaxTree body) {
        super(MSNodeType.PROC_DECL);
        this.identifier = identifier;
        this.parameters = params;
        this.body = body;
    }

    @Override
    public MSSyntaxTree copy() {
        // First, copy the identifier.
        MSSyntaxTree idCopy = this.identifier.copy();

        // Now copy the parameters.
        ArrayList<MSSyntaxTree> newParams = new ArrayList<>();
        for (MSSyntaxTree oldParam : this.parameters) {
            newParams.add(oldParam.copy());
        }

        // Lastly, copy the body over.
        MSSyntaxTree body = this.body.copy();
        return new MSProcedureDefinitionNode(idCopy, newParams, body);
    }

    /**
     * @return
     */
    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifier).append(": ");
        for (MSSyntaxTree params : this.parameters) {
            sb.append(params.getStringRep());
            sb.append(" ");
        }

        sb.append(this.body.getStringRep());
        sb.append(" ");
        return sb.toString();
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROCDECL ").append(this.identifier);
        for (MSSyntaxTree params : this.parameters) {
            sb.append("(PARAM " + params.toString());
            sb.append(") ");
        }

        sb.append(this.body.toString());
        sb.append(" ");
        sb.append(")");
        return sb.toString();
    }

    public MSSyntaxTree getIdentifier() {
        return this.identifier;
    }

    /**
     * @param idStr
     * @return
     */
    public int getArgumentLoc(String idStr) {
        for (int i = 0; i < this.parameters.size(); i++) {
            MSIdentifierNode id = (MSIdentifierNode) this.parameters.get(i);
            if (id.getIdentifier().equals(idStr)) {
                return i;
            }
        }
        return -1;
    }

    public MSSyntaxTree getBody() {
        return this.body;
    }
}
