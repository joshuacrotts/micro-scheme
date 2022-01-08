package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSNodeType;

/**
 *
 */
public enum SymbolType {

    PROCEDURE, VARIABLE, LAMBDA;

    /**
     *
     * @param nodeType
     * @return
     */
    public static SymbolType getSymbolTypeFromNodeType(final MSNodeType nodeType) {
        switch (nodeType) {
            case PROC_DECL:
                return PROCEDURE;
            case VAR_DECL:
            case NUM:
            case BOOL:
            case STR:
            case PAIR:
            case LIST:
            case OP:
                return VARIABLE;
            case LAMBDA_DECL:
            case EXPR_LAMBDA_DECL:
                return LAMBDA;
            default:
                throw new IllegalArgumentException("Internal interpreter error -" +
                        " cannot convert MSNodeType " + nodeType + " to SymbolType.");
        }
    }
}
