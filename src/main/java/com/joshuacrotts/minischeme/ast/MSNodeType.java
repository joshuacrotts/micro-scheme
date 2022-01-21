package com.joshuacrotts.minischeme.ast;

/**
 *
 * @author Joshua Crotts
 * @version 01/19/2022
 */
public enum MSNodeType {
    ROOT,
    SEQ,
    NUMBER,
    STRING,
    BOOLEAN,
    CHARACTER,
    SYMBOL,
    VARIABLE,
    COND,
    LAMBDA,
    DECLARATION,
    APPLICATION;
}
