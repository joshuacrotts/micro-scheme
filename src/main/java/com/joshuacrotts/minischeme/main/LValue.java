package com.joshuacrotts.minischeme.main;

/**
 *
 */
public class LValue {

    /**
     *
     */
    protected LValueType type;

    /**
     *
     */
    protected double dval;

    /**
     *
     */
    protected boolean bval;

    protected LValue(LValueType type) {
        this.type = type;
    }

    protected LValue(double dval) {
        this(LValueType.NUM);
        this.dval = dval;
    }

    protected LValue(boolean bval) {
        this(LValueType.BOOL);
        this.bval = bval;
    }

    protected LValue() {
        this(LValueType.NULL);
    }

    /**
     *
     */
    protected enum LValueType {
        NUM, BOOL, STR, DEF, NULL
    }

    @Override
    public String toString() {
        switch (this.type) {
            case NUM:
                return ((int) this.dval == this.dval)
                        ? Integer.toString((int) this.dval)
                        : Double.toString(this.dval);
            case BOOL:
                return this.bval ? "#t" : "#f";
        }
        return "";
    }
}