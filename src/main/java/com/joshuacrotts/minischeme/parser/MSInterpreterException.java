package com.joshuacrotts.minischeme.parser;

/**
 *
 */
public class MSInterpreterException extends IllegalArgumentException {

    public MSInterpreterException(String msg) {
        super("Internal interpreter error: " + msg);
    }
}
