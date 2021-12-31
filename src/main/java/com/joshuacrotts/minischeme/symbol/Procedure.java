package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

/**
 *
 */
public class Procedure extends Symbol {

    /**
     *
     */
    private final MSSyntaxTree procDef;

    public Procedure(MSSyntaxTree procDef) {
        super(SymbolType.PROCEDURE);
        this.procDef = procDef;
    }

    public MSSyntaxTree getProcDef() {
        return this.procDef;
    }
}
