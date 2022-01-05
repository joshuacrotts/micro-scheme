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

    // Conditionals.
    IF,
    COND,

    // Declarations.
    PROC_DECL,
    LAMBDA_DECL,
    EXPR_LAMBDA_DECL,
    VAR_DECL,
    LET_DECL,

    // Expression calls.
    EXPR_LAMBDA_DECL_CALL,
    CALL,

    // I/O or set.,
    SET,
    SET_READ,
    DECL_READ;
}
