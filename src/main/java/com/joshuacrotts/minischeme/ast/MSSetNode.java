package com.joshuacrotts.minischeme.ast;

/**
 *
 * @author Joshua Crotts
 * @version 01/23/2022
 */
public class MSSetNode extends MSSyntaxTree {

    public MSSetNode(MSSyntaxTree lhsExpression, MSSyntaxTree rhsExpression) {
        super(MSNodeType.SET, lhsExpression, rhsExpression);
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSetNode(this.getAssignee().copy(), this.getExpression().copy());
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getAssignee() {
        return this.getChild(0);
    }

    public MSSyntaxTree getExpression() {
        return this.getChild(1);
    }
}
