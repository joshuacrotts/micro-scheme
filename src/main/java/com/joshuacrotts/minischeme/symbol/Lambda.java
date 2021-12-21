package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

public class Lambda extends Symbol {

    /**
     *
     */
    private final MSSyntaxTree lambdaDef;

    public Lambda(MSSyntaxTree lambdaDef) {
        super(SymbolType.SYMBOL_LAMBDA);
        this.lambdaDef = lambdaDef;
    }

    public MSSyntaxTree getLambdaDef() {
        return this.lambdaDef;
    }
}
