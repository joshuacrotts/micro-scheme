package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import java.util.HashMap;

public class SymbolTable {

    private final HashMap<String, Symbol> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void addVariable(String identifier, MSSyntaxTree value) {
        this.table.put(identifier, new Variable(value));
    }

    public void addProcedure(String identifier, MSSyntaxTree procDef) {
        this.table.put(identifier, new Procedure(procDef));
    }

    public void addLambda(String identifier, MSSyntaxTree lambdaDef) {
        this.table.put(identifier, new Lambda(lambdaDef));
    }

    public Variable getVariable(String sym) {
        return this.isVariable(sym) ? (Variable) this.table.get(sym) : null;
    }

    public Procedure getProcedure(String sym) {
        return this.isProcedure(sym) ? (Procedure) this.table.get(sym) : null;
    }

    public Lambda getLambda(String sym) { return this.isLambda(sym) ? (Lambda) this.table.get(sym) : null; }

    public void setVariable(String identifier, MSSyntaxTree value) {
        if (!this.table.containsKey(identifier)) {
            throw new IllegalArgumentException("ERR variable " + identifier + " undefined");
        }
        this.table.put(identifier, new Variable(value));
    }

    public boolean hasSymbol(String identifier) {
        return this.table.containsKey(identifier);
    }

    public boolean isVariable(String identifier) {
        return this.hasSymbol(identifier)
            && this.table.get(identifier).getType() == SymbolType.SYMBOL_VAR;
    }

    public boolean isProcedure(String identifier) {
        return this.hasSymbol(identifier)
            && this.table.get(identifier).getType() == SymbolType.SYMBOL_PROC;
    }

    public boolean isLambda(String identifier) {
        return this.hasSymbol(identifier)
            && this.table.get(identifier).getType() == SymbolType.SYMBOL_LAMBDA;
    }
}
