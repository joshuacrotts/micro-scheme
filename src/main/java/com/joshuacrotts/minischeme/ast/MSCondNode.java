package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 */
public class MSCondNode extends MSSyntaxTree {

    /**
     *
     */
    private final int NUM_PREDICATES;

    /**
     *
     */
    private final int NUM_CONSEQUENTS;

    /**
     *
     */
    private final boolean hasElse;

    public MSCondNode(ArrayList<MSSyntaxTree> condPredicateList,
                      ArrayList<MSSyntaxTree> condConsequentList) {
        super(MSNodeType.COND);
        this.NUM_PREDICATES = condPredicateList.size();
        this.NUM_CONSEQUENTS = condConsequentList.size();
        this.hasElse = this.NUM_CONSEQUENTS - 1 == this.NUM_PREDICATES;
        condPredicateList.forEach(this::addChild);
        condConsequentList.forEach(this::addChild);
    }

    @Override
    public MSSyntaxTree copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public ArrayList<MSSyntaxTree> getPredicateList() {
        ArrayList<MSSyntaxTree> predicateList = new ArrayList<>();
        for (int i = 0; i < this.NUM_PREDICATES; i++) {
            predicateList.add(this.getChild(i));
        }
        return predicateList;
    }

    public ArrayList<MSSyntaxTree> getConsequentList() {
        ArrayList<MSSyntaxTree> predicateList = new ArrayList<>();
        for (int i = 0; i < this.NUM_CONSEQUENTS; i++) {
            predicateList.add(this.getChild(i + this.NUM_PREDICATES));
        }
        return predicateList;
    }

    public boolean hasElse() {
        return this.hasElse;
    }
}
