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

    protected LValue(LValueType type) {
        this.type = type;
    }

    protected LValue(double dval) {
        this(LValueType.LVAL_NUM);
        this.dval = dval;
    }

    protected LValue() {
        this(LValueType.LVAL_NULL);
    }

    /**
     *
     */
    protected enum LValueType {
        LVAL_NUM, LVAL_STR, LVAL_DEF, LVAL_NULL
    }

    @Override
    public String toString() {
        switch (this.type) {
            case LVAL_NUM: return Double.toString(this.dval);
        }
        return "";
    }
}