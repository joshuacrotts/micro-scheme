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
    LAMBDA_DECL,
    EXPR_LAMBDA_DECL,
    IF,
    COND,
    BOOL
}
