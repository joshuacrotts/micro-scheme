package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.main.MSUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class MSSyntaxTree implements Copyable {

    /**
     *
     */
    private ArrayList<MSSyntaxTree> children;

    /**
     *
     */
    private MSNodeType nodeType;

    public MSSyntaxTree() {
        this(MSNodeType.ROOT);
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

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree t = new MSSyntaxTree(this.nodeType);
        t.copyHelper(this, t);
        return t;
    }

    /**
     *
     * @param root
     * @param newTree
     */
    private void copyHelper(MSSyntaxTree root, MSSyntaxTree newTree) {
        for (MSSyntaxTree child : root.getChildren()) { newTree.addChild(child.copy()); }
    }

    /**
     * Recursively prints the syntax tree.
     */
    public void printSyntaxTree() {
        System.out.println(this.printSyntaxTreeHelper(0));
    }

    /**
     * Recursive function to print a syntax tree. The current depth is passed
     * as the "indent" parameter so that the output looks properly nested.
     * Each recursive call for a child is indented by two additional spaces.
     *
     * @param indent current indentation level
     * @return a string representation of this syntax tree node (and its descendants)
     * @author Steve Tate
     */
    private StringBuilder printSyntaxTreeHelper(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(MSUtils.repeatString(Math.max(0, indent), " "));
        sb.append(this);

        if (!this.children.isEmpty()) {
            sb.append(" (\n");
            boolean isFirstChild = true;
            for (MSSyntaxTree child : this.children) {
                if (!isFirstChild) {
                    sb.append(",\n");
                }
                isFirstChild = false;
                sb.append(child.printSyntaxTreeHelper(indent + 2));
            }
            sb.append(")");
        }

        return sb;
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

    public MSSyntaxTree getChild(int idx) { return this.children.get(idx); }

    public void setChild(int idx, MSSyntaxTree tree) { this.children.set(idx, tree); }

    public MSNodeType getNodeType() {
        return this.nodeType;
    }

    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        for (MSSyntaxTree child : this.getChildren()) {
            if (child != null) {
                sb.append(child.getStringRep());
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ROOT";
    }
}
