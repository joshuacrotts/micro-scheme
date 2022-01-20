package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSApplicationNode extends MSSyntaxTree {

    /**
     * The number of arguments that this application has.
     */
    private final int NUM_ARGUMENTS;

    public MSApplicationNode(MSSyntaxTree variableNode, ArrayList<MSSyntaxTree> arguments) {
        super(MSNodeType.APPLICATION, variableNode);
        this.NUM_ARGUMENTS = arguments.size();
        arguments.forEach(this::addChild);
    }

    public MSSyntaxTree copy() {
        MSSyntaxTree variableCopy = this.getChild(0).copy();
        ArrayList<MSSyntaxTree> argumentsCopy = new ArrayList<>();
        for (int i = 0; i < this.NUM_ARGUMENTS; i++) {
            argumentsCopy.add(this.getChild(i + 1).copy());
        }
        return new MSApplicationNode(variableCopy, argumentsCopy);
    }

    public MSSyntaxTree getVariable() {
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
