package com.joshuacrotts.minischeme.ast;

public enum MSNodeType {
    // Data types.
    ROOT,
    ID,
    OP,
    NUM,
    STR,
    PAIR,
    LIST,
    BOOL,
    SYMBOL,
    VECTOR,
    SYMBOL_LIT,

    // Conditionals.
    IF,
    COND,

    // Misc. declarations.
    DO,

    // Declarations.
    PROC_DECL,
    LAMBDA_DECL,
    EXPR_LAMBDA_DECL,
    VAR_DECL,
    LET_DECL,
    LET_NAMED_DECL,

    // Expression calls.
    EXPR_LAMBDA_DECL_CALL,
    CALL,

    // I/O or set.,
    SET,
    SET_READ,
    DECL_READ;
}
