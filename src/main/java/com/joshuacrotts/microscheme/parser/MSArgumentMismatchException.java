/******************************************************************************
 *  File: MSArgumentMismatchException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 ******************************************************************************/

package com.joshuacrotts.microscheme.parser;

public class MSArgumentMismatchException extends MSSemanticException {

    private static final String MISMATCH = "Argument Mismatch";

    public MSArgumentMismatchException(final String message) {
        super(MISMATCH, message);
    }

    public MSArgumentMismatchException(final String type, final String message) {
        super(type, message);
    }
}
