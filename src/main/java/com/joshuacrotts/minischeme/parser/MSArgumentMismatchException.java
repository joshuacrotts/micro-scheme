/******************************************************************************
 *  File: MSArgumentMismatchException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.parser;

public class MSArgumentMismatchException extends MSSemanticException {

    private static final String ERR_STRING = "Arity Mismatch";
    
    public MSArgumentMismatchException(final String preamble, final String expected, final String received) {
        super(ERR_STRING, String.format("%s expected argument type %s but got %s", preamble, expected, received));
    }

    public MSArgumentMismatchException(final String preamble, final int expectedArity, final int receivedArity) {
        super(ERR_STRING, String.format("Procedure %s; expected: %d, received: %d", preamble, expectedArity, receivedArity));
    }
    
    public MSArgumentMismatchException(final int expectedArity, final int receivedArity) {
        super(ERR_STRING, String.format("expected: %d, received: %d", expectedArity, receivedArity));
    }

    public MSArgumentMismatchException(final String preamble, final int index, final String expectedType, final String receivedType) {
        super(ERR_STRING, String.format("%s argument %d expected type %s but got %s", preamble, index + 1, expectedType, receivedType));
    }
}
