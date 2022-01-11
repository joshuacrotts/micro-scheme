package com.joshuacrotts.minischeme.parser;

/**
 *
 */
public class MSArgumentMismatchException extends MSSemanticException {

    public MSArgumentMismatchException(String preamble, String expected, String received) {
        super(preamble + " expected " + expected + " argument but got " + received);
    }

    public MSArgumentMismatchException(String preamble, int expectedArity, int receivedArity) {
        super(preamble + " expected " + (expectedArity == 1 ? " argument " : "arguments") + " but got " + receivedArity);
    }
}
