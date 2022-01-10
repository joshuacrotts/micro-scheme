package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.MSNumberNode;
import com.joshuacrotts.minischeme.ast.MSPairNode;
import com.joshuacrotts.minischeme.ast.MSStringNode;
import com.joshuacrotts.minischeme.ast.MSVectorNode;
import com.joshuacrotts.minischeme.parser.MSSemanticError;

public class MiniSchemeOperatorInterpreter {

    /**
     * @param lhs
     * @param opType
     * @return
     */
    protected static LValue interpretPrimitiveUnaryOperator(final int opType, final LValue lhs) throws MSSemanticError {
        switch (opType) {
            case MiniSchemeParser.DISPLAY:
                System.out.println(lhs.toDisplayString());
                return new LValue(LValue.LValueType.DISP);
            case MiniSchemeParser.SIN: return new LValue(Math.sin(lhs.getDoubleValue()));
            case MiniSchemeParser.COS: return new LValue(Math.cos(lhs.getDoubleValue()));
            case MiniSchemeParser.TAN: return new LValue(Math.tan(lhs.getDoubleValue()));
            case MiniSchemeParser.ASIN: return new LValue(Math.asin(lhs.getDoubleValue()));
            case MiniSchemeParser.ACOS: return new LValue(Math.acos(lhs.getDoubleValue()));
            case MiniSchemeParser.ATAN: return new LValue(Math.atan(lhs.getDoubleValue()));
            case MiniSchemeParser.SQRT: return new LValue(Math.sqrt(lhs.getDoubleValue()));
            case MiniSchemeParser.ROUND: return new LValue(Math.round(lhs.getDoubleValue()));
            case MiniSchemeParser.FLOOR: return new LValue(Math.floor(lhs.getDoubleValue()));
            case MiniSchemeParser.CEILING: return new LValue(Math.ceil(lhs.getDoubleValue()));
            case MiniSchemeParser.TRUNCATE: return new LValue((int) lhs.getDoubleValue());
            case MiniSchemeParser.TRUE_FN: return new LValue(lhs.getBoolValue());
            case MiniSchemeParser.FALSE_FN:
            case MiniSchemeParser.LOGICAL_NOT: return new LValue(!lhs.getBoolValue());
            case MiniSchemeParser.CAR: return new LValue(((MSPairNode) lhs.getTreeValue()).getCar());
            case MiniSchemeParser.CDR: return new LValue(((MSPairNode) lhs.getTreeValue()).getCdr());
            case MiniSchemeParser.NULL_FN: return new LValue(lhs.isLPair() && ((lhs.getTreeValue() == null) || ((MSPairNode) lhs.getTreeValue()).isNull()));
            case MiniSchemeParser.NUMBER_FN: return new LValue(lhs.getType() == LValue.LValueType.NUM);
            case MiniSchemeParser.BOOL_FN: return new LValue(lhs.getType() == LValue.LValueType.BOOL);
            case MiniSchemeParser.STRING_FN: return new LValue(lhs.getType() == LValue.LValueType.STR);
            case MiniSchemeParser.SYMBOL_FN: return new LValue(lhs.getType() != LValue.LValueType.PAIR && lhs.getType() != LValue.LValueType.STR && lhs.getType() != LValue.LValueType.BOOL && lhs.getType() != LValue.LValueType.NUM);
            case MiniSchemeParser.VECTOR_FN: return new LValue(lhs.getType() == LValue.LValueType.VECTOR);
            case MiniSchemeParser.PAIR_FN:
                // A "pair" cannot be the empty list.
                return new LValue(lhs.getTreeValue() != null
                        && !((MSPairNode) lhs.getTreeValue()).isNull()
                        && lhs.isLPair());
            case MiniSchemeParser.STRLEN_FN: return new LValue(lhs.getStringValue().length());
            case MiniSchemeParser.VECTORLEN_FN: return new LValue(((MSVectorNode) lhs.getTreeValue()).size());
            case MiniSchemeParser.NUMTOSTR_FN: return new LValue(new MSStringNode(lhs.toString()));
            case MiniSchemeParser.STRTONUM_FN: return new LValue(new MSNumberNode(Double.parseDouble(lhs.getStringValue())));
            case MiniSchemeParser.TODEG_FN: return new LValue(new MSNumberNode(Math.toDegrees(lhs.getDoubleValue())));
            case MiniSchemeParser.TORAD_FN: return new LValue(new MSNumberNode(Math.toRadians(lhs.getDoubleValue())));
            default:
                throw new MSSemanticError("invalid unary operator type " + opType);
        }
    }

