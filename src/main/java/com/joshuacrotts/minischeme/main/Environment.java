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

    public Environment createChildEnvironment(ArrayList<MSSyntaxTree> formals, ArrayList<LValue> args) {
        Environment e1 = new Environment(this);
        for (int i = 0; i < formals.size(); i++) {
            e1.bind(formals.get(i).getStringRep(), args.get(i));
        }
        return e1;
    }

    @Override
    public String toString() {
        return "Environment";
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
     * @param expr
     */
    public void bind(MSSyntaxTree id, LValue expr) {
        if (!id.isVariable()) {
            throw new MSInterpreterException("Cannot bind non-variable " + id.getStringRep());
        }

        this.bind(((MSVariableNode) id).getIdentifier(), expr);
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

    public int numberOfBindings() {
         return this.BINDINGS.size();
    }
}
