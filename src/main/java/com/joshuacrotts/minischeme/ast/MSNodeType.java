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

    ROOT("root"),
    SEQUENCE("sequence"),
    NUMBER("number"),
    STRING("string"),
    BOOLEAN("boolean"),
    CHARACTER("char"),
    SYMBOL("symbol"),
    LIST("list"),
    VECTOR("vector"),
    VARIABLE("variable"),
    COND("cond"),
    LAMBDA("lambda"),
    SET("set!"),
    SETCAR("set-car!"),
    SETCDR("set-cdr!"),
    SETVECTOR("vector-set!"),
    DO("do"),
    DECLARATION("declaration"),
    APPLICATION("application");
        
    private final String STRING_REP;
    
    MSNodeType(final String stringRep) {
        this.STRING_REP = stringRep;
    }
    
    @Override
    public String toString() {
        return this.STRING_REP;
    }
}
