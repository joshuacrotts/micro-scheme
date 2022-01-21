package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.ast.MSVariableNode;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;

import java.util.Stack;

public class EnvironmentStack {

    /**
     *
     */
    private final Stack<Environment> ENVIRONMENT_STACK;

    public EnvironmentStack() {
        this.ENVIRONMENT_STACK = new Stack<>();
    }

    public void bind(MSSyntaxTree id, MSSyntaxTree expr) {
        this.ENVIRONMENT_STACK.peek().bind(id, expr);
    }

    public MSSyntaxTree lookup(MSSyntaxTree id) {
        // We have to use a for loop to traverse backwards since iterators are broken with stacks.
        for (int i = this.ENVIRONMENT_STACK.size() - 1; i >= 0; i--) {
            Environment curr = this.ENVIRONMENT_STACK.get(i);
            if (!id.isVariable()) { throw new MSInterpreterException("Cannot search environment for non-variable " + id.getNodeType()); }

            MSSyntaxTree found = this.hasSymbolInEnvironment(id.getStringRep(), curr);
            if (found != null) { return found; }
        }

        return null;
    }

    public void addEnvironment(Environment environment) {
        this.ENVIRONMENT_STACK.push(environment);
    }

    public void addEnvironment() {
        this.ENVIRONMENT_STACK.push(new Environment());
    }

    public void popEnvironment() {
        this.ENVIRONMENT_STACK.pop();
    }

    /**
     * Given an identifier, we return if the symbol is declared inside an arbitrary
     * environment.
     *
     * @param id
     * @param environment
     * @return
     */
    private MSSyntaxTree hasSymbolInEnvironment(String id, Environment environment) {
        int idx = this.ENVIRONMENT_STACK.indexOf(environment);
        if (idx < 0 || idx >= this.ENVIRONMENT_STACK.size()) {
            throw new IndexOutOfBoundsException("idx " + idx + " is out of bounds: " + this.ENVIRONMENT_STACK.size());
        }

        return this.ENVIRONMENT_STACK.get(idx).findInEnvironment(id);
    }
}
