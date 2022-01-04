package com.joshuacrotts.minischeme.symbol;

import java.util.TreeMap;

import com.joshuacrotts.minischeme.ast.*;

/**
 * @author Joshua Crotts
 */
public class TypeTable {

    /**
     *
     */
    private final TreeMap<String, MSTypeDeclarationNode> typeTable;

    public TypeTable() {
        this.typeTable = new TreeMap<>();
    }

    /**
     *
     * @param identifier
     * @param typeDecl
     */
    public void addType(String identifier, MSSyntaxTree typeDecl) {
        this.typeTable.put(identifier, (MSTypeDeclarationNode) typeDecl);
    }

    /**
     * Given an identifier, we return if the type is declared anywhere in the
     * type table.
     *
     * @param identifier - identifier of type.
     *
     * @return true if the type is in the table, false otherwise.
     */
    public boolean hasType(String identifier) {
        return this.typeTable.containsKey(identifier);
    }

    /**
     *
     * @param identifier
     * @return
     */
    public MSTypeDeclarationNode getType(String identifier) {
        if (this.hasType(identifier)) {
            return this.typeTable.get(identifier);
        }
        return null;
    }
}