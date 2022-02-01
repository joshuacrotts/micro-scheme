/******************************************************************************
 *  File: LValue.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.main;

import ch.obermuhlner.math.big.BigComplex;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;

import java.math.BigDecimal;

public class LValue {

    /**
     * Tree associated with this LValue.
     */
    private final MSSyntaxTree TREE;

    /**
     * Environment associated with this LValue.
     */
    private final Environment ENVIRONMENT;

    public LValue(final MSSyntaxTree tree) {
        this.TREE = tree;
        this.ENVIRONMENT = null;
    }

    public LValue(final MSSyntaxTree tree, final Environment env) {
        this.TREE = tree;
        this.ENVIRONMENT = env;
    }
    
    public LValue(final BigComplex number) {
        this(new MSNumberNode(number));
    }

    public LValue(final BigDecimal number) { this(new MSNumberNode(number)); }

    public LValue(final double number) {
        this(new MSNumberNode(number));
    }

    public LValue(final boolean bvalue) {
        this(new MSBooleanNode(bvalue));
    }

    public LValue(final String svalue) {
        this(new MSStringNode(svalue));
    }

    public LValue(final char cvalue) {
        this(new MSCharacterNode(cvalue));
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
            case VECTOR:
                return lval.TREE;
        }

        throw new MSInterpreterException("Cannot return ast for " + lval.TREE.getNodeType());
    }

    @Override
    public String toString() {
        switch (this.TREE.getNodeType()) {
            case VARIABLE:
            case NUMBER:
            case BOOLEAN:
            case STRING:
            case CHARACTER:
            case SYMBOL:
            case VECTOR:
            case LIST: return this.TREE.getStringRep();
            default:
                throw new MSInterpreterException("Cannot return LValue for " + this.TREE.getNodeType() + " yet");
        }
    }

    public BigComplex getNumberValue() {
        if (this.TREE.getNodeType() == MSNodeType.NUMBER) { return ((MSNumberNode) this.TREE).getValue(); }
        throw new MSInterpreterException("Cannot return number from non-number lvalue " + this.TREE.getNodeType());
    }

    public boolean getBooleanValue() {
        if (this.TREE.getNodeType() == MSNodeType.BOOLEAN) { return ((MSBooleanNode) this.TREE).getValue(); }
        throw new MSInterpreterException("Cannot return boolean from non-boolean lvalue " + this.TREE.getNodeType());
    }

    public char getCharacterValue() {
        if (this.TREE.getNodeType() == MSNodeType.CHARACTER) { return ((MSCharacterNode) this.TREE).getValue(); }
        throw new MSInterpreterException("Cannot return character from non-character lvalue " + this.TREE.getNodeType());
    }

    public String getStringValue() {
        if (this.TREE.getNodeType() == MSNodeType.STRING) { return ((MSStringNode) this.TREE).getValue(); }
        throw new MSInterpreterException("Cannot return string from non-string lvalue " + this.TREE.getNodeType());
    }

    public MSSyntaxTree getSymbolValue() {
        if (this.TREE.getNodeType() == MSNodeType.SYMBOL) { return ((MSSymbolNode) this.TREE).getValue(); }
        throw new MSInterpreterException("Cannot return symbol from non-symbol lvalue " + this.TREE.getNodeType());
    }

    public MSSyntaxTree getTree() {
        return this.TREE;
    }

    public Environment getEnvironment() { return this.ENVIRONMENT; }
}