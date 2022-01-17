package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 * Represents a COND call. COND is equivalent to an if/else if/else chain
 * in other imperative languages. Each cond statement consists of a predicate
 * and an expression if its corresponding predicate returns true.
 * <p>
 * Example: (cond ([< x 0] (- x))
 * (else (x)))
 * <p>
 * COND statements don't require else expressions.
 * <p>
 * Child 0...2...4...: COND predicate.
 * Child 1...3...5...: COND expression if predicate is true.
 * Optional Child n-1: ELSE expression.
 *
 * @author Joshua Crotts
 * @version 12/23/2021
 */
public class MSCondNode extends MSSyntaxTree {

    /**
     * Number of conditions (i.e., how many conditions to possibly check).
     */
    private int condCondCount = 0;

    /**
     * Number of expression bodies. This should be a 1 to 1 ratio with condCondCount
     * unless there is an else statement.
     */
    private int condBodyCount = 0;

    /**
     * Keeps track of whether this cond has an else body.
     */
    private boolean hasElse;

    public MSCondNode(final ArrayList<MSSyntaxTree> condCond, final ArrayList<MSSyntaxTree> condBody) {
        super(MSNodeType.COND);
        for (int i = 0; i < condCond.size(); i++) {
            this.addChild(condCond.get(i));
            this.addChild(condBody.get(i));
            this.condCondCount++;
            this.condBodyCount++;
        }
        // If they are different sizes, then we know there is an else block.
        if (condBody.size() == condCond.size() + 1) {
            this.hasElse = true;
            this.addChild(condBody.get(condBody.size() - 1));
        }
    }

    private MSCondNode(final ArrayList<MSSyntaxTree> condCond, final ArrayList<MSSyntaxTree> condBody,
                       final boolean hasElse) {
        this(condCond, condBody);
        this.hasElse = hasElse;
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> condCondCopy = new ArrayList<>();
        ArrayList<MSSyntaxTree> condBodyCopy = new ArrayList<>();
        for (int i = 0; i < this.condCondCount; i++) {
            condCondCopy.add(this.getChild(2 * i).copy());
        }
        for (int i = 0; i < this.condBodyCount; i++) {
            condBodyCopy.add(this.getChild(2 * i + 1).copy());
        }
        if (this.hasElse) {
            condBodyCopy.add(this.getChild(this.getChildrenSize() - 1).copy());
        }

        return new MSCondNode(condCondCopy, condBodyCopy, this.hasElse);
    }

    @Override
    public String getStringRep() {
        return "";
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public boolean hasElse() {
        return this.hasElse;
    }
}

