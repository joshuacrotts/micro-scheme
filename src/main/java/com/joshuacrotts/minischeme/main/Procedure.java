package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Procedure extends Symbol {

    /**
     *
     */
    private MSSyntaxTree procDef;

    public Procedure(MSSyntaxTree procDef) {
        super(SymbolType.SYMBOL_PROC);
        this.procDef = procDef;
    }

    public MSSyntaxTree getProcDef() {
        return this.procDef;
    }
}
