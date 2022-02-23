/******************************************************************************
 *  File: Environment.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.main;

import com.joshuacrotts.microscheme.ast.MSSyntaxTree;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Environment {

    /**
     * Current bindings in this environment. Strings are paired with LValues.
     */
    private final TreeMap<String, LValue> BINDINGS;

    /**
     * Parent environment of *this* environment. Null if the global env.
     */
    private final Environment PARENT;

    public Environment(final Environment parent) {
        this.BINDINGS = new TreeMap<>();
        this.PARENT = parent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        // First, construct the current environment.
        int idx = 0;
        for (Map.Entry<String, LValue> symbol : this.BINDINGS.entrySet()) {
            sb.append("{");
            sb.append(String.format("%s:%s", symbol.getKey(), symbol.getValue().getTree().isLambda() ? "LAMBDA" : symbol.getValue()));
            sb.append((idx++ != this.BINDINGS.size() - 1 ) ? "}, " : "}");
        }

        // Then, if we have a parent environment, construct that.
        if (this.PARENT != null) {
            idx = 0;
            sb.append(" -> ");
            // If the mapping is empty, then just append <EMPTY>.
            if (this.PARENT.BINDINGS.isEmpty()) {
                sb.append("<EMPTY>");
            } else {
                for (Map.Entry<String, LValue> symbol : this.PARENT.BINDINGS.entrySet()) {
                    sb.append("{");
                    sb.append(String.format("%s:%s", symbol.getKey(), symbol.getValue().getTree().isLambda() ? "LAMBDA" : symbol.getValue()));
                    sb.append((idx++ != this.PARENT.BINDINGS.size() - 1) ? "}, " : "}");
                }
            }
        }
        sb.append(">");
        return sb.toString();
    }

    public Environment createChildEnvironment(final ArrayList<MSSyntaxTree> formals,
                                              final ArrayList<LValue> arguments) {
        Environment e1 = new Environment(this);
        for (int i = 0; i < formals.size(); i++) {
            e1.bind(formals.get(i).getStringRep(), arguments.get(i));
        }
        return e1;
    }

    public void createBindings(final ArrayList<MSSyntaxTree> formals,
                               final ArrayList<LValue> arguments) {
        for (int i = 0; i < formals.size(); i++) {
            this.bind(formals.get(i).getStringRep(), arguments.get(i));
        }
    }

    /**
     *
     * @param id
     * @param expr
     */
    public void bind(final String id, final LValue expr) {
        this.BINDINGS.put(id, expr);
    }

    /**
     *
     * @param id
     * @return
     */
    public LValue lookup(final String id) {
        LValue l = this.BINDINGS.get(id);
        if (l == null && this.PARENT != null) {
            l = this.PARENT.lookup(id);
        }
        return l;
    }

    public Environment getParent() {
        return this.PARENT;
    }
}
