package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

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
    public void addSymbol(String identifier, SymbolType symbolType, MSSyntaxTree symbolExpr) {
        this.symbolTable.put(identifier, new SymbolEntry(symbolType, symbolExpr));
    }

    /**
     *
     * @param identifier
     * @return
     */
    public boolean hasSymbol(String identifier) {
        return this.symbolTable.containsKey(identifier);
    }

    public TreeMap<String, SymbolEntry> getTreeMap() {
        return this.symbolTable;
    }
}
