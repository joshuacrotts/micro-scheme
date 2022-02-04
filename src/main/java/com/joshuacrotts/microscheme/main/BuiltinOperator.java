/******************************************************************************
 *  File: BuiltinOperator.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/26/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.main;

import ch.obermuhlner.math.big.BigComplex;
import ch.obermuhlner.math.big.BigComplexMath;
import ch.obermuhlner.math.big.BigDecimalMath;
import com.joshuacrotts.microscheme.ast.*;
import com.joshuacrotts.microscheme.parser.MSArgumentMismatchException;
import com.joshuacrotts.microscheme.parser.MSFunction;
import com.joshuacrotts.microscheme.parser.MSSemanticException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public final class BuiltinOperator {

    /**
     * Mapping of string procedure identifiers to their respective function calls.
     */
    private static final Map<String, MSFunction<ArrayList<LValue>, LValue>> OPERATORS;

    /**
     * Random object to continuously use throughout interpreter execution.
     */
    private static final Random RANDOM = new Random();

    static {
        OPERATORS = new HashMap<>();
        OPERATORS.put("display", BuiltinOperator::interpretDisplay);
        OPERATORS.put("+", BuiltinOperator::interpretAdd);
        OPERATORS.put("-", BuiltinOperator::interpretSubtract);
        OPERATORS.put("*", BuiltinOperator::interpretMultiply);
        OPERATORS.put("/", BuiltinOperator::interpretDivide);
        OPERATORS.put("**", BuiltinOperator::interpretPower);
        OPERATORS.put("log", BuiltinOperator::interpretLog);
        OPERATORS.put("floor", BuiltinOperator::interpretFloor);
        OPERATORS.put("ceiling", BuiltinOperator::interpretCeiling);
        OPERATORS.put("round", BuiltinOperator::interpretRound);
        OPERATORS.put("truncate", BuiltinOperator::interpretTruncate);
        OPERATORS.put("modulo", BuiltinOperator::interpretModulo);
        OPERATORS.put("remainder", BuiltinOperator::interpretRemainder);
        OPERATORS.put("sin", BuiltinOperator::interpretSin);
        OPERATORS.put("cos", BuiltinOperator::interpretCos);
        OPERATORS.put("tan", BuiltinOperator::interpretTan);
        OPERATORS.put("sinh", BuiltinOperator::interpretSinh);
        OPERATORS.put("cosh", BuiltinOperator::interpretCosh);
        OPERATORS.put("tanh", BuiltinOperator::interpretTanh);
        OPERATORS.put("asin", BuiltinOperator::interpretAsin);
        OPERATORS.put("acos", BuiltinOperator::interpretAcos);
        OPERATORS.put("atan", BuiltinOperator::interpretAtan);
        OPERATORS.put("asinh", BuiltinOperator::interpretAsinh);
        OPERATORS.put("acosh", BuiltinOperator::interpretAcosh);
        OPERATORS.put("atanh", BuiltinOperator::interpretAtanh);
        OPERATORS.put("<", BuiltinOperator::interpretLess);
        OPERATORS.put("<=", BuiltinOperator::interpretLessEqual);
        OPERATORS.put(">", BuiltinOperator::interpretGreater);
        OPERATORS.put(">=", BuiltinOperator::interpretGreaterEqual);
        OPERATORS.put("=", BuiltinOperator::interpretNumericEqual);
        OPERATORS.put("not", BuiltinOperator::interpretNot);
        OPERATORS.put("and", BuiltinOperator::interpretAnd);
        OPERATORS.put("or", BuiltinOperator::interpretOr);
        OPERATORS.put("equal?", BuiltinOperator::interpretEqualPredicate);
        OPERATORS.put("eq?", BuiltinOperator::interpretEqPredicate);
        OPERATORS.put("cons", BuiltinOperator::interpretConsFunction);
        OPERATORS.put("list", BuiltinOperator::interpretListFunction);
        OPERATORS.put("car", BuiltinOperator::interpretCarFunction);
        OPERATORS.put("cdr", BuiltinOperator::interpretCdrFunction);
        OPERATORS.put("vector", BuiltinOperator::interpretVectorFunction);
        OPERATORS.put("vector-ref", BuiltinOperator::interpretVectorRefFunction);
        OPERATORS.put("vector-length", BuiltinOperator::interpretVectorLengthFunction);
        OPERATORS.put("null?", BuiltinOperator::interpretNullPredicate);
        OPERATORS.put("number?", BuiltinOperator::interpretNumberPredicate);
        OPERATORS.put("char?", BuiltinOperator::interpretCharPredicate);
        OPERATORS.put("string?", BuiltinOperator::interpretStringPredicate);
        OPERATORS.put("symbol?", BuiltinOperator::interpretSymbolPredicate);
        OPERATORS.put("pair?", BuiltinOperator::interpretPairPredicate);
        OPERATORS.put("list?", BuiltinOperator::interpretListPredicate);
        OPERATORS.put("vector?", BuiltinOperator::interpretVectorPredicate);
        OPERATORS.put("string-append", BuiltinOperator::interpretStringAppendFunction);
        OPERATORS.put("string-length", BuiltinOperator::interpretStringLengthFunction);
        OPERATORS.put("string<?", BuiltinOperator::interpretStringLess);
        OPERATORS.put("string<=?", BuiltinOperator::interpretStringLessEqual);
        OPERATORS.put("string>?", BuiltinOperator::interpretStringGreater);
        OPERATORS.put("string>=?", BuiltinOperator::interpretStringGreaterEqual);
        OPERATORS.put("char<?", BuiltinOperator::interpretCharLess);
        OPERATORS.put("char<=?", BuiltinOperator::interpretCharLessEqual);
        OPERATORS.put("char>?", BuiltinOperator::interpretCharGreater);
        OPERATORS.put("char>=?", BuiltinOperator::interpretCharGreaterEqual);
        OPERATORS.put("number->string", BuiltinOperator::interpretNumberStringFunction);
        OPERATORS.put("string->number", BuiltinOperator::interpretStringNumberFunction);
        OPERATORS.put("list->string", BuiltinOperator::interpretListStringFunction);
        OPERATORS.put("string->list", BuiltinOperator::interpretStringListFunction);
        OPERATORS.put("random", BuiltinOperator::interpretRandomFunction);
        OPERATORS.put("random-integer", BuiltinOperator::interpretRandomIntegerFunction);
        OPERATORS.put("random-double", BuiltinOperator::interpretRandomDoubleFunction);
        OPERATORS.put("random-set-seed!", BuiltinOperator::interpretRandomSetSeedFunction);
    }

    /**
     *
     * @param expressionNode
     * @return
     */
    public static boolean isBuiltinOperator(final MSSyntaxTree expressionNode) {
        return BuiltinOperator.OPERATORS.containsKey(expressionNode.getStringRep());
    }

    /**
     *
     * @param expressionNode
     * @param evalArguments
     * @param env
     * @return
     * @throws MSSemanticException
     */
    public static LValue interpretBuiltinOperator(final MSSyntaxTree expressionNode,
                                                  final ArrayList<LValue> evalArguments,
                                                  final Environment env) throws MSSemanticException {
        if (!expressionNode.isVariable()) { return null; }
        return BuiltinOperator.OPERATORS.get(expressionNode.getStringRep()).apply(evalArguments);
    }

    private static LValue interpretDisplay(final ArrayList<LValue> displayArguments) {
        if (displayArguments.size() != 1) { throw new MSArgumentMismatchException("display", 1, displayArguments.size()); }
        System.out.println(displayArguments.get(0));
        return null;
    }

    private static LValue interpretAdd(final ArrayList<LValue> addArguments) {
        BigComplex result = addArguments.get(0).getNumberValue();
        for (int i = 1; i < addArguments.size(); i++) {
            result = result.add(addArguments.get(i).getNumberValue());
        }
        return new LValue(result);
    }

    private static LValue interpretSubtract(final ArrayList<LValue> subtractArguments) {
        BigComplex result = subtractArguments.get(0).getNumberValue();
        for (int i = 1; i < subtractArguments.size(); i++) {
            result = result.subtract(subtractArguments.get(i).getNumberValue());
        }
        return subtractArguments.size() == 1 ? new LValue(result.negate()) : new LValue(result);
    }

    private static LValue interpretMultiply(final ArrayList<LValue> multiplyArguments) {
        BigComplex result = multiplyArguments.get(0).getNumberValue();
        for (int i = 1; i < multiplyArguments.size(); i++) { result = result.multiply(multiplyArguments.get(i).getNumberValue()); }
        return new LValue(result);
    }

    private static LValue interpretDivide(final ArrayList<LValue> divideArguments) throws MSSemanticException {
        if (divideArguments.size() != 2) { throw new MSArgumentMismatchException("/", 2, divideArguments.size());}
        BigComplex dividend = divideArguments.get(0).getNumberValue();
        BigComplex divisor = divideArguments.get(1).getNumberValue();

        if (divisor.equals(BigComplex.ZERO)) { throw new MSSemanticException("division by zero"); }
        return new LValue(dividend.divide(divisor, MSNumberNode.PRECISION));
    }

    private static LValue interpretPower(final ArrayList<LValue> powerArguments) throws MSArgumentMismatchException {
        if (powerArguments.size() != 2) { throw new MSArgumentMismatchException("**", 2, powerArguments.size()); }
        BigComplex base = powerArguments.get(0).getNumberValue();
        BigComplex power = powerArguments.get(1).getNumberValue();
        return new LValue(BigComplexMath.pow(base, power, MSNumberNode.PRECISION));
    }

    private static LValue interpretLog(final ArrayList<LValue> logArguments) throws MSArgumentMismatchException {
        if (logArguments.size() != 1) { throw new MSArgumentMismatchException("log", 1, logArguments.size()); }
        BigComplex antilogarithm = logArguments.get(0).getNumberValue();
        return new LValue(BigComplexMath.log(antilogarithm, MSNumberNode.PRECISION));
    }

    private static LValue interpretFloor(final ArrayList<LValue> floorArguments) throws MSArgumentMismatchException {
        if (floorArguments.size() != 1) { throw new MSArgumentMismatchException("floor", 1, floorArguments.size()); }
        BigComplex floorArgument = floorArguments.get(0).getNumberValue();
        if (!floorArgument.isReal()) { throw new MSArgumentMismatchException("floor", "real", floorArgument.toString()); }
        return new LValue(floorArgument.re.setScale(0, RoundingMode.FLOOR));
    }

    private static LValue interpretCeiling(final ArrayList<LValue> ceilingArguments) throws MSArgumentMismatchException {
        if (ceilingArguments.size() != 1) { throw new MSArgumentMismatchException("ceiling", 1, ceilingArguments.size()); }
        BigComplex ceilingArgument = ceilingArguments.get(0).getNumberValue();
        if (!ceilingArgument.isReal()) { throw new MSArgumentMismatchException("ceiling", "real", ceilingArgument.toString()); }
        return new LValue(ceilingArgument.re.setScale(0, RoundingMode.CEILING));
    }

    private static LValue interpretRound(final ArrayList<LValue> roundArguments) throws MSArgumentMismatchException {
        if (roundArguments.size() != 1) { throw new MSArgumentMismatchException("round", 1, roundArguments.size()); }
        BigComplex roundArgument = roundArguments.get(0).getNumberValue();
        if (!roundArgument.isReal()) { throw new MSArgumentMismatchException("round", "real", roundArgument.toString()); }
        return new LValue(roundArgument.re.setScale(0, RoundingMode.HALF_UP));
    }

    private static LValue interpretTruncate(final ArrayList<LValue> truncateArguments) throws MSArgumentMismatchException {
        if (truncateArguments.size() != 1) { throw new MSArgumentMismatchException("truncate", 1, truncateArguments.size()); }
        BigComplex truncateArgument = truncateArguments.get(0).getNumberValue();
        if (!truncateArgument.isReal()) { throw new MSArgumentMismatchException("truncate", "real", truncateArgument.toString()); }
        return new LValue(new BigDecimal(truncateArgument.re.toBigInteger().intValue()));
    }

    private static LValue interpretModulo(final ArrayList<LValue> moduloArguments) throws MSArgumentMismatchException {
        if (moduloArguments.size() != 2) { throw new MSArgumentMismatchException("modulo", 2, moduloArguments.size()); }
        BigComplex dividend = moduloArguments.get(0).getNumberValue();
        BigComplex divisor = moduloArguments.get(1).getNumberValue();
        if (!dividend.isReal()) { throw new MSArgumentMismatchException("modulo", 0, "integer", dividend.toString()); }
        if (!dividend.isReal()) { throw new MSArgumentMismatchException("modulo", 1, "integer", divisor.toString()); }
        return new LValue(dividend.re.remainder(divisor.re, MSNumberNode.PRECISION));
    }

    private static LValue interpretRemainder(final ArrayList<LValue> remainderArguments) throws MSArgumentMismatchException {
        if (remainderArguments.size() != 2) { throw new MSArgumentMismatchException("remainder", 2, remainderArguments.size()); }
        BigComplex dividend = remainderArguments.get(0).getNumberValue();
        BigComplex divisor = remainderArguments.get(1).getNumberValue();
        if (!dividend.isReal()) { throw new MSArgumentMismatchException("remainder", 0, "integer", dividend.toString()); }
        if (!dividend.isReal()) { throw new MSArgumentMismatchException("remainder", 1, "integer", divisor.toString()); }
        return new LValue(dividend.re.remainder(divisor.re, MSNumberNode.PRECISION).multiply(BigDecimal.valueOf(dividend.re.signum())));
    }

    private static LValue interpretSin(final ArrayList<LValue> sinArguments) throws MSArgumentMismatchException {
        if (sinArguments.size() != 1) { throw new MSArgumentMismatchException("sin", 1, sinArguments.size()); }
        BigComplex argument = sinArguments.get(0).getNumberValue();
        return new LValue(BigComplexMath.sin(argument, MSNumberNode.PRECISION));
    }

    private static LValue interpretCos(final ArrayList<LValue> cosArguments) throws MSArgumentMismatchException {
        if (cosArguments.size() != 1) { throw new MSArgumentMismatchException("cos", 1, cosArguments.size()); }
        BigComplex argument = cosArguments.get(0).getNumberValue();
        return new LValue(BigComplexMath.cos(argument, MSNumberNode.PRECISION));
    }

    private static LValue interpretTan(final ArrayList<LValue> tanArguments) throws MSArgumentMismatchException {
        if (tanArguments.size() != 1) { throw new MSArgumentMismatchException("tan", 1, tanArguments.size()); }
        BigComplex argument = tanArguments.get(0).getNumberValue();
        return new LValue(BigComplexMath.tan(argument, MSNumberNode.PRECISION));
    }

    private static LValue interpretAsin(final ArrayList<LValue> sinArguments) throws MSArgumentMismatchException {
        if (sinArguments.size() != 1) { throw new MSArgumentMismatchException("asin", 1, sinArguments.size()); }
        BigComplex argument = sinArguments.get(0).getNumberValue();
        return new LValue(BigComplexMath.asin(argument, MSNumberNode.PRECISION));
    }

    private static LValue interpretAcos(final ArrayList<LValue> cosArguments) throws MSArgumentMismatchException {
        if (cosArguments.size() != 1) { throw new MSArgumentMismatchException("acos", 1, cosArguments.size()); }
        BigComplex argument = cosArguments.get(0).getNumberValue();
        return new LValue(BigComplexMath.acos(argument, MSNumberNode.PRECISION));
    }

    private static LValue interpretAtan(final ArrayList<LValue> tanArguments) throws MSArgumentMismatchException {
        if (tanArguments.size() != 1) { throw new MSArgumentMismatchException("atan", 1, tanArguments.size()); }
        BigComplex argument = tanArguments.get(0).getNumberValue();
        return new LValue(BigComplexMath.atan(argument, MSNumberNode.PRECISION));
    }

    private static LValue interpretSinh(final ArrayList<LValue> sinhArguments) throws MSArgumentMismatchException {
        if (sinhArguments.size() != 1) { throw new MSArgumentMismatchException("sinh", 1, sinhArguments.size()); }
        BigComplex arg = sinhArguments.get(0).getNumberValue();
        BigDecimal lhs = BigDecimalMath.sinh(arg.re, MSNumberNode.PRECISION).multiply(BigDecimalMath.cos(arg.im, MSNumberNode.PRECISION));
        BigDecimal rhs = BigDecimalMath.cosh(arg.re, MSNumberNode.PRECISION).multiply(BigDecimalMath.sin(arg.im, MSNumberNode.PRECISION));
        return new LValue(BigComplex.valueOf(lhs, rhs));
    }

    private static LValue interpretCosh(final ArrayList<LValue> coshArguments) throws MSArgumentMismatchException {
        if (coshArguments.size() != 1) { throw new MSArgumentMismatchException("cosh", 1, coshArguments.size()); }
        BigComplex arg = coshArguments.get(0).getNumberValue();
        BigDecimal lhs = BigDecimalMath.cosh(arg.re, MSNumberNode.PRECISION).multiply(BigDecimalMath.cos(arg.im, MSNumberNode.PRECISION));
        BigDecimal rhs = BigDecimalMath.sinh(arg.re, MSNumberNode.PRECISION).multiply(BigDecimalMath.sin(arg.im, MSNumberNode.PRECISION));
        return new LValue(BigComplex.valueOf(lhs, rhs));
    }

    private static LValue interpretTanh(final ArrayList<LValue> tanhArguments) throws MSArgumentMismatchException {
        if (tanhArguments.size() != 1) { throw new MSArgumentMismatchException("tanh", 1, tanhArguments.size()); }
        BigComplex arg = tanhArguments.get(0).getNumberValue();
        BigDecimal topLhs = BigDecimalMath.sinh(arg.re, MSNumberNode.PRECISION).multiply(BigDecimalMath.cos(arg.re, MSNumberNode.PRECISION));
        BigDecimal topRhs = BigDecimalMath.cosh(arg.re, MSNumberNode.PRECISION).multiply(BigDecimalMath.sin(arg.im, MSNumberNode.PRECISION));
        BigDecimal botLhs = BigDecimalMath.cosh(arg.re, MSNumberNode.PRECISION).multiply(BigDecimalMath.cos(arg.im, MSNumberNode.PRECISION));
        BigDecimal botRhs = BigDecimalMath.sinh(arg.re, MSNumberNode.PRECISION).multiply(BigDecimalMath.sin(arg.im, MSNumberNode.PRECISION));
        return new LValue(BigComplex.valueOf(topLhs.add(topRhs).divide(botLhs.add(botRhs), MSNumberNode.PRECISION)));
    }

    private static LValue interpretAsinh(final ArrayList<LValue> sinhArguments) throws MSArgumentMismatchException {
        if (sinhArguments.size() != 1) { throw new MSArgumentMismatchException("asinh", 1, sinhArguments.size()); }
        BigComplex argument = sinhArguments.get(0).getNumberValue();
        if (!argument.isReal()) { throw new MSArgumentMismatchException("asinh", 1, "real", argument.toString()); }
        return new LValue(BigDecimalMath.asinh(argument.re, MSNumberNode.PRECISION));
    }

    private static LValue interpretAcosh(final ArrayList<LValue> coshArguments) throws MSArgumentMismatchException {
        if (coshArguments.size() != 1) { throw new MSArgumentMismatchException("acosh", 1, coshArguments.size()); }
        BigComplex argument = coshArguments.get(0).getNumberValue();
        if (!argument.isReal()) { throw new MSArgumentMismatchException("acosh", 1, "real", argument.toString()); }
        return new LValue(BigDecimalMath.acosh(argument.re, MSNumberNode.PRECISION));
    }

    private static LValue interpretAtanh(final ArrayList<LValue> tanhArguments) throws MSArgumentMismatchException {
        if (tanhArguments.size() != 1) { throw new MSArgumentMismatchException("atanh", 1, tanhArguments.size()); }
        BigComplex argument = tanhArguments.get(0).getNumberValue();
        if (!argument.isReal()) { throw new MSArgumentMismatchException("atanh", 1, "real", argument.toString()); }
        return new LValue(BigDecimalMath.atanh(argument.re, MSNumberNode.PRECISION));
    }

    private static LValue interpretLess(final ArrayList<LValue> lessArguments) throws MSArgumentMismatchException {
        if (lessArguments.size() != 2) { throw new MSArgumentMismatchException("<", 2, lessArguments.size()); }
        BigComplex lhs = lessArguments.get(0).getNumberValue();
        BigComplex rhs = lessArguments.get(1).getNumberValue();
        if (!lhs.isReal()) { throw new MSArgumentMismatchException("<", 1, "real", lhs.toString()); }
        if (!rhs.isReal()) { throw new MSArgumentMismatchException("<", 2, "real", rhs.toString()); }
        return new LValue(lhs.re.compareTo(rhs.re) < 0);
    }

    private static LValue interpretLessEqual(final ArrayList<LValue> lessEqualArguments) throws MSArgumentMismatchException {
        if (lessEqualArguments.size() != 2) { throw new MSArgumentMismatchException("<=", 2, lessEqualArguments.size()); }
        BigComplex lhs = lessEqualArguments.get(0).getNumberValue();
        BigComplex rhs = lessEqualArguments.get(1).getNumberValue();
        if (!lhs.isReal()) { throw new MSArgumentMismatchException("<=", 1, "real", lhs.toString()); }
        if (!rhs.isReal()) { throw new MSArgumentMismatchException("<=", 2, "real", rhs.toString()); }
        return new LValue(lhs.re.compareTo(rhs.re) <= 0);
    }

    private static LValue interpretGreater(final ArrayList<LValue> greaterArguments) throws MSArgumentMismatchException {
        if (greaterArguments.size() != 2) { throw new MSArgumentMismatchException(">", 2, greaterArguments.size()); }
        BigComplex lhs = greaterArguments.get(0).getNumberValue();
        BigComplex rhs = greaterArguments.get(1).getNumberValue();
        if (!lhs.isReal()) { throw new MSArgumentMismatchException(">", 1, "real", lhs.toString()); }
        if (!rhs.isReal()) { throw new MSArgumentMismatchException(">", 2, "real", rhs.toString()); }
        return new LValue(lhs.re.compareTo(rhs.re) > 0);
    }

    private static LValue interpretGreaterEqual(final ArrayList<LValue> greaterEqualArguments) throws MSArgumentMismatchException {
        if (greaterEqualArguments.size() != 2) { throw new MSArgumentMismatchException(">=", 2, greaterEqualArguments.size()); }
        BigComplex lhs = greaterEqualArguments.get(0).getNumberValue();
        BigComplex rhs = greaterEqualArguments.get(1).getNumberValue();
        if (!lhs.isReal()) { throw new MSArgumentMismatchException(">=", 1, "real", lhs.toString()); }
        if (!rhs.isReal()) { throw new MSArgumentMismatchException(">=", 2, "real", rhs.toString()); }
        return new LValue(lhs.re.compareTo(rhs.re) >= 0);
    }

    private static LValue interpretNumericEqual(final ArrayList<LValue> numericEqualArguments) throws MSArgumentMismatchException {
        if (numericEqualArguments.size() != 2) { throw new MSArgumentMismatchException("=", 2, numericEqualArguments.size()); }
        BigComplex lhs = numericEqualArguments.get(0).getNumberValue();
        BigComplex rhs = numericEqualArguments.get(1).getNumberValue();
        return new LValue(lhs.re.compareTo(rhs.re) == 0 && lhs.im.compareTo(rhs.im) == 0);
    }

    private static LValue interpretNot(final ArrayList<LValue> notArguments) throws MSArgumentMismatchException {
        if (notArguments.size() != 1) { throw new MSArgumentMismatchException("not", 2, notArguments.size()); }
        LValue booleanArgument = notArguments.get(0);
        return new LValue(!booleanArgument.getBooleanValue());
    }

    private static LValue interpretAnd(final ArrayList<LValue> andArguments) {
        if (andArguments.isEmpty()) { return new LValue(false); }
        for (LValue argument : andArguments) {
            if (!argument.getBooleanValue()) {return new LValue(false);}
        }
        return new LValue(true);
    }

    private static LValue interpretOr(final ArrayList<LValue> orArguments) {
        if (orArguments.isEmpty()) { return new LValue(false); }
        for (LValue argument : orArguments) {
            if (argument.getBooleanValue()) { return new LValue(true); }
        }
        return new LValue(false);
    }

    private static LValue interpretEqualPredicate(final ArrayList<LValue> equalArguments) throws MSArgumentMismatchException {
        if (equalArguments.size() != 2) { throw new MSArgumentMismatchException("equal?", 2, equalArguments.size()); }
        LValue lhs = equalArguments.get(0);
        LValue rhs = equalArguments.get(1);

        if (lhs == rhs) { return new LValue(true); }
        else if (lhs.getTree().getNodeType() == rhs.getTree().getNodeType()) {
            // Check the type.
            switch (lhs.getTree().getNodeType()) {
                case NUMBER: return new LValue(lhs.getNumberValue().equals(rhs.getNumberValue()));
                case STRING: return new LValue(lhs.getStringValue().equals(rhs.getStringValue()));
                case BOOLEAN: return new LValue(lhs.getBooleanValue() == rhs.getBooleanValue());
                case CHARACTER: return new LValue(lhs.getCharacterValue() == rhs.getCharacterValue());
                case SYMBOL: return new LValue(lhs.getSymbolValue().getStringRep().equals(rhs.getSymbolValue().getStringRep()));
                case VARIABLE:
                case LIST: return new LValue(lhs.getTree().getStringRep().equals(rhs.getTree().getStringRep()));
                default:
                    break;
            }
        }

        return new LValue(false);
    }

    private static LValue interpretEqPredicate(final ArrayList<LValue> equalArguments) throws MSArgumentMismatchException {
        if (equalArguments.size() != 2) { throw new MSArgumentMismatchException("eq?", 2, equalArguments.size()); }
        LValue lhs = equalArguments.get(0);
        LValue rhs = equalArguments.get(1);
        if (lhs == rhs) { return new LValue(true); }
        else if (lhs.getTree().getNodeType() == rhs.getTree().getNodeType()) {
            // Check the type.
            switch (lhs.getTree().getNodeType()) {
                case NUMBER: return new LValue(lhs.getNumberValue().equals(rhs.getNumberValue()));
                case STRING: return new LValue(lhs.getStringValue().equals(rhs.getStringValue()));
                case CHARACTER: return new LValue(lhs.getCharacterValue() == rhs.getCharacterValue());
                case BOOLEAN: return new LValue(lhs.getBooleanValue() == rhs.getBooleanValue());
                case SYMBOL: return new LValue(lhs.getSymbolValue().getStringRep().equals(rhs.getSymbolValue().getStringRep()));
                case VARIABLE: return new LValue(lhs.getTree().getStringRep().equals(rhs.getTree().getStringRep()));
                default:
                    break;
            }
        }

        return new LValue(false);
    }

    private static LValue interpretConsFunction(final ArrayList<LValue> consArguments) throws MSArgumentMismatchException {
        if (consArguments.size() != 2) { throw new MSArgumentMismatchException("cons", 2, consArguments.size()); }
        MSSyntaxTree lhs = LValue.getAst(consArguments.get(0));
        MSSyntaxTree rhs = LValue.getAst(consArguments.get(1));
        return new LValue(new MSListNode(lhs, rhs));
    }

    private static LValue interpretListFunction(final ArrayList<LValue> listArguments) {
        MSListNode rootList = null;
        MSListNode currList = null;
        for (int i = listArguments.size() - 1; i >= 0; i--) {
            MSSyntaxTree rhsExpression = LValue.getAst(listArguments.get(i));
            currList = new MSListNode(rhsExpression, currList);
        }

        rootList = Optional.ofNullable(currList).orElse(MSListNode.EMPTY_LIST);
        return new LValue(rootList);
    }

    private static LValue interpretCarFunction(final ArrayList<LValue> carArguments) throws MSArgumentMismatchException {
        if (carArguments.size() != 1) { throw new MSArgumentMismatchException("car", 1, carArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(carArguments.get(0));
        if (!argument.isList()) { throw new MSArgumentMismatchException("car", "list/cons pair", argument.getNodeType().toString()); }
        MSListNode listArgument = (MSListNode) argument;
        // Check to make sure we're not doing cdr on an empty list.
        if (listArgument.isEmptyList()) { throw new MSArgumentMismatchException("car", "non-empty list/cons pair", "()"); }
        return new LValue(listArgument.getCar());
    }

    private static LValue interpretCdrFunction(final ArrayList<LValue> cdrArguments) throws MSArgumentMismatchException {
        if (cdrArguments.size() != 1) { throw new MSArgumentMismatchException("cdr", 1, cdrArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(cdrArguments.get(0));
        if (!argument.isList()) { throw new MSArgumentMismatchException("cdr", "list/cons pair", argument.getNodeType().toString()); }
        MSListNode listArgument = (MSListNode) argument;
        // Check to make sure we're not doing cdr on an empty list.
        if (listArgument.isEmptyList()) { throw new MSArgumentMismatchException("cdr", "non-empty list/cons pair", "()"); }
        return new LValue(listArgument.getCdr());
    }

    private static LValue interpretVectorFunction(final ArrayList<LValue> vectorArguments) {
        ArrayList<MSSyntaxTree> vectorElements = new ArrayList<>();
        for (LValue lval : vectorArguments) { vectorElements.add(LValue.getAst(lval)); }
        return new LValue(new MSVectorNode(vectorElements));
    }

    private static LValue interpretVectorRefFunction(final ArrayList<LValue> vectorRefArguments) throws MSArgumentMismatchException {
        if (vectorRefArguments.size() != 2) { throw new MSArgumentMismatchException("vector-ref", 2, vectorRefArguments.size()); }
        MSSyntaxTree vector = LValue.getAst(vectorRefArguments.get(0));
        LValue index = vectorRefArguments.get(1);
        if (!vector.isVector()) { throw new MSArgumentMismatchException("vector-ref", 0, "vector", vector.getStringNodeType()); }
        return new LValue(vector.getChild(index.getNumberValue().re.intValue()));
    }

    private static LValue interpretVectorLengthFunction(final ArrayList<LValue> vectorLengthArguments) throws MSArgumentMismatchException {
        if (vectorLengthArguments.size() != 1) { throw new MSArgumentMismatchException("vector-length", 1, vectorLengthArguments.size()); }
        MSSyntaxTree vector = LValue.getAst(vectorLengthArguments.get(0));
        return new LValue(((MSVectorNode) vector).size());
    }

    private static LValue interpretNullPredicate(final ArrayList<LValue> nullArguments) throws MSArgumentMismatchException {
        if (nullArguments.size() != 1) { throw new MSArgumentMismatchException("null?", 1, nullArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(nullArguments.get(0));
        if (!argument.isList()) { return new LValue(false); }
        return new LValue(((MSListNode) argument).isEmptyList());
    }

    private static LValue interpretNumberPredicate(final ArrayList<LValue> numberArguments) throws MSArgumentMismatchException {
        if (numberArguments.size() != 1) { throw new MSArgumentMismatchException("number?", 1, numberArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(numberArguments.get(0));
        return new LValue(argument.isNumber());
    }

    private static LValue interpretCharPredicate(final ArrayList<LValue> characterArguments) throws MSArgumentMismatchException {
        if (characterArguments.size() != 1) { throw new MSArgumentMismatchException("character?", 1, characterArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(characterArguments.get(0));
        return new LValue(argument.isCharacter());
    }

    private static LValue interpretStringPredicate(final ArrayList<LValue> stringArguments) throws MSArgumentMismatchException {
        if (stringArguments.size() != 1) { throw new MSArgumentMismatchException("string?", 1, stringArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(stringArguments.get(0));
        return new LValue(argument.isString());
    }

    private static LValue interpretSymbolPredicate(final ArrayList<LValue> symbolArguments) throws MSArgumentMismatchException {
        if (symbolArguments.size() != 1) { throw new MSArgumentMismatchException("symbol?", 1, symbolArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(symbolArguments.get(0));
        return new LValue(argument.isSymbol() || argument.isVariable());
    }

    private static LValue interpretPairPredicate(final ArrayList<LValue> pairArguments) throws MSArgumentMismatchException {
        if (pairArguments.size() != 1) { throw new MSArgumentMismatchException("pair?", 1, pairArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(pairArguments.get(0));
        if (!argument.isList()) { return new LValue(false); }
        MSListNode listArgument = (MSListNode) argument;
        return new LValue(!listArgument.isEmptyList());
    }

    private static LValue interpretListPredicate(final ArrayList<LValue> listArguments) throws MSArgumentMismatchException {
        if (listArguments.size() != 1) { throw new MSArgumentMismatchException("list?", 1, listArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(listArguments.get(0));
        if (!argument.isList()) { return new LValue(false); }
        MSListNode listArgument = (MSListNode) argument;
        return new LValue(listArgument.isProper());
    }

    private static LValue interpretVectorPredicate(final ArrayList<LValue> vectorArguments) throws MSArgumentMismatchException {
        if (vectorArguments.size() != 1) { throw new MSArgumentMismatchException("vector?", 1, vectorArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(vectorArguments.get(0));
        return new LValue(argument.isVector());
    }

    private static LValue interpretStringAppendFunction(final ArrayList<LValue> stringAppendArguments) throws MSArgumentMismatchException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringAppendArguments.size(); i++) {
            LValue currentArgument = stringAppendArguments.get(i);
            if (!currentArgument.getTree().isString()) {
                throw new MSArgumentMismatchException("string-append", i + 1, "string", currentArgument.getTree().getNodeType().toString());
            }
            stringBuilder.append(currentArgument.getTree().getStringRep());
        }
        return new LValue(stringBuilder.toString());
    }

    private static LValue interpretStringLengthFunction(final ArrayList<LValue> stringLengthArguments) throws MSArgumentMismatchException {
        if (stringLengthArguments.size() != 1) { throw new MSArgumentMismatchException("string-length", 1, stringLengthArguments.size()); }
        MSStringNode stringArgument = (MSStringNode) LValue.getAst(stringLengthArguments.get(0));
        return new LValue(stringArgument.length());
    }

    private static LValue interpretStringLess(final ArrayList<LValue> stringLessArguments) throws MSArgumentMismatchException {
        if (stringLessArguments.size() != 2) { throw new MSArgumentMismatchException("string<?", 2, stringLessArguments.size()); }
        String leftArgument = stringLessArguments.get(0).getStringValue();
        String rightArgument = stringLessArguments.get(1).getStringValue();
        return new LValue(leftArgument.compareTo(rightArgument) < 0);
    }

    private static LValue interpretStringLessEqual(final ArrayList<LValue> stringLessEqualArguments) throws MSArgumentMismatchException {
        if (stringLessEqualArguments.size() != 2) { throw new MSArgumentMismatchException("string<=?", 2, stringLessEqualArguments.size()); }
        String leftArgument = stringLessEqualArguments.get(0).getStringValue();
        String rightArgument = stringLessEqualArguments.get(1).getStringValue();
        return new LValue(leftArgument.compareTo(rightArgument) <= 0);
    }

    private static LValue interpretStringGreater(final ArrayList<LValue> stringGreaterArguments) throws MSArgumentMismatchException {
        if (stringGreaterArguments.size() != 2) { throw new MSArgumentMismatchException("string>?", 2, stringGreaterArguments.size()); }
        String leftArgument = stringGreaterArguments.get(0).getStringValue();
        String rightArgument = stringGreaterArguments.get(1).getStringValue();
        return new LValue(leftArgument.compareTo(rightArgument) > 0);
    }

    private static LValue interpretStringGreaterEqual(final ArrayList<LValue> stringGreaterEqualArguments) throws MSArgumentMismatchException {
        if (stringGreaterEqualArguments.size() != 2) { throw new MSArgumentMismatchException("string>=?", 2, stringGreaterEqualArguments.size()); }
        String leftArgument = stringGreaterEqualArguments.get(0).getStringValue();
        String rightArgument = stringGreaterEqualArguments.get(1).getStringValue();
        return new LValue(leftArgument.compareTo(rightArgument) >= 0);
    }

    private static LValue interpretCharLess(final ArrayList<LValue> charLessArguments) throws MSArgumentMismatchException {
        if (charLessArguments.size() != 2) { throw new MSArgumentMismatchException("char<?", 2, charLessArguments.size()); }
        char leftArgument = charLessArguments.get(0).getCharacterValue();
        char rightArgument = charLessArguments.get(1).getCharacterValue();
        return new LValue(leftArgument < rightArgument);
    }

    private static LValue interpretCharLessEqual(final ArrayList<LValue> charLessEqualArguments) throws MSArgumentMismatchException {
        if (charLessEqualArguments.size() != 2) { throw new MSArgumentMismatchException("char<=?", 2, charLessEqualArguments.size()); }
        char leftArgument = charLessEqualArguments.get(0).getCharacterValue();
        char rightArgument = charLessEqualArguments.get(1).getCharacterValue();
        return new LValue(leftArgument <= rightArgument);
    }

    private static LValue interpretCharGreater(final ArrayList<LValue> charGreaterArguments) throws MSArgumentMismatchException {
        if (charGreaterArguments.size() != 2) { throw new MSArgumentMismatchException("char>?", 2, charGreaterArguments.size()); }
        char leftArgument = charGreaterArguments.get(0).getCharacterValue();
        char rightArgument = charGreaterArguments.get(1).getCharacterValue();
        return new LValue(leftArgument > rightArgument);
    }

    private static LValue interpretCharGreaterEqual(final ArrayList<LValue> charGreaterEqualArguments) throws MSArgumentMismatchException {
        if (charGreaterEqualArguments.size() != 2) { throw new MSArgumentMismatchException("char>=?", 2, charGreaterEqualArguments.size()); }
        char leftArgument = charGreaterEqualArguments.get(0).getCharacterValue();
        char rightArgument = charGreaterEqualArguments.get(1).getCharacterValue();
        return new LValue(leftArgument >= rightArgument);
    }

    private static LValue interpretNumberStringFunction(final ArrayList<LValue> numberStringArguments) throws MSArgumentMismatchException {
        if (numberStringArguments.size() != 1) { throw new MSArgumentMismatchException("number->string", 1, numberStringArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(numberStringArguments.get(0));
        return new LValue(argument.getStringRep());
    }

    private static LValue interpretStringNumberFunction(final ArrayList<LValue> stringNumberArguments) throws MSArgumentMismatchException {
        if (stringNumberArguments.size() != 1) { throw new MSArgumentMismatchException("string->number", 1, stringNumberArguments.size()); }
        LValue argument = stringNumberArguments.get(0);
        return new LValue(argument.getStringValue());
    }

    private static LValue interpretListStringFunction(final ArrayList<LValue> listStringArguments) throws MSArgumentMismatchException {
        if (listStringArguments.size() != 1) { throw new MSArgumentMismatchException("list->string", 1, listStringArguments.size()); }
        MSListNode curr = (MSListNode) LValue.getAst(listStringArguments.get(0));
        StringBuilder sb = new StringBuilder();
        for (int i = 0;; i++) {
            if (curr == null || (curr.isEmptyList())) { break; }
            else if (!curr.getCar().isCharacter()) { throw new MSArgumentMismatchException("list->string", i, "char", curr.getNodeType().toString()); }
            sb.append(curr.getCar().getStringRep());
            curr = (MSListNode) curr.getCdr();
        }
        return new LValue(sb.toString());
    }

    private static LValue interpretStringListFunction(final ArrayList<LValue> stringListArguments) throws MSArgumentMismatchException {
        if (stringListArguments.size() != 1) { throw new MSArgumentMismatchException("string->list", 1, stringListArguments.size()); }
        // Grab each character, convert it to a MSCharacterNode, then add it to the pair.
        MSSyntaxTree parentList;
        MSListNode prevList = null;
        String str = stringListArguments.get(0).getTree().getStringRep();
        for (int i = str.length() - 1; i >= 0; i--) {
            MSSyntaxTree rhsCharacter = new MSCharacterNode(str.charAt(i));
            prevList = new MSListNode(rhsCharacter, prevList);
        }
        // If they enter the empty list, then we need to add a "blank" list node.
        return new LValue(Optional.ofNullable(prevList).orElse(MSListNode.EMPTY_LIST));
    }

    private static LValue interpretRandomFunction(final ArrayList<LValue> randomArguments) {
        if (randomArguments.size() != 0) { throw new MSArgumentMismatchException("random", 0, randomArguments.size()); }
        return new LValue(BuiltinOperator.RANDOM.nextDouble());
    }

    private static LValue interpretRandomIntegerFunction(final ArrayList<LValue> randomIntegerArguments) {
        if (randomIntegerArguments.size() != 2) { throw new MSArgumentMismatchException("random-integer", 2, randomIntegerArguments.size()); }
        int min = randomIntegerArguments.get(0).getNumberValue().re.intValue();
        int max = randomIntegerArguments.get(1).getNumberValue().re.intValue();
        return new LValue(BuiltinOperator.RANDOM.nextInt((max - min) + 1) + min);
    }

    private static LValue interpretRandomDoubleFunction(final ArrayList<LValue> randomDoubleArguments) {
        if (randomDoubleArguments.size() != 2) { throw new MSArgumentMismatchException("random-double", 2, randomDoubleArguments.size()); }
        double min = randomDoubleArguments.get(0).getNumberValue().re.doubleValue();
        double max = randomDoubleArguments.get(1).getNumberValue().re.doubleValue();
        return new LValue(min + (max - min) * BuiltinOperator.RANDOM.nextDouble());
    }

    private static LValue interpretRandomSetSeedFunction(final ArrayList<LValue> randomSetSeedArguments) {
        if (randomSetSeedArguments.size() != 1) { throw new MSArgumentMismatchException("random-set-seed", 1, randomSetSeedArguments.size()); }
        long seed = randomSetSeedArguments.get(0).getNumberValue().re.longValue();
        BuiltinOperator.RANDOM.setSeed(seed);
        return new LValue("random-set-seed!");
    }
}