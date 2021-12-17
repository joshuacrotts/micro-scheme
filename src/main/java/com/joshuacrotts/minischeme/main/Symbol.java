package com.joshuacrotts.minischeme.main;

public abstract class Symbol {

    /**
     *
     */
    private SymbolType type;

    public Symbol(SymbolType type) {
        this.type = type;
    }

    public SymbolType getType() {
        return this.type;
    }
}
