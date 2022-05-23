/******************************************************************************
 *  File: MSArgumentTypeMismatchException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 ******************************************************************************/

package com.joshuacrotts.microscheme.parser;

public class MSArgumentTypeMismatchException extends MSArgumentMismatchException {

    private static final String TYPE_MISMATCH = "Argument Type Mismatch";

    public MSArgumentTypeMismatchException(final String preamble, final String expected, final String received) {
        super(TYPE_MISMATCH, String.format("%s expected argument type %s but got %s", preamble, expected, received));
    }

    public MSArgumentTypeMismatchException(final String preamble, final int index, final String expectedType, final String receivedType) {
        super(TYPE_MISMATCH, String.format("%s argument %d expected type %s but got %s", preamble, index + 1, expectedType, receivedType));
    }
}
