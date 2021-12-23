package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSBooleanNode;
import com.joshuacrotts.minischeme.ast.MSNumberNode;
import com.joshuacrotts.minischeme.ast.MSStringNode;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

/**
 *
 */
public class LValue {

    /**
     *
     */
    private final LValueType type;

    /**
     *
     */
    private MSNumberNode dval;

    /**
     *
     */
    private MSBooleanNode bval;

    /**
     *
     */
    private MSStringNode sval;

    /**
     *
     */
    private MSSyntaxTree tval;

    protected LValue(LValueType type) {
        this.type = type;
    }

    protected LValue(MSNumberNode dval) {
        this(LValueType.NUM);
        this.dval = dval;
    }

    protected LValue(MSBooleanNode bval) {
        this(LValueType.BOOL);
        this.bval = bval;
    }

    protected LValue(MSStringNode sval) {
        this(LValueType.STR);
        this.sval = sval;
    }

    protected LValue(double dval) {
        this(new MSNumberNode(dval));
    }

    protected LValue(boolean bval) {
        this(new MSBooleanNode(bval));
    }

    protected LValue(String sval) {
        this(new MSStringNode(sval));
    }

    protected LValue(MSSyntaxTree tval) {
        if (tval instanceof MSNumberNode) {
            this.type = LValueType.NUM;
            this.dval = ((MSNumberNode) tval);
        } else if (tval instanceof MSBooleanNode) {
            this.type = LValueType.BOOL;
            this.bval = ((MSBooleanNode) tval);
        } else if (tval instanceof MSStringNode) {
            this.type = LValueType.STR;
            this.sval = ((MSStringNode) tval);
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
    protected static MSSyntaxTree getAstFromLValue(LValue lval) {
        switch (lval.getType()) {
            case NUM: return new MSNumberNode(lval.getDoubleValue());
            case BOOL: return new MSBooleanNode(lval.getBoolValue());
            case STR: return new MSStringNode(lval.getStringValue());
            case PAIR: return lval.getTreeValue();
            case NULL:
                return null;
        }
        throw new IllegalArgumentException("Internal interpreter error " +
                "- cannot get AST from LValue of type " + lval.getType());
    }

    @Override
    public String toString() {
        switch (this.type) {
            case NUM:
                return ((int) this.dval.getValue() == this.dval.getValue())
                       ? Integer.toString((int) this.dval.getValue())
                       : Double.toString(this.dval.getValue());
            case BOOL:
                return this.bval.getValue() ? "#t" : "#f";
            case STR:
                return this.sval.getValue();
            case PAIR:
                return this.tval == null
                       ? "()"
                       : this.tval.getStringRep();
        }
        return "";
    }

    /**
     * @return
     */
    protected String toDisplayString() {
        return this.type == LValueType.STR
                ? this.sval.getStringRep()
                : this.toString();
    }

    protected double getDoubleValue() {
        return this.dval.getValue();
    }

    protected boolean getBoolValue() {
        return this.bval.getValue();
    }

    protected String getStringValue() {
        return this.sval.getStringRep();
    }

    protected LValueType getType() {
        return this.type;
    }

    protected MSSyntaxTree getTreeValue() {
        return this.tval;
    }

    /**
     *
     */
    protected enum LValueType {
        NUM, BOOL, PAIR, STR, DISP, PROCCALL, DEF, NULL
    }
}