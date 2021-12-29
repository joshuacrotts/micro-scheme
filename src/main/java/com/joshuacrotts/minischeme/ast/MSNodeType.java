package com.joshuacrotts.minischeme.ast;

public enum MSNodeType {
    ROOT,
    ID,
    OP,
    NUM,
    STR,
    PAIR,
    LIST,
    PROC_DECL,
    PROC_CALL,
    LAMBDA_DECL,
    EXPR_LAMBDA_DECL,
    EXPR_LAMBDA_CALL,
    EXPR_LAMBDA_DECL_CALL,
    IF,
    COND,
    BOOL,
    SET,
    SET_READ,
    DECL_READ,
    VAR_DECL,
    CALL
}
