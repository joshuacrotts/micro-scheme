package com.joshuacrotts.minischeme.ast;

/**
 * Represents an abstract declaration type. There are five classifications
 * of declarations: READ, PROC[edure], LAMBDA, VAR[iable], and LET.
 *
 * @author Joshua Crotts
 * @version 01/15/2021
 */
public abstract class MSDeclaration extends MSSyntaxTree {

    public MSDeclaration(final MSNodeType declType) {
        super(declType);
        if (!isValidDeclarationType(declType)) {
            throw new IllegalArgumentException("Internal interpreter error " +
                    "- declaration must be of type read, proc, lambda, var, or let " +
                    "but got " + declType.toString());
        }
    }

    /**
     * Returns whether or not this is a correct declaration. Only certain
     * subclasses should extend this class (i.e., those that declare
     * either an I/O action, procedure, lambda, variable, or let env.
     *
     * @param declType - node type of declaration.
     *
     * @return true if valid, false otherwise.
     */
    private static boolean isValidDeclarationType(final MSNodeType declType) {
        return declType == MSNodeType.DECL_READ
                || declType == MSNodeType.PROC_DECL
                || declType == MSNodeType.LAMBDA_DECL
                || declType == MSNodeType.VAR_DECL
                || declType == MSNodeType.EXPR_LAMBDA_DECL
                || declType == MSNodeType.LET_DECL;
    }
}
