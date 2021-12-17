package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Symbol> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void addVariable(String identifier, MSSyntaxTree value) {
        this.table.put(identifier, new Variable(value));
    }

    public void addProcedure(String identifier, ArrayList<MSSyntaxTree> body) {
        this.table.put(identifier, new Procedure(identifier, body.size(), body));
    }

    public boolean hasSymbol(String identifier) {
        return this.table.containsKey(identifier);
    }

    public boolean isVariable(String identifier) {
        return this.hasSymbol(identifier )&& this.table.get(identifier).getType() == SymbolType.SYMBOL_VAR;
    }

    public boolean isProcedure(String identifier) {
        return this.hasSymbol(identifier )&& this.table.get(identifier).getType() == SymbolType.SYMBOL_PROC;
    }
}
