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

    public Environment env;

    public LValue(final MSSyntaxTree tree) {
        this.TREE = tree;
    }

    /**
     * Returns the corresponding value for an LValue. This is usually just
     * the AST in the underlying LValue for further evaluation.
     *
     * @param lval LValue object to get AST from.
     * @return MSSyntaxTree object corresponding to LValue.
     */
    public static MSSyntaxTree getAst(final LValue lval) {
        switch (lval.TREE.getNodeType()) {
            case NUMBER:
            case BOOLEAN:
            case CHARACTER:
            case STRING:
            case VARIABLE:
            case SYMBOL:
            case LIST:
            case APPLICATION:
            case LAMBDA:
                return lval.TREE;
        }

        throw new MSInterpreterException("Cannot return ast for " + lval.TREE.getNodeType());
    }

    public BigDecimal getNumberValue() {
        if (this.TREE.getNodeType() == MSNodeType.NUMBER) {
            return ((MSNumberNode) this.TREE).getValue();
        }
        throw new MSInterpreterException("Cannot return number from non-number lvalue " + this.TREE.getNodeType());
    }

    public boolean getBooleanValue() {
        if (this.TREE.getNodeType() == MSNodeType.BOOLEAN) {
            return ((MSBooleanNode) this.TREE).getValue();
        }
        throw new MSInterpreterException("Cannot return boolean from non-boolean lvalue " + this.TREE.getNodeType());
    }

    public char getCharacterValue() {
        if (this.TREE.getNodeType() == MSNodeType.CHARACTER) {
            return ((MSCharacterNode) this.TREE).getValue();
        }
        throw new MSInterpreterException("Cannot return character from non-character lvalue " + this.TREE.getNodeType());
    }

    public String getStringValue() {
        if (this.TREE.getNodeType() == MSNodeType.STRING) {
            return ((MSStringNode) this.TREE).getValue();
        }
        throw new MSInterpreterException("Cannot return string from non-string lvalue " + this.TREE.getNodeType());
    }

    public MSSyntaxTree getSymbolValue() {
        if (this.TREE.getNodeType() == MSNodeType.SYMBOL) {
            return ((MSSymbolNode) this.TREE).getValue();
        }
        throw new MSInterpreterException("Cannot return symbol from non-symbol lvalue " + this.TREE.getNodeType());
    }

    public String toString() {
        switch (this.TREE.getNodeType()) {
            case VARIABLE: return ((MSVariableNode) this.TREE).getStringRep();
            case NUMBER: return ((MSNumberNode) this.TREE).getStringRep();
            case BOOLEAN: return ((MSBooleanNode) this.TREE).getStringRep();
            case STRING: return ((MSStringNode) this.TREE).getStringRep();
            case CHARACTER: return ((MSCharacterNode) this.TREE).getStringRep();
            case SYMBOL: return ((MSSymbolNode) this.TREE).getStringRep();
            case LIST: return ((MSListNode) this.TREE).getStringRep();
            default:
                throw new MSInterpreterException("Cannot return LValue for " + this.TREE.getNodeType() + " yet.");
        }
    }

    public MSSyntaxTree getTree() {
        return this.TREE;
    }
}