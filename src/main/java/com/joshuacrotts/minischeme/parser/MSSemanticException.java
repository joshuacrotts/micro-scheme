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

package com.joshuacrotts.minischeme.parser;

public class MSSemanticException extends Exception {

    public MSSemanticException(final String errType, final String msg) {
        super(String.format("%s: %s", errType, msg));
    }
    
    public MSSemanticException(final String msg) {
        super(String.format("MiniScheme Error: %s", msg));
    }
}
