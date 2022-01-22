package com.joshuacrotts.minischeme.parser;

/**
 *
 */
public class MSArgumentMismatchException extends MSSemanticException {

    public MSArgumentMismatchException(String preamble, String expected, String received) {
        super(String.format("%s expected argument type %s but got %s", preamble, expected, received));
    }

    public MSArgumentMismatchException(String preamble, int expectedArity, int receivedArity) {
        super(String.format("%s arity mismatch; expected: %d, received: %d", preamble, expectedArity, receivedArity));
    }

    public MSArgumentMismatchException(String preamble, int index, String expectedType, String receivedType) {
        super(String.format("%s argument %d expected type %s but got %s", preamble, index + 1, expectedType, receivedType));
    }
}
