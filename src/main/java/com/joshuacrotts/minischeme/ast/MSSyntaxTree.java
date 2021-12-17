package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;
import java.util.Arrays;

public class MSSyntaxTree {

    /**
     *
     */
    private ArrayList<MSSyntaxTree> children;

    /**
     *
     */
    private MSNodeType nodeType;

    public MSSyntaxTree() {
        this(MSNodeType.MS_ROOT);
    }

    public MSSyntaxTree(MSNodeType nodeType) {
        this.children = new ArrayList<>();
        this.nodeType = nodeType;
    }

    public MSSyntaxTree(MSNodeType nodeType, MSSyntaxTree ... children) {
        this.children = new ArrayList<>();
        this.nodeType = nodeType;
        this.children.addAll(Arrays.asList(children));
    }

    public void addChild(MSSyntaxTree tree) {
        this.children.add(tree);
    }

    public int getChildrenSize() {
        return this.children.size();
    }

    public ArrayList<MSSyntaxTree> getChildren() {
        return this.children;
    }

    public MSSyntaxTree getChild(int idx) {
        return this.children.get(idx);
    }

    public MSNodeType getNodeType() {
        return this.nodeType;
    }

    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        for (MSSyntaxTree child : this.getChildren()) {
            sb.append(child.getStringRep());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ROOT (");
        for (MSSyntaxTree child : this.getChildren()) {
            sb.append(child.toString());
        }
        sb.append(")");
        return sb.toString();
    }
}
