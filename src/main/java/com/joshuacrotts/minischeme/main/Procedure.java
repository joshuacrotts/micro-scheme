package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Procedure extends Symbol {

    /**
     *
     */
    private ArrayList<MSSyntaxTree> functionBody;

    /**
     *
     */
    private int parameters;

    public Procedure(String identifier, int parameters, ArrayList<MSSyntaxTree> functionBody) {
        super(SymbolType.SYMBOL_PROC);
        this.parameters = parameters;
        this.functionBody = functionBody;
    }

    public int getParameters() {
        return this.parameters;
    }

    public ArrayList<MSSyntaxTree> getFunctionBody() {
        return this.functionBody;
    }
}
