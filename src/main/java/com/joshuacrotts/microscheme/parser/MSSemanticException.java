/******************************************************************************
 *  File: MSSemanticException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  A semantic exception is a type of runtime exception. These can occur for
 *  a variety of reasons, e.g., arity mismatch, type mismatch...
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.parser;

public class MSSemanticException extends RuntimeException {

    public MSSemanticException(final String errType, final String msg) {
        super(String.format("%s: %s", errType, msg));
    }
    
    public MSSemanticException(final String msg) {
        super(String.format("MicroScheme Error: %s", msg));
    }
}
