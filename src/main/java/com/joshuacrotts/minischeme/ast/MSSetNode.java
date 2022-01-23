package com.joshuacrotts.minischeme.ast;

public class MSSetNode extends MSSyntaxTree {

    public MSSetNode(MSSyntaxTree lhsExpression, MSSyntaxTree rhsExpression) {
        super(MSNodeType.SET, lhsExpression, rhsExpression);
    }
}
