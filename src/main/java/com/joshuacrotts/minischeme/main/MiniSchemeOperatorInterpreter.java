package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.parser.MSArgumentMismatchException;
import com.joshuacrotts.minischeme.parser.MSSemanticException;

import java.util.Optional;

public class MiniSchemeOperatorInterpreter {

    /**
     * @param lhs
     * @param opType
     * @return
     */
    protected static LValue interpretPrimitiveUnaryOperator(final int opType, final LValue lhs) throws MSSemanticException {
        switch (opType) {
            case MiniSchemeParser.DISPLAY:
                System.out.println(lhs.toDisplayString());
                return new LValue(LValue.LValueType.DISP);
            case MiniSchemeParser.SIN: return MiniSchemeOperatorInterpreter.interpretSin(lhs);
            case MiniSchemeParser.COS: return MiniSchemeOperatorInterpreter.interpretCos(lhs);
            case MiniSchemeParser.TAN: return MiniSchemeOperatorInterpreter.interpretTan(lhs);
            case MiniSchemeParser.ASIN: return MiniSchemeOperatorInterpreter.interpretAsin(lhs);
            case MiniSchemeParser.ACOS: return MiniSchemeOperatorInterpreter.interpretAcos(lhs);
            case MiniSchemeParser.ATAN: return MiniSchemeOperatorInterpreter.interpretAtan(lhs);
            case MiniSchemeParser.SINH: return MiniSchemeOperatorInterpreter.interpretSinh(lhs);
            case MiniSchemeParser.COSH: return MiniSchemeOperatorInterpreter.interpretCosh(lhs);
            case MiniSchemeParser.TANH: return MiniSchemeOperatorInterpreter.interpretTanh(lhs);
            case MiniSchemeParser.SQRT: return MiniSchemeOperatorInterpreter.interpretSqrt(lhs);
            case MiniSchemeParser.ROUND: return MiniSchemeOperatorInterpreter.interpretRound(lhs);
            case MiniSchemeParser.FLOOR: return MiniSchemeOperatorInterpreter.interpretFloor(lhs);
            case MiniSchemeParser.CEILING: return MiniSchemeOperatorInterpreter.interpretCeiling(lhs);
            case MiniSchemeParser.TRUNCATE: return MiniSchemeOperatorInterpreter.interpretTruncate(lhs);
            case MiniSchemeParser.LOGICAL_NOT: return MiniSchemeOperatorInterpreter.interpretLogicalNot(lhs);
            case MiniSchemeParser.CAR: return MiniSchemeOperatorInterpreter.interpretCar(lhs);
            case MiniSchemeParser.CDR: return MiniSchemeOperatorInterpreter.interpretCdr(lhs);
            case MiniSchemeParser.NULL_FN: return MiniSchemeOperatorInterpreter.interpretNullFn(lhs);
            case MiniSchemeParser.NUMBER_FN: return MiniSchemeOperatorInterpreter.interpretNumberFn(lhs);
            case MiniSchemeParser.BOOL_FN: return MiniSchemeOperatorInterpreter.interpretBoolFn(lhs);
            case MiniSchemeParser.CHAR_FN: return MiniSchemeOperatorInterpreter.interpretCharFn(lhs);
            case MiniSchemeParser.STRING_FN: return MiniSchemeOperatorInterpreter.interpretStringFn(lhs);
            case MiniSchemeParser.SYMBOL_FN: return MiniSchemeOperatorInterpreter.interpretSymbolFn(lhs);
            case MiniSchemeParser.VECTOR_FN: return MiniSchemeOperatorInterpreter.interpretVectorFn(lhs);
            case MiniSchemeParser.PAIR_FN: return MiniSchemeOperatorInterpreter.interpretPairFn(lhs);
            case MiniSchemeParser.PROCEDURE_FN: return MiniSchemeOperatorInterpreter.interpretProcedureFn(lhs);
            case MiniSchemeParser.LAMBDA_FN: return MiniSchemeOperatorInterpreter.interpretLambdaFn(lhs);
            case MiniSchemeParser.STRLEN_FN: return MiniSchemeOperatorInterpreter.interpretStringLengthFn(lhs);
            case MiniSchemeParser.VECTORLEN_FN: return MiniSchemeOperatorInterpreter.interpretVectorLengthFn(lhs);
            case MiniSchemeParser.NUMTOSTR_FN: return MiniSchemeOperatorInterpreter.interpretNumberToStringFn(lhs);
            case MiniSchemeParser.STRTONUM_FN: return MiniSchemeOperatorInterpreter.interpretStringToNumberFn(lhs);
            case MiniSchemeParser.LISTTOSTR_FN: return MiniSchemeOperatorInterpreter.interpretListToStringFn(lhs);
            case MiniSchemeParser.STRTOLIST_FN: return MiniSchemeOperatorInterpreter.interpretStringToListFn(lhs);
            case MiniSchemeParser.TODEG_FN: return MiniSchemeOperatorInterpreter.interpretToDegrees(lhs);
            case MiniSchemeParser.TORAD_FN: return MiniSchemeOperatorInterpreter.interpretToRadians(lhs);
            case MiniSchemeParser.RAND_FN: return MiniSchemeOperatorInterpreter.interpretRandomFn(lhs);
            default:
                throw new MSSemanticException("invalid unary operator type " + opType);
        }
    }

