/******************************************************************************
 *  File: MSArgumentArityMismatchException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 ******************************************************************************/

package com.joshuacrotts.microscheme.parser;

public class MSArgumentArityMismatchException extends MSArgumentMismatchException {

    private static final String ARITY_MISMATCH = "Arity Mismatch";

    public MSArgumentArityMismatchException(final String preamble, final int expectedArity, final int receivedArity) {
        super(ARITY_MISMATCH, String.format("Procedure %s; expected: %d, received: %d", preamble, expectedArity, receivedArity));
    }

    public MSArgumentArityMismatchException(final int expectedArity, final int receivedArity) {
        super(ARITY_MISMATCH, String.format("expected: %d, received: %d", expectedArity, receivedArity));
    }
}
