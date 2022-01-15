package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

import java.util.TreeMap;

/**
 *
 */
public class Environment {

    /**
     *
     */
    private final TreeMap<String, SymbolEntry> SYMBOL_TABLE;

    public Environment() {
        this.SYMBOL_TABLE = new TreeMap<>();
    }

    /**
     *
     * @param identifier
     * @param symbolType
     * @param symbolExpr
     */
    public void addSymbol(final String identifier, final SymbolType symbolType,
                          final MSSyntaxTree symbolExpr) {
        this.SYMBOL_TABLE.put(identifier, new SymbolEntry(symbolType, symbolExpr));
    }

    /**
     *
     * @param identifier
     * @return
     */
    public boolean hasSymbol(final String identifier) {
        return this.SYMBOL_TABLE.containsKey(identifier);
    }

    public TreeMap<String, SymbolEntry> getSymbolTable() {
        return this.SYMBOL_TABLE;
    }
}
