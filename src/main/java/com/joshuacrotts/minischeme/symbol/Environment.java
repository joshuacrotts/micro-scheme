package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

import java.util.Map;
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

    public Environment(final TreeMap<String, SymbolEntry> symbolTable) {
        this.SYMBOL_TABLE = symbolTable;
    }

    /**
     * @param identifier
     * @param symbolType
     * @param symbolExpr
     */
    public void addSymbol(final String identifier, final SymbolType symbolType,
                          final MSSyntaxTree symbolExpr) {
        this.SYMBOL_TABLE.put(identifier, new SymbolEntry(symbolType, symbolExpr));
    }

    /**
     * @param identifier
     * @return
     */
    public boolean hasSymbol(final String identifier) {
        return this.SYMBOL_TABLE.containsKey(identifier);
    }

    public TreeMap<String, SymbolEntry> getSymbolTable() {
        return this.SYMBOL_TABLE;
    }

    public Environment copy() {
        TreeMap<String, SymbolEntry> symbolTableCopy = new TreeMap<>();
        for (Map.Entry<String, SymbolEntry> pair : this.SYMBOL_TABLE.entrySet()) {
            SymbolEntry symEntry = pair.getValue();
            SymbolEntry symEntryCopy = new SymbolEntry(symEntry.getSymbolType(), symEntry.getSymbolData().copy());
            symbolTableCopy.put(pair.getKey(), symEntryCopy);
        }
        return new Environment(symbolTableCopy);
    }
}
