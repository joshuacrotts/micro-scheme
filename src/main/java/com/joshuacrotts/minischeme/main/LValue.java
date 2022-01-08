package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.*;

/**
 * LValues are "return values" from a section of the interpreter. Each
 * interpreter step returns an LValue object to designate what to do with
 * some intermediate step. For example, interpreting an ID resolves it to
 * some value in the symbol table which may be a string, bool, number, etc.
 * This gets passed up the chain to other interpreter steps to be used as such.
 *
 * @author Joshua Crotts
 * @version 01/07/2021
 */
public class LValue {

    /**
     * Type associated with this LValue.
     */
    private final LValueType type;

    /**
     * Number associated with this LValue if type == NUM.
     */
    private MSNumberNode dval;

    /**
     * Boolean associated with this LValue if type == BOOL.
     */
    private MSBooleanNode bval;

    /**
     * String associated with this LValue if type == STR.
     */
    private MSStringNode strval;

    /**
     * AST node associated with this LValue for any other LValueType.
     */
    private MSSyntaxTree tval;

    protected LValue(final LValueType type) {
        this.type = type;
    }

    protected LValue(final MSNumberNode dval) {
        this(LValueType.NUM);
        this.dval = dval;
    }

    protected LValue(final MSBooleanNode bval) {
        this(LValueType.BOOL);
        this.bval = bval;
    }

    protected LValue(final MSStringNode strval) {
        this(LValueType.STR);
        this.strval = strval;
    }

    protected LValue(final MSSymbolNode symVal) {
        this(LValueType.SYM);
        this.tval = symVal;
    }

    protected LValue(final MSVectorNode vecVal) {
        this(LValueType.VECTOR);
        this.tval = vecVal;
    }

    protected LValue(final double dval) {
        this(new MSNumberNode(dval));
    }

    protected LValue(final boolean bval) {
        this(new MSBooleanNode(bval));
    }

    protected LValue(final String sval) {
        this(new MSStringNode(sval));
    }

    protected LValue(final MSSyntaxTree tval) {
        if (tval instanceof MSNumberNode) {
            this.type = LValueType.NUM;
            this.dval = ((MSNumberNode) tval);
        } else if (tval instanceof MSBooleanNode) {
            this.type = LValueType.BOOL;
            this.bval = ((MSBooleanNode) tval);
        } else if (tval instanceof MSStringNode) {
            this.type = LValueType.STR;
            this.strval = ((MSStringNode) tval);
        } else if (tval instanceof MSSymbolNode || tval instanceof MSSymbolLiteralNode) {
            this.type = LValueType.SYM;
            this.tval = tval;
        } else {
            this.type = LValueType.PAIR;
            this.tval = tval;
        }
    }

    protected LValue(LValueType type, MSSyntaxTree tval) {
        this.type = type;
        this.tval = tval;
    }

    protected LValue() {
        this(LValueType.NULL);
    }

    /**
     *
     * @param lval
     * @return
     */
    protected static MSSyntaxTree getAstFromLValue(final LValue lval) {
        switch (lval.getType()) {
            case NUM: return new MSNumberNode(lval.getDoubleValue());
            case BOOL: return new MSBooleanNode(lval.getBoolValue());
            case STR: return new MSStringNode(lval.getStringValue());
            case SYM:
            case VECTOR:
            case PROCCALL:
            case LAMBDACALL:
            case PAIR: return lval.getTreeValue();
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this.type) {
            case NUM:
                return ((int) this.dval.getValue() == this.dval.getValue())
                        ? Integer.toString((int) this.dval.getValue())
                        : Double.toString(this.dval.getValue());
            case BOOL: return this.bval.getValue() ? "#t" : "#f";
            case STR: return this.strval.getValue();
            case SYM:
            case VECTOR:
            case PAIR:
                return this.tval == null ? "()" : this.tval.getStringRep();
            case PROCCALL: return "#<procedure-" + ((MSIdentifierNode) this.tval).getIdentifier() + ">";
            case LAMBDACALL: return "#<lambda-" + ((MSIdentifierNode) this.tval).getIdentifier() + ">";
        }
        return "";
    }

    /**
     * @return
     */
    protected String toDisplayString() {
        return this.isLString() ? this.strval.getStringRep() : this.toString();
    }

    protected double getDoubleValue() {
        return this.dval.getValue();
    }

    protected boolean getBoolValue() {
        return this.bval.getValue();
    }

    protected String getStringValue() {
        return this.strval.getStringRep();
    }

    protected LValueType getType() {
        return this.type;
    }

    protected MSSyntaxTree getTreeValue() {
        return this.tval;
    }

    protected boolean isLNumber() { return this.type == LValueType.NUM; }

    protected boolean isLBool() { return this.type == LValueType.BOOL; }

    protected boolean isLString() { return this.type == LValueType.STR; }

    protected boolean isLSymbol() { return this.type == LValueType.SYM; }

    protected boolean isLVector() { return this.type == LValueType.VECTOR; }

    protected boolean isLPair() { return this.type == LValueType.PAIR; }

    protected boolean isLProcCall() { return this.type == LValueType.PROCCALL; }

    protected boolean isLLambdaCall() { return this.type == LValueType.LAMBDACALL; }

    /**
     *
     */
    protected enum LValueType {
        NUM, BOOL, PAIR, STR, SYM, VECTOR, DISP, PROCCALL, LAMBDACALL, NULL
    }
}