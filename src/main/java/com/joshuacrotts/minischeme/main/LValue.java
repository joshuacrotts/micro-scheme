package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;

import java.math.BigDecimal;

/**
 *
 * @author Joshua Crotts
 * @version 01/07/2021
 */
public class LValue {

    /**
     * Tree associated with this LValue.
     */
    private final MSSyntaxTree TREE;

    public LValue(final MSSyntaxTree tree) {
        this.TREE = tree;
    }

    public BigDecimal getNumberValue() {
        if (this.TREE.getNodeType() == MSNodeType.NUMBER) {
            return ((MSNumberNode) this.TREE).getNumber();
        }
        throw new MSInterpreterException("Cannot return number from non-number lvalue " + this.TREE.getNodeType());
    }

    public String toString() {
        switch (this.TREE.getNodeType()) {
            case VARIABLE: return ((MSVariableNode) this.TREE).getStringRep();
            case NUMBER: return ((MSNumberNode) this.TREE).getStringRep();
            default:
                throw new MSInterpreterException("Cannot return LValue for " + this.TREE.getNodeType() + " yet.");
        }
    }
}