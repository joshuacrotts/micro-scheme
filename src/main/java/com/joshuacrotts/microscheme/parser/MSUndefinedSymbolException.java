/******************************************************************************
 *  File: MSUndefinedSymbolException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/26/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.parser;

public class MSUndefinedSymbolException extends MSSemanticException {
    
    private static final String ERR_STRING = "Undefined Symbol";
    
    public MSUndefinedSymbolException(String sym) {
        super(ERR_STRING, String.format("'%s'", sym));
    }
}
