package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.MiniSchemeParser;

/**
 *
 * @author Joshua Crotts
 * @version 01/23/2022
 */
public class MSSetNode extends MSSyntaxTree {

    /**
     *
     */
    private final int TYPE;

    public MSSetNode(int type, MSSyntaxTree lhsExpression, MSSyntaxTree rhsExpression) {
        super(type == MiniSchemeParser.SET ? MSNodeType.SET
                : type == MiniSchemeParser.SETCAR ? MSNodeType.SETCAR
                    : MSNodeType.SETCDR, lhsExpression, rhsExpression);
        this.TYPE = type;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSSetNode(this.TYPE, this.getAssignee().copy(), this.getExpression().copy());
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
