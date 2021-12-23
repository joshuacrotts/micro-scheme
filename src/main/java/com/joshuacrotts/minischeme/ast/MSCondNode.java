package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSCondNode extends MSSyntaxTree {

    /**
     *
     */
    private int condCondCount = 0;

    /**
     *
     */
    private int condBodyCount = 0;

    /**
     *
     */
    private boolean hasElse;

    public MSCondNode(ArrayList<MSSyntaxTree> condCond,
        ArrayList<MSSyntaxTree> condBody) {
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
            this.condBodyCount++;
            this.addChild(condBody.get(condBody.size() - 1));
        }
    }

    private MSCondNode(ArrayList<MSSyntaxTree> condCond,
        ArrayList<MSSyntaxTree> condBody,
        boolean hasElse) {
        this(condCond, condBody);
        this.hasElse = hasElse;
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> condCondCopy = new ArrayList<>();
        ArrayList<MSSyntaxTree> condBodyCopy = new ArrayList<>();
        for (int i = 0; i < this.getChildrenSize(); i += 2) {
            condCondCopy.add(this.getChild(i).copy());
        }
        for (int i = 1; i < this.getChildrenSize(); i += 2) {
            condBodyCopy.add(this.getChild(i).copy());
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

