package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.ast.MSVariableNode;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Joshua Crotts
 * @version 01/20/2022
 */
public class Environment {

    /**
     *
     */
    private final TreeMap<String, LValue> BINDINGS;

    private Environment PARENT;

    public Environment(Environment parent) {
        this.BINDINGS = new TreeMap<>();
        this.PARENT = parent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // First, construct the current environment.
        sb.append("<");
        int idx = 0;
        for (Map.Entry<String, LValue> symbol : this.BINDINGS.entrySet()) {
            sb.append("{");
            sb.append(String.format("%s:%s", symbol.getKey(), symbol.getValue()));
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
                    sb.append(String.format("%s:%s", symbol.getKey(), symbol.getValue()));
                    sb.append((idx++ != this.BINDINGS.size() - 1) ? "}, " : "}");
                }
            }
        }
        sb.append(">");
        return sb.toString();
    }

    /**
     *
     * @param formals
     * @param arguments
     * @return
     */
    public Environment createChildEnvironment(ArrayList<MSSyntaxTree> formals, ArrayList<LValue> arguments) {
        Environment e1 = new Environment(this);
        for (int i = 0; i < formals.size(); i++) {
            e1.bind(formals.get(i).getStringRep(), arguments.get(i));
        }
        return e1;
    }

    /**
     *
     * @param id
     * @param expr
     */
    public void bind(String id, LValue expr) {
        this.BINDINGS.put(id, expr);
    }

    /**
     *
     * @param id
     * @return
     */
    public LValue lookup(String id) {
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
