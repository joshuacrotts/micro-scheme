package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 * Defines a Scheme procedure definition. A Scheme procedure consists of three primary components:
 * the identifier (i.e., the name of the procedure), its parameters (which may be empty), and then
 * an expression defining the body of a procedure. Note that the body expression can be as complex
 * as it ought to be since expressions are recursive.
 */
public class MSProcedureDeclarationNode extends MSSyntaxTree implements MSCallable {

    public MSProcedureDeclarationNode(MSSyntaxTree identifier, ArrayList<MSSyntaxTree> params,
                                      MSSyntaxTree body) {
        super(MSNodeType.PROC_DECL);
        this.addChild(identifier);
        for (int i = 0; i < params.size(); i++) {
            this.addChild(params.get(i));
        }
        this.addChild(body);
    }

    @Override
    public MSSyntaxTree copy() {
        // First, copy the identifier.
        MSSyntaxTree idCopy = this.getChild(0).copy();

        // Now copy the parameters.
        ArrayList<MSSyntaxTree> newParams = new ArrayList<>();
        for (int i = 1; i < this.getChildrenSize() - 1; i++) {
            MSSyntaxTree oldParam = this.getChild(i);
            newParams.add(oldParam.copy());
        }

        // Lastly, copy the body over.
        MSSyntaxTree body = this.getBody().copy();
        return new MSProcedureDeclarationNode(idCopy, newParams, body);
    }

    /**
     * @return
     */
    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getIdentifier()).append(": ");
        for (int i = 1; i < this.getChildrenSize() - 1; i++) {
            MSSyntaxTree param = this.getChild(i);
            sb.append(param.getStringRep());
            sb.append(" ");
        }

        sb.append(this.getBody().getStringRep());
        sb.append(" ");
        return sb.toString();
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getIdentifier() {
        return this.getChild(0);
    }

    /**
     * @param idStr
     * @return
     */
    public int getArgumentLoc(String idStr) {
        // Offset to account for the identifier and body being children.
        for (int i = 1; i < this.getChildrenSize() - 1; i++) {
            MSIdentifierNode id = (MSIdentifierNode) this.getChild(i);
            if (id.getIdentifier().equals(idStr)) {
                return i - 1;
            }
        }
        return -1;
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }
}
