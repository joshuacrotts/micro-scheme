package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.main.MSUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Joshua Crotts
 * @version 12/23/2021
 */
public class MSSyntaxTree implements Copyable {

    /**
     * Each MSSyntaxTree has 0...n children associated with it. This
     * list keeps track of them.
     */
    private final ArrayList<MSSyntaxTree> CHILDREN;

    /**
     * Type of MSSyntaxTree this is. Controls how the tree is evaluated.
     */
    private final MSNodeType NODE_TYPE;

    public MSSyntaxTree() {
        this(MSNodeType.ROOT);
    }

    public MSSyntaxTree(final MSNodeType nodeType) {
        this.CHILDREN = new ArrayList<>();
        this.NODE_TYPE = nodeType;
    }

    public MSSyntaxTree(final MSNodeType nodeType, final MSSyntaxTree... children) {
        this.CHILDREN = new ArrayList<>();
        this.CHILDREN.addAll(Arrays.asList(children));
        this.NODE_TYPE = nodeType;
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree t = new MSSyntaxTree(this.NODE_TYPE);
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

    public void addChild(final MSSyntaxTree tree) {
        this.CHILDREN.add(tree);
    }

    public int getChildrenSize() {
        return this.CHILDREN.size();
    }

    public ArrayList<MSSyntaxTree> getChildren() {
        return this.CHILDREN;
    }

    public MSSyntaxTree getChild(final int idx) {
        return this.CHILDREN.get(idx);
    }

    public void setChild(final int idx, final MSSyntaxTree tree) {
        this.CHILDREN.set(idx, tree);
    }

    public MSNodeType getNodeType() {
        return this.NODE_TYPE;
    }

    public boolean isExprLambdaDecl() {
        return this.NODE_TYPE == MSNodeType.EXPR_LAMBDA_DECL;
    }

    public boolean isOp() {
        return this.NODE_TYPE == MSNodeType.OP;
    }

    public boolean isId() {
        return this.NODE_TYPE == MSNodeType.ID;
    }

    public boolean isVector() {
        return this.NODE_TYPE == MSNodeType.VECTOR;
    }

    public boolean isVarDecl() {
        return this.NODE_TYPE == MSNodeType.VAR_DECL;
    }

    public boolean isNumber() {
        return this.NODE_TYPE == MSNodeType.NUM;
    }

    public boolean isList() {
        return this.NODE_TYPE == MSNodeType.LIST;
    }

    public boolean isBool() {
        return this.NODE_TYPE == MSNodeType.BOOL;
    }

    public boolean isChar() {
        return this.NODE_TYPE == MSNodeType.CHAR;
    }

    public boolean isString() {
        return this.NODE_TYPE == MSNodeType.STR;
    }

    public boolean isSymbol() {
        return this.NODE_TYPE == MSNodeType.SYMBOL;
    }

    public boolean isSymbolLit() {
        return this.NODE_TYPE == MSNodeType.SYMBOL_LIT;
    }

    public boolean isLetDecl() {
        return this.NODE_TYPE == MSNodeType.LET_DECL;
    }

    public boolean isApplication() { return this.NODE_TYPE == MSNodeType.APPLICATION; }

    public boolean isSet() { return this.NODE_TYPE == MSNodeType.SET; }

    public boolean isTerminalType() {
        return this.isNumber() || this.isSymbol() || this.isString() || this.isSymbolLit() || this.isBool();
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
    private StringBuilder printSyntaxTreeHelper(final int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(MSUtils.repeatString(Math.max(0, indent), " "));
        sb.append(this);

        if (!this.CHILDREN.isEmpty()) {
            sb.append(" (\n");
            boolean isFirstChild = true;
            for (MSSyntaxTree child : this.CHILDREN) {
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
