package com.joshuacrotts.minischeme.symbol;

import com.joshuacrotts.minischeme.ast.MSNodeType;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;

/**
 *
 */
public enum SymbolType {

    PROCEDURE, VARIABLE, LAMBDA;

    /**
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
            case LIST:
            case VECTOR:
            case OP:
            case SYMBOL:
            case SYMBOL_LIT:
                return VARIABLE;
            case LAMBDA_DECL:
            case EXPR_LAMBDA_DECL:
                return LAMBDA;
            default:
                throw new MSInterpreterException("Cannot convert MSNodeType " + nodeType + " to SymbolType");
        }
    }
}
