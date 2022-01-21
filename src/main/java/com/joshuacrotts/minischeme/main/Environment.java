package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.ast.MSVariableNode;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;

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
    private final TreeMap<String, MSSyntaxTree> BINDINGS;

    public Environment() {
        this.BINDINGS = new TreeMap<>();
    }

    /**
     *
     * @param id
     * @param expr
     */
    public void bind(String id, MSSyntaxTree expr) {
        this.BINDINGS.put(id, expr);
    }

    /**
     *
     * @param id
     * @param expr
     */
    public void bind(MSSyntaxTree id, MSSyntaxTree expr) {
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
    public MSSyntaxTree findInEnvironment(String id) {
        return this.BINDINGS.get(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public MSSyntaxTree findInEnvironment(MSSyntaxTree id) {
        if (!id.isVariable()) {
            throw new MSInterpreterException("Cannot lookup non-variable " + id.getStringRep());
        }

        return this.findInEnvironment(((MSVariableNode) id).getIdentifier());
    }
}
