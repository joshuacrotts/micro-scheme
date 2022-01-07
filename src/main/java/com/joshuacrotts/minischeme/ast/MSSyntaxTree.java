package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.main.MSUtils;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 *
 * @author Joshua Crotts
 * @version 12/23/2021
 */
public class MSSyntaxTree implements Copyable {

    /**
     * Each MSSyntaxTree has 0...n children associated with it. This
     * list keeps track of them.
     */
    private final ArrayList<MSSyntaxTree> children;

    /**
     * Type of MSSyntaxTree this is. Controls how the tree is evaluated.
     */
    private final MSNodeType nodeType;

    public MSSyntaxTree() {
        this(MSNodeType.ROOT);
    }

    public MSSyntaxTree(MSNodeType nodeType) {
        this.children = new ArrayList<>();
        this.nodeType = nodeType;
    }

    public MSSyntaxTree(MSNodeType nodeType, MSSyntaxTree... children) {
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

    @Override
    public String toString() {
        return "ROOT";
    }

    /**
     * Recursively prints the syntax tree.
     */
    public void printSyntaxTree() {
        System.out.println(this.printSyntaxTreeHelper(0));
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

    public void setChild(int idx, MSSyntaxTree tree) {
        this.children.set(idx, tree);
    }

    public MSNodeType getNodeType() {
        return this.nodeType;
    }

    public boolean isExprLambdaDecl() {
        return this.nodeType == MSNodeType.EXPR_LAMBDA_DECL;
    }

    public boolean isOp() {
        return this.nodeType == MSNodeType.OP;
    }

    public boolean isId() {
        return this.nodeType == MSNodeType.ID;
    }

    public boolean isVector() {
        return this.nodeType == MSNodeType.VECTOR;
    }

    public boolean isVarDecl() {
        return this.nodeType == MSNodeType.VAR_DECL;
    }

    public boolean isNumber() {
        return this.nodeType == MSNodeType.NUM;
    }

    public boolean isPair() {
        return this.nodeType == MSNodeType.PAIR;
    }

    public boolean isList() {
        return this.nodeType == MSNodeType.LIST;
    }

    public boolean isBool() {
        return this.nodeType == MSNodeType.BOOL;
    }

    public boolean isString() {
        return this.nodeType == MSNodeType.STR;
    }

    public boolean isSymbol() {
        return this.nodeType == MSNodeType.SYMBOL;
    }

    public boolean isSymbolLit() { return this.nodeType == MSNodeType.SYMBOL_LIT; }

    public boolean isLetDecl() {
        return this.nodeType == MSNodeType.LET_DECL;
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

    /**
     * Recursive copy helper function.
     *
     * @param root
     * @param newTree
     */
    private void copyHelper(MSSyntaxTree root, MSSyntaxTree newTree) {
        for (MSSyntaxTree child : root.getChildren()) {
            newTree.addChild(child.copy());
        }
    }

    /**
     * Recursive function to print a syntax tree. The current depth is passed as the "indent"
     * parameter so that the output looks properly nested. Each recursive call for a child is
     * indented by two additional spaces.
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
                if (child != null) {
                    sb.append(child.printSyntaxTreeHelper(indent + 2));
                }
            }
            sb.append(")");
        }

        return sb;
    }
}
