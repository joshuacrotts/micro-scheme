/******************************************************************************
 *  File: MSApplicationNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *  A MSApplicationNode is an AST representing an application of a procedure to
 *  some arguments. The procedure is either a user-defined lambda or a builtin
 *  primitive value. Each argument is evaluated prior to evaluating the
 *  MSApplicationNode.
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSApplicationNode extends MSSyntaxTree {

    /**
     * The number of arguments that this application has.
     */
    private final int NUM_ARGUMENTS;

    public MSApplicationNode(final MSSyntaxTree expressionNode, final ArrayList<MSSyntaxTree> arguments) {
        super(MSNodeType.APPLICATION, expressionNode);
        this.NUM_ARGUMENTS = arguments.size();
        arguments.forEach(this::addChild);
    }

    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < this.NUM_ARGUMENTS - 1; i++) {
            sb.append(this.getChild(i + 1).getStringRep());
            sb.append(" ");
        }
        sb.append(this.getChild(this.getChildrenSize() - 1).getStringRep());
        return sb.append(")").toString();
    }

    public MSSyntaxTree getExpression() {
        return this.getChild(0);
    }

    public ArrayList<MSSyntaxTree> getArguments() {
        ArrayList<MSSyntaxTree> arguments = new ArrayList<>();
        for (int i = 0; i < this.NUM_ARGUMENTS; i++) {
            arguments.add(this.getChild(i + 1));
        }
        return arguments;
    }
}
