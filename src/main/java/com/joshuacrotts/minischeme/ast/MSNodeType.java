package com.joshuacrotts.minischeme.ast;

public enum MSNodeType {
    ROOT,
    ID,
    OP,
    NUM,
    STR,
    PAIR,
    LIST,
    VAR,
    PROC_DECL,
    PROC_CALL,
    EXPR_LAMBDA_DECL,
    EXPR_LAMBDA_CALL,
    EXPR_LAMBDA_DECL_CALL,
    IF,
    COND,
    BOOL,
    SET
}