    /**
     * @param lhs
     * @param opType
     * @param rhs
     * @return
     */
    protected static LValue interpretPrimitiveBinaryOperator(int opType, LValue lhs, LValue rhs) throws MSSemanticError {
        switch (opType) {
            case MiniSchemeParser.LOGICAL_EQ: return new LValue(lhs.getDoubleValue() == rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_NE: return new LValue(lhs.getDoubleValue() != rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_LT: return new LValue(lhs.getDoubleValue() < rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_LE: return new LValue(lhs.getDoubleValue() <= rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_GT: return new LValue(lhs.getDoubleValue() > rhs.getDoubleValue());
            case MiniSchemeParser.LOGICAL_GE: return new LValue(lhs.getDoubleValue() >= rhs.getDoubleValue());
            case MiniSchemeParser.STREQ_FN: return new LValue(lhs.getStringValue().equals(rhs.getStringValue()));
            case MiniSchemeParser.STRLT_FN: return new LValue(lhs.getStringValue().compareTo(rhs.getStringValue()) < 0);
            case MiniSchemeParser.STRLE_FN: return new LValue(lhs.getStringValue().compareTo(rhs.getStringValue()) <= 0);
            case MiniSchemeParser.STRGT_FN: return new LValue(lhs.getStringValue().compareTo(rhs.getStringValue()) > 0);
            case MiniSchemeParser.STRGE_FN: return new LValue(lhs.getStringValue().compareTo(rhs.getStringValue()) >= 0);
            case MiniSchemeParser.RANDINT_FN: return new LValue(MSUtils.randomInt((int) lhs.getDoubleValue(), (int) rhs.getDoubleValue()));
            case MiniSchemeParser.RANDDOUBLE_FN: return new LValue(MSUtils.randomDouble(lhs.getDoubleValue(), rhs.getDoubleValue()));
            case MiniSchemeParser.VECTOR_REF_FN: return new LValue(lhs.getTreeValue().getChild((int) rhs.getDoubleValue()));
            default:
                throw new MSSemanticError("invalid binary operator type " + opType);
        }
    }

    /**
     *
     * @param op1
     * @param op2
     * @param op3
     * @param opType
     * @return
     * @throws MSSemanticError
     */
    protected static LValue interpretPrimitiveTernaryOperator(final int opType, final LValue op1,
                                                              final LValue op2, final LValue op3) throws MSSemanticError {
        switch (opType) {
            case MiniSchemeParser.RAND_FN: return new LValue(Math.random());
            case MiniSchemeParser.STRSUBSTR:
                return new LValue(op1.getStringValue().substring((int) op2.getDoubleValue(), (int) op3.getDoubleValue()));
            default:
                throw new IllegalArgumentException("Internal interpreter error - invalid primitive ternary operator.");
        }
    }

    /**
     *
     * @param opType
     * @param lhs
     * @param rhs
     * @return
     */
    protected static LValue interpretPrimitiveNaryOperator(final int opType, final LValue lhs, final LValue rhs) {
        switch (opType) {
            case MiniSchemeParser.PLUS: return new LValue(lhs.getDoubleValue() + rhs.getDoubleValue());
            case MiniSchemeParser.MINUS: return new LValue(lhs.getDoubleValue() - rhs.getDoubleValue());
            case MiniSchemeParser.STAR: return new LValue(lhs.getDoubleValue() * rhs.getDoubleValue());
            case MiniSchemeParser.SLASH: return new LValue(lhs.getDoubleValue() / rhs.getDoubleValue());
            case MiniSchemeParser.MODULO: return new LValue(lhs.getDoubleValue() % rhs.getDoubleValue());
            case MiniSchemeParser.EXPONENTIATION: return new LValue(Math.pow(lhs.getDoubleValue(), rhs.getDoubleValue()));
            case MiniSchemeParser.STRAPPEND_FN: return new LValue(lhs.getStringValue() + rhs.getStringValue());
            case MiniSchemeParser.LOGICAL_AND: return new LValue(lhs.getBoolValue() && rhs.getBoolValue());
            case MiniSchemeParser.LOGICAL_OR: return new LValue(lhs.getBoolValue() || rhs.getBoolValue());
            case MiniSchemeParser.FALSE_FN: return new LValue(!lhs.getBoolValue() && !rhs.getBoolValue());
            case MiniSchemeParser.EQ_FN: return interpretEqFn(lhs, rhs);
            case MiniSchemeParser.EQUAL_FN: return interpretEqualFn(lhs, rhs);
            default:
                throw new IllegalArgumentException("Internal interpreter error - invalid primitive n-ary operator.");
        }
    }

    /**
     * @param lhs
     * @param rhs
     * @return
     */
    private static LValue interpretEqualFn(final LValue lhs, final LValue rhs) {
        if (lhs.getType() == rhs.getType()) {
            switch (lhs.getType()) {
                case NUM: return new LValue(lhs.getDoubleValue() == rhs.getDoubleValue());
                case BOOL: return new LValue(lhs.getBoolValue() == rhs.getBoolValue());
                case STR: return new LValue(lhs.getStringValue().equals(rhs.getStringValue()));
                case VECTOR:
                case PAIR: return new LValue(lhs.toString().equals(rhs.toString()));
                case NULL: return new LValue(true);
                default:
                    throw new IllegalArgumentException("Internal interpreter error " +
                            "- cannot use equal? procedure on operands of type " + lhs.getType());
            }
        }
        return new LValue(false);
    }

    /**
     * @param lhs
     * @param rhs
     * @return
     */
    private static LValue interpretEqFn(final LValue lhs, final LValue rhs) {
        // If they're the same reference then return true.
        if (lhs == rhs) {
            return new LValue(true);
        } else if (lhs.getType() == rhs.getType()) {
            // Doubles are a special case.
            if (lhs.isLNumber()) {
                return new LValue(lhs.getDoubleValue() == rhs.getDoubleValue());
            } else if (lhs.isLSymbol()) {
                return new LValue(lhs.toString().equals(rhs.toString()));
            } else if (lhs.isLString()) {
                return new LValue(lhs.getStringValue().equals(rhs.getStringValue()));
            }
        }

        return new LValue(false);
    }
}
