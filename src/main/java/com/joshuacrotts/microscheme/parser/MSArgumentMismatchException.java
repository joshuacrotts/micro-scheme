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

package com.joshuacrotts.microscheme.parser;

public class MSArgumentMismatchException extends MSSemanticException {

    private static final String TYPE_MISMATCH = "Argument Type Mismatch";
    private static final String ARITY_MISMATCH = "Arity Mismatch";
    
    public MSArgumentMismatchException(final String preamble, final int expectedArity, final int receivedArity) {
        super(ARITY_MISMATCH, String.format("Procedure %s; expected: %d, received: %d", preamble, expectedArity, receivedArity));
    }

    public MSArgumentMismatchException(final int expectedArity, final int receivedArity) {
        super(ARITY_MISMATCH, String.format("expected: %d, received: %d", expectedArity, receivedArity));
    }

    public MSArgumentMismatchException(final String preamble, final String expected, final String received) {
        super(TYPE_MISMATCH, String.format("%s expected argument type %s but got %s", preamble, expected, received));
    }

    public MSArgumentMismatchException(final String preamble, final int index, final String expectedType, final String receivedType) {
        super(TYPE_MISMATCH, String.format("%s argument %d expected type %s but got %s", preamble, index + 1, expectedType, receivedType));
    }
}
