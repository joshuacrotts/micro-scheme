package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

import java.util.Stack;

public class Environment {

    /**
     *
     */
    private Stack<SymbolTable> environments;

    public Environment() {
        this.environments = new Stack<>();
    }

    public void addEnvironment() {
        this.environments.push(new SymbolTable());
    }

    public void addVariable(String identifier, MSSyntaxTree value) {
        this.environments.peek().addVariable(identifier, value);
    }

    public void addProcedure(String identifier, MSSyntaxTree procDef) {
        this.environments.peek().addProcedure(identifier, procDef);
    }

    public boolean hasSymbol(String identifier) {
        return this.environments.peek().hasSymbol(identifier);
    }
}
