package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSBooleanLitNode;
import com.joshuacrotts.minischeme.ast.MSDoubleLitNode;
import com.joshuacrotts.minischeme.ast.MSStringLitNode;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

/**
 *
 */
public class LValue {

    /**
     *
     */
    private LValueType type;

    /**
     *
     */
    private MSDoubleLitNode dval;

    /**
     *
     */
    private MSBooleanLitNode bval;

    /**
     *
     */
    private MSStringLitNode sval;

    /**
     *
     */
    private MSSyntaxTree tval;

    protected LValue(LValueType type) {
        this.type = type;
    }

    protected LValue(MSDoubleLitNode dval) {
        this(LValueType.NUM);
        this.dval = dval;
    }

    protected LValue(MSBooleanLitNode bval) {
        this(LValueType.BOOL);
        this.bval = bval;
    }

    protected LValue(double dval) {
        this(new MSDoubleLitNode(dval));
    }

    protected LValue(boolean bval) {
        this(new MSBooleanLitNode(bval));
    }

    protected LValue(String sval) {
        this(new MSStringLitNode(sval));
    }

    protected LValue(MSSyntaxTree tval) {
        if (tval instanceof MSDoubleLitNode) {
            this.type = LValueType.NUM;
            this.dval = ((MSDoubleLitNode) tval);
        } else if (tval instanceof MSBooleanLitNode) {
            this.type = LValueType.BOOL;
            this.bval = ((MSBooleanLitNode) tval);
        } else if (tval instanceof MSStringLitNode) {
            this.type = LValueType.STR;
            this.sval = ((MSStringLitNode) tval);
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
     * @return
     */
    protected String toDisplayString() {
        switch (this.type) {
            case STR: return this.sval.getStringRep();
            default:
                return this.toString();
        }
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
     *
     */
    protected enum LValueType {
        NUM, BOOL, PAIR, STR, DISP, PROCCALL, DEF, NULL
    }
}