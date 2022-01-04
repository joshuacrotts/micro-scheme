package com.joshuacrotts.minischeme.symbol;

import java.util.Stack;
import java.util.TreeMap;

import com.joshuacrotts.minischeme.ast.MSNodeType;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.ast.MSVariableDeclarationNode;

/**
 * @author Joshua Crotts
 */
public class SymbolTable {

    /**
     * Stack of environment objects to represent the call-stack/scope for variables,
     * procedures, and lambdas. Each time a variable or function is referenced, it is
     * checked against this table. Variables are checked in the top-most stack for existence
     * and it traverses to the bottom until they are either found or not. Functions
     * are simply checked for existence but if they are declared twice, then we
     * throw an error (this does not happen yet).
     */
    private final Stack<Environment> environmentTable;

    public SymbolTable() {
        this.environmentTable = new Stack<>();
    }

    /**
     * Given an identifier, SymbolType, and MSSyntaxTree data object, we push it to the top-most
     * environment in the stack.
     *
     * @param id   - identification of the symbol.
     * @param type
     * @param data
     */
    public void addSymbol(String id, SymbolType type, MSSyntaxTree data) {
        this.environmentTable.peek().addSymbol(id, type, data);
    }

    /**
     *
     * @param id
     * @param data
     */
    public void setSymbol(String id, MSSyntaxTree data) {
        SymbolEntry entry = this.getSymbolEntry(id);
        entry.setSymbolData(data);
    }

    /**
     * Given an identifier, we return if the symbol is declared anywhere in the
     * symbol table.
     *
     * @param id - identifier of symbol.
     *
     * @return true if the symbol is in any environment stack, false otherwise.
     */
    public boolean hasSymbol(String id) {
        boolean found = false;

        // We have to use a for loop to traverse backwards since iterators are broken
        // with stacks...
        for (int i = this.environmentTable.size() - 1; i >= 0; i--) {
            Environment curr = this.environmentTable.get(i);
            found = hasSymbolInEnvironment(id, curr);

            if (found) {
                return true;
            }
        }

        return false;
    }

    /**
     * Given an identifier, we return if the symbol is declared inside the current
     * environment. This is useful for determining if we have a variable previously
     * declared in the same scope.
     *
     * @param id - identifier of symbol.
     * @return true if the identifier was found in the current environment (defined
     *         as the top-most environment on the stack), false otherwise.
     */
    public boolean hasSymbolInCurrentEnvironment(String id) {
        return this.environmentTable.peek().hasSymbol(id);
    }

    /**
     * Given a symbol ID, returns the SymbolEntry object. This is useful for
     * comparing datatypes of a variable, procedure, etc.
     *
     * @param id - identifier of symbol.
     *
     * @return SymbolEntry value for identifier key.
     */
    public SymbolEntry getSymbolEntry(String id) {
        for (int i = this.environmentTable.size() - 1; i >= 0; i--) {
            Environment curr = this.environmentTable.get(i);

            for (String key : curr.getSymbolTable().keySet()) {
                if (id.equals(key)) {
                    return curr.getSymbolTable().get(key);
                }
            }
        }

        return null;
    }

    /**
     * Adds a new environment to the stack.
     */
    public void addEnvironment() {
        this.environmentTable.push(new Environment());
    }

    /**
     * Removes the top-most environment from the stack. This is useful for going out
     * of scope of a function, for instance (really, this is the ONLY place it is
     * used).
     */
    public void popEnvironment() {
        this.environmentTable.pop();
    }

    /**
     * This method should print out all of the global variables. It can be called
     * after parsing in order to see what global variables were seen. Names should
     * be output in alphabetical order.
     */
    public void printGlobalVars() {
        int stackSize = this.environmentTable.size();
        // Retrieve the bottom of the stack (which is the global block).
        Environment globalEnvironment = this.environmentTable.get(stackSize - 1);

        // Return the set of keys.
        TreeMap<String, SymbolEntry> map = globalEnvironment.getSymbolTable();

        // Now iterate through the map and find all vars.
        for (String key : map.keySet()) {
            if (map.get(key).getSymbolType() == SymbolType.VARIABLE) {
                System.out.println(key);
            }
        }
    }

    public boolean isVariable(String id) {
        if (this.hasSymbol(id)) { return this.getSymbolEntry(id).getSymbolType() == SymbolType.VARIABLE; }
        return false;
    }

    public MSSyntaxTree getVariable(String id) {
        if (isVariable(id)) {
            MSSyntaxTree varData = this.getSymbolEntry(id).getSymbolData();
            if (varData.getNodeType() == MSNodeType.VAR_DECL) {
                return ((MSVariableDeclarationNode) varData).getExpression();
            }
            return varData;
        }
        return null;
    }

    public boolean isProcedure(String id) {
        if (this.hasSymbol(id)) { return this.getSymbolEntry(id).getSymbolType() == SymbolType.PROCEDURE; }
        return false;
    }

    public boolean isLambda(String id) {
        if (this.hasSymbol(id)) { return this.getSymbolEntry(id).getSymbolType() == SymbolType.LAMBDA; }
        return false;
    }

    /**
     * Given an identifier, we return if the symbol is declared inside an arbitrary
     * environment.
     *
     * @param id
     * @param environment
     * @return
     */
    private boolean hasSymbolInEnvironment(String id, Environment environment) {
        int idx = this.environmentTable.indexOf(environment);
        if (idx < 0 || idx >= this.environmentTable.size()) {
            throw new IndexOutOfBoundsException("idx " + idx + " is out of bounds.");
        }

        return this.environmentTable.get(idx).hasSymbol(id);
    }
}