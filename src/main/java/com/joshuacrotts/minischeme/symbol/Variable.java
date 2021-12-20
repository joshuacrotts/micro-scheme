package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

public class Variable extends Symbol {

    /**
     *
     */
    private final MSSyntaxTree expr;

    public Variable(MSSyntaxTree expression) {
        super(SymbolType.SYMBOL_VAR);
        this.expr = expression;
    }

    public MSSyntaxTree getExpression() {
        return this.expr;
    }
}
