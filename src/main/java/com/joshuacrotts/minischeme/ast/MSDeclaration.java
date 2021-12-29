package com.joshuacrotts.minischeme.ast;

/**
 *
 */
public abstract class MSDeclaration extends MSSyntaxTree {

    public MSDeclaration(MSNodeType declType) {
        super(declType);
        if (!isValidDeclarationType(declType)) {
            throw new IllegalArgumentException("Internal interpreter error " +
                    "- declaration must be of type read, proc, lambda, or var.");
        }
    }

    /**
     *
     * @param declType
     * @return
     */
    private static boolean isValidDeclarationType(MSNodeType declType) {
        return declType == MSNodeType.DECL_READ
                || declType == MSNodeType.PROC_DECL
                || declType == MSNodeType.LAMBDA_DECL
                || declType == MSNodeType.VAR_DECL
                || declType == MSNodeType.EXPR_LAMBDA_DECL;
    }
}
