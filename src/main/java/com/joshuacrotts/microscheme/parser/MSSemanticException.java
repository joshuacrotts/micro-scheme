/******************************************************************************
 *  File: MSSemanticException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/26/2022
 *
 *
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
