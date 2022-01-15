package com.joshuacrotts.minischeme.parser;

/**
 *
 */
public class MSInterpreterException extends Exception {

    public MSInterpreterException(String msg) {
        super("Internal interpreter error: " + msg);
    }
}
