/******************************************************************************
 *  File: MSSyntaxTree.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/26/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.main.MSUtils;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Arrays;

public class MSSyntaxTree {

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
    public String toString() {
        return this.getNodeType().toString();
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

    public boolean isRoot() { return this.NODE_TYPE == MSNodeType.ROOT; }

    public boolean isVariable() { return this.NODE_TYPE == MSNodeType.VARIABLE; }

    public boolean isLambda() { return this.NODE_TYPE == MSNodeType.LAMBDA; }

    public boolean isBoolean() { return this.NODE_TYPE == MSNodeType.BOOLEAN; }

    public boolean isCharacter() { return this.NODE_TYPE == MSNodeType.CHARACTER; }

    public boolean isCond() { return this.NODE_TYPE == MSNodeType.COND; }

    public boolean isString() { return this.NODE_TYPE == MSNodeType.STRING; }

    public boolean isApplication() { return this.NODE_TYPE == MSNodeType.APPLICATION; }

    public boolean isSymbol() { return this.NODE_TYPE == MSNodeType.SYMBOL; }

    public boolean isList() { return this.NODE_TYPE == MSNodeType.LIST; }

    public boolean isNumber() { return this.NODE_TYPE == MSNodeType.NUMBER; }

    public boolean isVector() { return this.NODE_TYPE == MSNodeType.VECTOR; }
    
    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        for (MSSyntaxTree child : this.getChildren()) {
            if (child != null) {
                sb.append(child.getStringRep());
            }
        }
        return sb.toString();
    }
    
    public String getStringNodeType() {
        return this.getNodeType().toString();
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
