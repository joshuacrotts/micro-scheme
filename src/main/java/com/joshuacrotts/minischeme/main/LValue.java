package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSBooleanLitNode;
import com.joshuacrotts.minischeme.ast.MSDoubleLitNode;
import com.joshuacrotts.minischeme.ast.MSNodeType;
import com.joshuacrotts.minischeme.ast.MSPairNode;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;

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

    /**
     *
     */
    protected MSSyntaxTree tval;

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

    protected LValue(MSSyntaxTree tval) {
        if (tval instanceof MSDoubleLitNode) {
            this.type = LValueType.NUM;
            this.dval = ((MSDoubleLitNode) tval).getValue();
        } else if (tval instanceof MSBooleanLitNode) {
            this.type = LValueType.BOOL;
            this.bval = ((MSBooleanLitNode) tval).getValue();
        } else {
            this.type = LValueType.PAIR;
            this.tval = tval;
        }
    }

    protected LValue() {
        this(LValueType.NULL);
    }

    /**
     *
     */
    protected enum LValueType {
        NUM, BOOL, PAIR, STR, DEF, NULL
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
            case PAIR:
                return ((MSPairNode) this.tval).getStringRep();
        }
        return "";
    }
}