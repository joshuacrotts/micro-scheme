package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

/**
 *
 */
public class SymbolEntry {

    /**
     *
     */
    private SymbolType symbolType;

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

    public void setSymbolType(SymbolType symbolType) {
        this.symbolType = symbolType;
    }

    public MSSyntaxTree getSymbolData() {
        return this.symbolData;
    }

    public void setSymbolData(MSSyntaxTree symbolData) {
        this.symbolData = symbolData;
    }
}
