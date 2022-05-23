/******************************************************************************
 *  File: MSInterpreterException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  MSInterpreterExceptions are thrown by the interpreter to designate an error
 *  with the parser or interpreter itself rather than code.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.parser;

public class MSInterpreterException extends IllegalArgumentException {

    public MSInterpreterException(final String msg) {
        super("Internal interpreter error: " + msg);
    }
}
