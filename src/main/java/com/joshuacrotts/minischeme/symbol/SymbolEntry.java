package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

/**
 *
 */
public class SymbolEntry {

    /**
     *
     */
    private final SymbolType symbolType;

    /**
     *
     */
    private MSSyntaxTree symbolData;

    protected SymbolEntry(SymbolType symbolType, MSSyntaxTree symbolData) {
        this.symbolType = symbolType;
        this.symbolData = symbolData;
    }

    public SymbolType getSymbolType() {
        return this.symbolType;
    }

    public MSSyntaxTree getSymbolData() {
        return this.symbolData;
    }

    public void setSymbolData(MSSyntaxTree symbolData) {
        this.symbolData = symbolData;
    }
}