    /**
     * @param lhs
     * @param opType
     * @param rhs
     * @return
     */
    protected static LValue interpretPrimitiveBinaryOperator(int opType, LValue lhs, LValue rhs) throws MSSemanticException {
        switch (opType) {
            case MiniSchemeParser.MODULO: return MiniSchemeOperatorInterpreter.interpretModulo(lhs, rhs);
            case MiniSchemeParser.REMAINDER: return MiniSchemeOperatorInterpreter.interpretRemainder(lhs, rhs);
            case MiniSchemeParser.LOGICAL_EQ: return MiniSchemeOperatorInterpreter.interpretLogicalEq(lhs, rhs);
            case MiniSchemeParser.LOGICAL_LT: return MiniSchemeOperatorInterpreter.interpretLogicalLt(lhs, rhs);
            case MiniSchemeParser.LOGICAL_LE: return MiniSchemeOperatorInterpreter.interpretLogicalLe(lhs, rhs);
            case MiniSchemeParser.LOGICAL_GT: return MiniSchemeOperatorInterpreter.interpretLogicalGt(lhs, rhs);
            case MiniSchemeParser.LOGICAL_GE: return MiniSchemeOperatorInterpreter.interpretLogicalGe(lhs, rhs);
            case MiniSchemeParser.STREQ_FN: return MiniSchemeOperatorInterpreter.interpretStringEqFn(lhs, rhs);
            case MiniSchemeParser.STRLT_FN: return MiniSchemeOperatorInterpreter.interpretStringLtFn(lhs, rhs);
            case MiniSchemeParser.STRLE_FN: return MiniSchemeOperatorInterpreter.interpretStringLeFn(lhs, rhs);
            case MiniSchemeParser.STRGT_FN: return MiniSchemeOperatorInterpreter.interpretStringGtFn(lhs, rhs);
            case MiniSchemeParser.STRGE_FN: return MiniSchemeOperatorInterpreter.interpretStringGeFn(lhs, rhs);
            case MiniSchemeParser.RANDINT_FN: return MiniSchemeOperatorInterpreter.interpretRandomIntFn(lhs, rhs);
            case MiniSchemeParser.RANDDOUBLE_FN: return MiniSchemeOperatorInterpreter.interpretRandomDoubleFn(lhs, rhs);
            case MiniSchemeParser.VECTOR_REF_FN: return MiniSchemeOperatorInterpreter.interpretVectorRefFn(lhs, rhs);
            default:
                throw new MSSemanticException("invalid binary operator type " + opType);
        }
    }

