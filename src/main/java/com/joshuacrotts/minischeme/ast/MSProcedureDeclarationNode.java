package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 * Defines a Scheme procedure definition. A Scheme procedure consists of three primary components:
 * the identifier (i.e., the name of the procedure), its parameters (which may be empty), and then
 * an expression defining the body of a procedure. Note that the body expression can be as complex
 * as it ought to be since expressions are recursive.
 * <p>
 * Child 0: Identifier of procedure.
 * Child 1...n-1: Parameters of procedure, if any.
 * Child n: Body of procedure.
 *
 * @author Joshua Crotts
 * @version 12/28/2021
 */
public class MSProcedureDeclarationNode extends MSDeclaration implements Callable {

    /**
     * Number of parameters that this procedure requires.
     */
    private final int NUM_PARAMS;

    public MSProcedureDeclarationNode(final MSSyntaxTree identifier, final ArrayList<MSSyntaxTree> params,
                                      final MSSyntaxTree body) {
        super(MSNodeType.PROC_DECL);
        this.NUM_PARAMS = params.size();
        this.addChild(identifier);
        params.forEach(this::addChild);
        this.addChild(body);
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree idCopy = this.getChild(0).copy();

        ArrayList<MSSyntaxTree> newParams = new ArrayList<>();
        for (int i = 0; i < this.NUM_PARAMS; i++) {
            newParams.add(this.getChild(i + 1).copy());
        }

        MSSyntaxTree body = this.getBody().copy();
        return new MSProcedureDeclarationNode(idCopy, newParams, body);
    }

    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getIdentifier()).append(": ");
        for (int i = 0; i < this.NUM_PARAMS; i++) {
            MSSyntaxTree param = this.getChild(i + 1);
            sb.append(param.getStringRep());
            sb.append(" ");
        }

        sb.append(this.getBody().getStringRep());
        sb.append(" ");
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public int getArgumentIndex(String idStr) {
        // Offset to account for the identifier and body being children.
        for (int i = 0; i < this.NUM_PARAMS; i++) {
            MSIdentifierNode id = (MSIdentifierNode) this.getChild(i + 1);
            if (id.getIdentifier().equals(idStr)) {
                return i;
            }
        }
        return -1;
    }

    public MSIdentifierNode getIdentifier() {
        return (MSIdentifierNode) this.getChild(0);
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }

    public int getParameterCount() {
        return this.NUM_PARAMS;
    }
}
