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

    public MSArgumentMismatchException(final String preamble, final String expected, final String received) {
        super(String.format("%s expected argument type %s but got %s", preamble, expected, received));
    }

    public MSArgumentMismatchException(final String preamble, final int expectedArity, final int receivedArity) {
        super(String.format("%s arity mismatch; expected: %d, received: %d", preamble, expectedArity, receivedArity));
    }

    public MSArgumentMismatchException(final String preamble, final int index, final String expectedType, final String receivedType) {
        super(String.format("%s argument %d expected type %s but got %s", preamble, index + 1, expectedType, receivedType));
    }
}
