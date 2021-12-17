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
    private String identifier;

    /**
     *
     */
    private int parameters;

    public MSProcedureDefinitionNode(String identifier, int params, ArrayList<MSSyntaxTree> body) {
        super(MSNodeType.MS_PROC);
    }
}
