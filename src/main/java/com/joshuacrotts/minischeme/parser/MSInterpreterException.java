/******************************************************************************
 *  File: MSInterpreterException.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/26/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.parser;

public class MSInterpreterException extends IllegalArgumentException {

    public MSInterpreterException(final String msg) {
        super("Internal interpreter error: " + msg);
    }
}
