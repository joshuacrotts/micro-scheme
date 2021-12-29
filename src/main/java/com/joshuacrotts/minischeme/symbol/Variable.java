package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.ast.MSVariableDeclarationNode;

/**
 *
 */
public class Variable extends Symbol {

    /**
     *
     */
    private MSSyntaxTree varDecl;

    public Variable(MSSyntaxTree varDecl) {
        super(SymbolType.SYMBOL_VAR);
        this.varDecl = varDecl;
    }

    public MSSyntaxTree getExpression() {
        return ((MSVariableDeclarationNode) this.varDecl).getExpression();
    }
}
