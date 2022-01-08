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
    private final TreeMap<String, SymbolEntry> symbolTable;

    public Environment() {
        this.symbolTable = new TreeMap<>();
    }

    /**
     *
     * @param identifier
     * @param symbolType
     * @param symbolExpr
     */
    public void addSymbol(final String identifier, final SymbolType symbolType,
                          final MSSyntaxTree symbolExpr) {
        this.symbolTable.put(identifier, new SymbolEntry(symbolType, symbolExpr));
    }

    /**
     *
     * @param identifier
     * @return
     */
    public boolean hasSymbol(final String identifier) {
        return this.symbolTable.containsKey(identifier);
    }

    public TreeMap<String, SymbolEntry> getSymbolTable() {
        return this.symbolTable;
    }
}