    /**
     *
     * @param op1
     * @param op2
     * @param op3
     * @param opType
     * @return
     * @throws MSSemanticException
     */
    protected static LValue interpretPrimitiveTernaryOperator(final int opType, final LValue op1,
                                                              final LValue op2, final LValue op3) throws MSSemanticException {
        switch (opType) {
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
            case MiniSchemeParser.EQ_FN: return interpretEqFn(lhs, rhs);
            case MiniSchemeParser.EQUAL_FN: return interpretEqualFn(lhs, rhs);
            default:
                throw new IllegalArgumentException("Internal interpreter error - invalid primitive n-ary operator.");
        }
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretSin(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("sin", "number", lhs.getType().toString());
        }
        return new LValue(Math.sin(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretCos(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("cos", "number", lhs.getType().toString());
        }
        return new LValue(Math.cos(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretTan(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("tan", "number", lhs.getType().toString());
        }
        return new LValue(Math.tan(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretAsin(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("asin", "number", lhs.getType().toString());
        }
        return new LValue(Math.asin(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretAcos(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("acos", "number", lhs.getType().toString());
        }
        return new LValue(Math.acos(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretAtan(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("atan", "number", lhs.getType().toString());
        }
        return new LValue(Math.atan(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretSinh(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("sinh", "number", lhs.getType().toString());
        }
        return new LValue(Math.sinh(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretCosh(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("cosh", "number", lhs.getType().toString());
        }
        return new LValue(Math.cosh(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretTanh(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("tanh", "number", lhs.getType().toString());
        }
        return new LValue(Math.tanh(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSSemanticException
     */
    private static LValue interpretSqrt(final LValue lhs) throws MSSemanticException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("sqrt", "number", lhs.getType().toString());
        } else if (lhs.getDoubleValue() < 0) {
            throw new MSSemanticException("cannot use sqrt on negative number " + lhs);
        }
        return new LValue(Math.sqrt(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretRound(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("round", "number", lhs.getType().toString());
        }
        return new LValue(Math.round(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretCeiling(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("ceiling", "number", lhs.getType().toString());
        }
        return new LValue(Math.ceil(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretFloor(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("floor", "number", lhs.getType().toString());
        }
        return new LValue(Math.floor(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretTruncate(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("truncate", "number", lhs.getType().toString());
        }
        return new LValue((int) lhs.getDoubleValue());
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretLogicalNot(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLBool()) {
            throw new MSArgumentMismatchException("logical not", "boolean", lhs.getType().toString());
        }
        return new LValue(!lhs.getBoolValue());
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretCar(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLPair()) {
            throw new MSArgumentMismatchException("car", "list/pair", lhs.getType().toString());
        }
        return new LValue(((MSPairNode) lhs.getTreeValue()).getCar());
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretCdr(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLPair()) {
            throw new MSArgumentMismatchException("cdr", "list/pair", lhs.getType().toString());
        }
        return new LValue(((MSPairNode) lhs.getTreeValue()).getCdr());
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretNullFn(final LValue lhs) {
        return new LValue(lhs.isLPair() &&
                ((lhs.getTreeValue() == null) || ((MSPairNode) lhs.getTreeValue()).isNull()));
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretNumberFn(final LValue lhs) {
        return new LValue(lhs.isLNumber());
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretBoolFn(final LValue lhs) {
        return new LValue(lhs.isLBool());
    }

    /**
     * Determines if an expression is a character. A character is defined as
     * a char literal in the form #\ch where 'ch' is an ascii character. A
     * character is also defined as a symbol where its corresponding expression
     * is a character e.g., '#\x. This is handled implicitly by the interpreter.
     *
     * @param lhs LValue object to compare.
     * @return true if lvalue passed is a char, false otherwise.
     */
    private static LValue interpretCharFn(final LValue lhs) {
        return new LValue(lhs.isLChar());
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretStringFn(final LValue lhs) {
        return new LValue(lhs.isLString());
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretSymbolFn(final LValue lhs) {
        return new LValue(lhs.isLSymbol());
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretPairFn(final LValue lhs) {
        return new LValue(lhs.getTreeValue() != null
                && !((MSPairNode) lhs.getTreeValue()).isNull()
                && lhs.isLPair());
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretVectorFn(final LValue lhs) {
        return new LValue(lhs.isLVector());
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretProcedureFn(final LValue lhs) {
        return new LValue(lhs.isLProcCall());
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretLambdaFn(final LValue lhs) {
        return new LValue(lhs.isLLambdaCall());
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSSemanticException
     */
    private static LValue interpretStringLengthFn(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string-length", "string", lhs.getType().toString());
        }
        return new LValue(lhs.getStringValue().length());
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSSemanticException
     */
    private static LValue interpretVectorLengthFn(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLVector()) {
            throw new MSArgumentMismatchException("vector-length", "vector", lhs.getType().toString());
        }
        return new LValue(((MSVectorNode) lhs.getTreeValue()).size());
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSSemanticException
     */
    private static LValue interpretNumberToStringFn(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("number->string", "number", lhs.getType().toString());
        }
        // If it's possible to show it as an integer then we should.
        return new LValue(String.valueOf(lhs.toString()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSSemanticException
     */
    private static LValue interpretStringToNumberFn(final LValue lhs) throws MSSemanticException {
        if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string->number", "string", lhs.getType().toString());
        }

        try {
            return new LValue(Double.parseDouble(lhs.getStringValue()));
        } catch (NumberFormatException ex) {
            throw new MSSemanticException("cannot convert non-string " + lhs.getStringValue() + " to number");
        }
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretListToStringFn(final LValue lhs) throws MSArgumentMismatchException {
        // Compare each element of the list to make sure the head is a char.
        StringBuilder sb = new StringBuilder();
        MSSyntaxTree curr = lhs.getTreeValue();
        int idx = 0;
        while (true) {
            // Once we hit the end of the list, just break.
            if (curr == null || (curr.isPair() && ((MSPairNode) curr).isNull())) {
                break;
            } else if (!curr.isList() && !curr.isPair()) {
                throw new MSArgumentMismatchException("list->string list", "char at index " + idx, curr.getNodeType().toString());
            }

            MSPairNode pair = (MSPairNode) curr;
            if (!pair.getCar().isChar()) {
                throw new MSArgumentMismatchException("list->string list", "char at index " + idx, curr.getNodeType().toString());
            }
            sb.append(pair.getCar().getStringRep());
            curr = pair.getCdr();
        }

        return new LValue(new MSStringNode(sb.toString()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringToListFn(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string->list", "string", lhs.getType().toString());
        }

        // Grab each character, convert it to a MSCharacterNode, then add it to the pair.
        MSSyntaxTree parentPair = null;
        MSPairNode prevPair = new MSPairNode();
        String str = lhs.getStringValue();
        for (int i = str.length() - 1; i >= 0; i--) {
            MSSyntaxTree rchar = new MSCharacterNode(str.charAt(i));
            prevPair = new MSPairNode(MSNodeType.LIST, rchar, prevPair);
        }
        // If they enter the empty list, then we need to add a "blank" pair node.
        parentPair = Optional.ofNullable(prevPair).orElse(new MSPairNode());
        return new LValue(parentPair);
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretToDegrees(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("to-degrees", "number", lhs.getType().toString());
        }
        return new LValue(Math.toDegrees(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretToRadians(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("to-radians", "number", lhs.getType().toString());
        }
        return new LValue(Math.toRadians(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @return
     */
    private static LValue interpretRandomFn(final LValue lhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("random", "number", lhs.getType().toString());
        }
        return new LValue(Math.random() * lhs.getDoubleValue());
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
                case SYM:
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
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretModulo(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("modulo", "number", lhs.getType().toString());
        } else if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("modulo", "number", rhs.getType().toString());
        }

        return new LValue(lhs.getDoubleValue() % rhs.getDoubleValue());
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretRemainder(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("modulo", "number", lhs.getType().toString());
        } else if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("modulo", "number", rhs.getType().toString());
        }

        return new LValue((lhs.getDoubleValue() % rhs.getDoubleValue()) * Math.signum(lhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretLogicalEq(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("=", "number", lhs.getType().toString());
        } else if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("=", "number", rhs.getType().toString());
        }

        return new LValue(lhs.getDoubleValue() == rhs.getDoubleValue());
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretLogicalLt(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("<", "number", lhs.getType().toString());
        } else if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("<", "number", rhs.getType().toString());
        }

        return new LValue(lhs.getDoubleValue() < rhs.getDoubleValue());
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretLogicalLe(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("<=", "number", lhs.getType().toString());
        } else if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException("<=", "number", rhs.getType().toString());
        }

        return new LValue(lhs.getDoubleValue() <= rhs.getDoubleValue());
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretLogicalGt(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException(">", "number", lhs.getType().toString());
        } else if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException(">", "number", rhs.getType().toString());
        }

        return new LValue(lhs.getDoubleValue() > rhs.getDoubleValue());
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretLogicalGe(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException(">=", "number", lhs.getType().toString());
        } else if (!lhs.isLNumber()) {
            throw new MSArgumentMismatchException(">=", "number", rhs.getType().toString());
        }

        return new LValue(lhs.getDoubleValue() >= rhs.getDoubleValue());
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringEqFn(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string=?", "string", lhs.getType().toString());
        } else if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string=?", "string", rhs.getType().toString());
        }

        return new LValue(lhs.getStringValue().equals(rhs.getStringValue()));
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringLtFn(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string<?", "number", lhs.getType().toString());
        } else if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string<?", "number", rhs.getType().toString());
        }

        return new LValue(lhs.getStringValue().compareTo(rhs.getStringValue()) < 0);
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringLeFn(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string<=?", "string", lhs.getType().toString());
        } else if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string<=?", "string", rhs.getType().toString());
        }

        return new LValue(lhs.getStringValue().compareTo(rhs.getStringValue()) <= 0);
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringGtFn(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string>?", "string", lhs.getType().toString());
        } else if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string>?", "string", rhs.getType().toString());
        }

        return new LValue(lhs.getStringValue().compareTo(rhs.getStringValue()) > 0);
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringGeFn(final LValue lhs, final LValue rhs) throws MSArgumentMismatchException {
        if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string>=?", "string", lhs.getType().toString());
        } else if (!lhs.isLString()) {
            throw new MSArgumentMismatchException("string>=?", "string", rhs.getType().toString());
        }

        return new LValue(lhs.getStringValue().compareTo(rhs.getStringValue()) >= 0);
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private static LValue interpretRandomIntFn(final LValue lhs, final LValue rhs) {
        return new LValue(MSUtils.randomInt((int) lhs.getDoubleValue(), (int) rhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private static LValue interpretRandomDoubleFn(final LValue lhs, final LValue rhs) {
        return new LValue(MSUtils.randomDouble(lhs.getDoubleValue(), rhs.getDoubleValue()));
    }

    /**
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private static LValue interpretVectorRefFn(final LValue lhs, final LValue rhs) throws MSSemanticException {
        if (!lhs.isLVector()) {
            throw new MSArgumentMismatchException("vector-ref", "vector", lhs.getType().toString());
        } else if (!rhs.isLNumber()) {
            throw new MSSemanticException("cannot reference vector from non-integer index");
        }
        return new LValue(lhs.getTreeValue().getChild((int) rhs.getDoubleValue()));
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
