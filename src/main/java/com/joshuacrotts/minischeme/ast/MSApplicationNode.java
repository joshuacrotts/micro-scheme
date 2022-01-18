package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSApplicationNode extends MSSyntaxTree {

    /**
     *
     */
    private final int NUM_ARGS;

    public MSApplicationNode(MSSyntaxTree lhs, ArrayList<MSSyntaxTree> rhsArgs) {
        super(MSNodeType.APPLICATION, lhs);
        rhsArgs.forEach(this::addChild);
        this.NUM_ARGS = rhsArgs.size();
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree lhsCopy = this.getChild(0).copy();
        ArrayList<MSSyntaxTree> rhsArgsCopy = new ArrayList<>();
        for (int i = 0; i < this.NUM_ARGS; i++) {
            rhsArgsCopy.add(this.getChild(i + 1).copy());
        }
        return new MSApplicationNode(lhsCopy, rhsArgsCopy);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getExpression() {
        return this.getChild(0);
    }

    public ArrayList<MSSyntaxTree> getArguments() {
        ArrayList<MSSyntaxTree> rhsArgs = new ArrayList<>();
        for (int i = 0; i < this.NUM_ARGS; i++) {
            rhsArgs.add(this.getChild(i + 1));
        }
        return rhsArgs;
    }
}
