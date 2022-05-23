/******************************************************************************
 *  File: MSUndefinedSymbolException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  MSUndefinedSymbolExceptions are thrown when an unknown symbol is encountered.
 *  Undefined implies it is not in the current environment or in any parent
 *  environments.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.parser;

public class MSUndefinedSymbolException extends MSSemanticException {
    
    private static final String ERR_STRING = "Undefined Symbol";
    
    public MSUndefinedSymbolException(String sym) {
        super(ERR_STRING, String.format("'%s'", sym));
    }
}
