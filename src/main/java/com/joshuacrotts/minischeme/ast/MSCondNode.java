/******************************************************************************
 *  File: MSCondNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/26/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSCondNode extends MSSyntaxTree {

    /**
     * Number of predicates (conditionals) in this COND node.
     */
    private final int NUM_PREDICATES;

    /**
     * Number of consequents (bodies) in this COND node.
     */
    private final int NUM_CONSEQUENTS;

    /**
     * Does this COND have an alternative body?
     */
    private final boolean HAS_ELSE;

    public MSCondNode(final ArrayList<MSSyntaxTree> condPredicateList,
                      final ArrayList<MSSyntaxTree> condConsequentList) {
        super(MSNodeType.COND);
        this.NUM_PREDICATES = condPredicateList.size();
        this.NUM_CONSEQUENTS = condConsequentList.size();
        this.HAS_ELSE = this.NUM_CONSEQUENTS - 1 == this.NUM_PREDICATES;
        condPredicateList.forEach(this::addChild);
        condConsequentList.forEach(this::addChild);
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
        ArrayList<MSSyntaxTree> consequentList = new ArrayList<>();
        for (int i = 0; i < this.NUM_CONSEQUENTS; i++) {
            consequentList.add(this.getChild(i + this.NUM_PREDICATES));
        }
        return consequentList;
    }

    public boolean hasElse() {
        return this.HAS_ELSE;
    }
}
