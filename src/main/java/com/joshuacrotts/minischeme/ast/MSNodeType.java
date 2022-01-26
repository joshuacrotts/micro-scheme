/******************************************************************************
 *  File: MSNodeType.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

public enum MSNodeType {
    ROOT,
    SEQUENCE,
    NUMBER,
    STRING,
    BOOLEAN,
    CHARACTER,
    SYMBOL,
    LIST,
    VECTOR,
    VARIABLE,
    COND,
    LAMBDA,
    SET,
    SETCAR,
    SETCDR,
    SETVECTOR,
    DO,
    DECLARATION,
    APPLICATION
}
