/******************************************************************************
 *  File: BuiltinOperator.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.main;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.parser.MSArgumentMismatchException;
import com.joshuacrotts.minischeme.parser.MSSemanticException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Optional;

public final class BuiltinOperator {

    /**
     *
     * @param expressionNode
     * @return
     */
    public static boolean isBuiltinOperator(final MSSyntaxTree expressionNode) {
        switch (expressionNode.getStringRep()) {
            case "display":
            case "+":
            case "-":
            case "*":
            case "/":
            case "**":
            case "log":
            case "floor":
            case "ceiling":
            case "round":
            case "truncate":
            case "modulo":
            case "remainder":
            case "sin":
            case "cos":
            case "tan":
            case "asin":
            case "acos":
            case "atan":
            case "sinh":
            case "cosh":
            case "tanh":
            case "asinh":
            case "acosh":
            case "atanh":
            case "<":
            case "<=":
            case ">":
            case ">=":
            case "=":
            case "not":
            case "and":
            case "or":
            case "equal?":
            case "eq?":
            case "cons":
            case "list":
            case "car":
            case "cdr":
            case "set-car!":
            case "set-cdr!":
            case "vector":
            case "vector-ref":
            case "vector-length":
            case "null?":
            case "number?":
            case "char?":
            case "string?":
            case "symbol?":
            case "pair?":
            case "list?":
            case "vector?":
            case "string-append":
            case "string-length":
            case "string<?":
            case "string<=?":
            case "string>?":
            case "string>=?":
            case "number->string":
            case "string->number":
            case "list->string":
            case "string->list":
                return true;
            default:
                return false;
        }
    }

    /**
     *
     * @param expressionNode
     * @param evalArguments
     * @param env
     * @return
     */
    public static LValue interpretBuiltinOperator(final MSSyntaxTree expressionNode,
                                                  final ArrayList<LValue> evalArguments,
                                                  final Environment env) throws MSSemanticException {
        if (!expressionNode.isVariable()) { return null; }
        switch (expressionNode.getStringRep()) {
            case "display": return BuiltinOperator.interpretDisplay(evalArguments);
            case "+": return BuiltinOperator.interpretAdd(evalArguments);
            case "-": return BuiltinOperator.interpretSubtract(evalArguments);
            case "*": return BuiltinOperator.interpretMultiply(evalArguments);
            case "/": return BuiltinOperator.interpretDivide(evalArguments);
            case "**": return BuiltinOperator.interpretPower(evalArguments);
            case "log": return BuiltinOperator.interpretLog(evalArguments);
            case "floor": return BuiltinOperator.interpretFloor(evalArguments);
            case "ceiling": return BuiltinOperator.interpretCeiling(evalArguments);
            case "round": return BuiltinOperator.interpretRound(evalArguments);
            case "truncate": return BuiltinOperator.interpretTruncate(evalArguments);
            case "modulo": return BuiltinOperator.interpretModulo(evalArguments);
            case "remainder": return BuiltinOperator.interpretRemainder(evalArguments);
            case "sin": return BuiltinOperator.interpretSin(evalArguments);
            case "cos": return BuiltinOperator.interpretCos(evalArguments);
            case "tan": return BuiltinOperator.interpretTan(evalArguments);
            case "asin": return BuiltinOperator.interpretAsin(evalArguments);
            case "acos": return BuiltinOperator.interpretAcos(evalArguments);
            case "atan": return BuiltinOperator.interpretAtan(evalArguments);
            case "sinh": return BuiltinOperator.interpretSinh(evalArguments);
            case "cosh": return BuiltinOperator.interpretCosh(evalArguments);
            case "tanh": return BuiltinOperator.interpretTanh(evalArguments);
            case "asinh": return BuiltinOperator.interpretAsinh(evalArguments);
            case "acosh": return BuiltinOperator.interpretAcosh(evalArguments);
            case "atanh": return BuiltinOperator.interpretAtanh(evalArguments);
            case "<": return BuiltinOperator.interpretLess(evalArguments);
            case "<=": return BuiltinOperator.interpretLessEqual(evalArguments);
            case ">": return BuiltinOperator.interpretGreater(evalArguments);
            case ">=": return BuiltinOperator.interpretGreaterEqual(evalArguments);
            case "=": return BuiltinOperator.interpretNumericEqual(evalArguments);
            case "not": return BuiltinOperator.interpretNot(evalArguments);
            case "and": return BuiltinOperator.interpretAnd(evalArguments);
            case "or": return BuiltinOperator.interpretOr(evalArguments);
            case "equal?": return BuiltinOperator.interpretEqualFunction(evalArguments);
            case "eq?": return BuiltinOperator.interpretEqFunction(evalArguments);
            case "cons": return BuiltinOperator.interpretConsFunction(evalArguments);
            case "list": return BuiltinOperator.interpretListFunction(evalArguments);
            case "car": return BuiltinOperator.interpretCarFunction(evalArguments);
            case "cdr": return BuiltinOperator.interpretCdrFunction(evalArguments);
            case "vector": return BuiltinOperator.interpretVectorFunction(evalArguments);
            case "vector-ref": return BuiltinOperator.interpretVectorRefFunction(evalArguments);
            case "vector-length": return BuiltinOperator.interpretVectorLengthFunction(evalArguments);
            case "null?": return BuiltinOperator.interpretNullPredicate(evalArguments);
            case "number?": return BuiltinOperator.interpretNumberPredicate(evalArguments);
            case "char?": return BuiltinOperator.interpretCharPredicate(evalArguments);
            case "string?": return BuiltinOperator.interpretStringPredicate(evalArguments);
            case "symbol?": return BuiltinOperator.interpretSymbolPredicate(evalArguments);
            case "pair?": return BuiltinOperator.interpretPairPredicate(evalArguments);
            case "list?": return BuiltinOperator.interpretListPredicate(evalArguments);
            case "vector?": return BuiltinOperator.interpretVectorPredicate(evalArguments);
            case "string-append": return BuiltinOperator.interpretStringAppendFunction(evalArguments);
            case "string-length": return BuiltinOperator.interpretStringLengthFunction(evalArguments);
            case "string<?": return BuiltinOperator.interpretStringLess(evalArguments);
            case "string<=?": return BuiltinOperator.interpretStringLessEqual(evalArguments);
            case "string>?": return BuiltinOperator.interpretStringGreater(evalArguments);
            case "string>=?": return BuiltinOperator.interpretStringGreaterEqual(evalArguments);
            case "number->string": return BuiltinOperator.interpretNumberStringFunction(evalArguments);
            case "string->number": return BuiltinOperator.interpretStringNumberFunction(evalArguments);
            case "list->string": return BuiltinOperator.interpretListStringFunction(evalArguments);
            case "string->list": return BuiltinOperator.interpretStringListFunction(evalArguments);
            default:
                return null;
        }
    }

    /**
     *
     * @param displayArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretDisplay(final ArrayList<LValue> displayArguments) throws MSArgumentMismatchException {
        if (displayArguments.size() != 1) { throw new MSArgumentMismatchException("display", 1, displayArguments.size());}
        return displayArguments.get(0);
    }

    /**
     *
     * @param addArguments
     * @return
     */
    private static LValue interpretAdd(final ArrayList<LValue> addArguments) {
        BigDecimal result = addArguments.get(0).getNumberValue();
        for (int i = 1; i < addArguments.size(); i++) { result = result.add(addArguments.get(i).getNumberValue()); }
        return new LValue(result);
    }

    /**
     *
     * @param subtractArguments
     * @return
     */
    private static LValue interpretSubtract(final ArrayList<LValue> subtractArguments) {
        BigDecimal result = subtractArguments.get(0).getNumberValue();
        for (int i = 1; i < subtractArguments.size(); i++) { result = result.subtract(subtractArguments.get(i).getNumberValue()); }
        return new LValue(result);
    }

    /**
     *
     * @param multiplyArguments
     * @return
     */
    private static LValue interpretMultiply(final ArrayList<LValue> multiplyArguments) {
        BigDecimal result = multiplyArguments.get(0).getNumberValue();
        for (int i = 1; i < multiplyArguments.size(); i++) { result = result.multiply(multiplyArguments.get(i).getNumberValue()); }
        return new LValue(result);
    }

    /**
     *
     * @param divideArguments
     * @return
     */
    private static LValue interpretDivide(final ArrayList<LValue> divideArguments) throws MSSemanticException {
        if (divideArguments.size() != 2) { throw new MSArgumentMismatchException("/", 2, divideArguments.size());}
        BigDecimal dividend = divideArguments.get(0).getNumberValue();
        BigDecimal divisor = divideArguments.get(1).getNumberValue();

        if (divisor.equals(BigDecimal.ZERO)) { throw new MSSemanticException("division by zero"); }
        return new LValue(dividend.divide(divisor, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param powerArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretPower(final ArrayList<LValue> powerArguments) throws MSArgumentMismatchException {
        if (powerArguments.size() != 2) { throw new MSArgumentMismatchException("**", 2, powerArguments.size()); }
        BigDecimal base = powerArguments.get(0).getNumberValue();
        BigDecimal power = powerArguments.get(1).getNumberValue();
        return new LValue(BigDecimalMath.pow(base, power, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param logArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretLog(final ArrayList<LValue> logArguments) throws MSArgumentMismatchException {
        if (logArguments.size() != 1) { throw new MSArgumentMismatchException("log", 1, logArguments.size()); }
        BigDecimal antilogarithm = logArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.log(antilogarithm, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param floorArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretFloor(final ArrayList<LValue> floorArguments) throws MSArgumentMismatchException {
        if (floorArguments.size() != 1) { throw new MSArgumentMismatchException("floor", 1, floorArguments.size()); }
        BigDecimal floorArgument = floorArguments.get(0).getNumberValue();
        return new LValue(floorArgument.setScale(0, RoundingMode.FLOOR));
    }

    /**
     *
     * @param ceilingArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretCeiling(final ArrayList<LValue> ceilingArguments) throws MSArgumentMismatchException {
        if (ceilingArguments.size() != 1) { throw new MSArgumentMismatchException("ceiling", 1, ceilingArguments.size()); }
        BigDecimal ceilingArgument = ceilingArguments.get(0).getNumberValue();
        return new LValue(ceilingArgument.setScale(0, RoundingMode.CEILING));
    }

    /**
     *
     * @param roundArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretRound(final ArrayList<LValue> roundArguments) throws MSArgumentMismatchException {
        if (roundArguments.size() != 1) { throw new MSArgumentMismatchException("round", 1, roundArguments.size()); }
        BigDecimal roundArgument = roundArguments.get(0).getNumberValue();
        return new LValue(roundArgument.setScale(0, RoundingMode.HALF_UP));
    }

    /**
     *
     * @param truncateArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretTruncate(final ArrayList<LValue> truncateArguments) throws MSArgumentMismatchException {
        if (truncateArguments.size() != 1) { throw new MSArgumentMismatchException("truncate", 1, truncateArguments.size()); }
        BigDecimal truncateArgument = truncateArguments.get(0).getNumberValue();
        return new LValue(new BigDecimal(truncateArgument.toBigInteger().intValue()));
    }

    /**
     *
     * @param moduloArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretModulo(final ArrayList<LValue> moduloArguments) throws MSArgumentMismatchException {
        if (moduloArguments.size() != 2) { throw new MSArgumentMismatchException("modulo", 2, moduloArguments.size()); }
        BigDecimal dividend = moduloArguments.get(0).getNumberValue();
        BigDecimal divisor = moduloArguments.get(1).getNumberValue();
        return new LValue(dividend.remainder(divisor, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param remainderArguments
     * @return
     */
    private static LValue interpretRemainder(final ArrayList<LValue> remainderArguments) throws MSArgumentMismatchException {
        if (remainderArguments.size() != 2) { throw new MSArgumentMismatchException("remainder", 2, remainderArguments.size()); }
        BigDecimal dividend = remainderArguments.get(0).getNumberValue();
        BigDecimal divisor = remainderArguments.get(1).getNumberValue();
        BigDecimal remainder = dividend.remainder(divisor, MSNumberNode.PRECISION).multiply(BigDecimal.valueOf(dividend.signum()));
        return new LValue(remainder);
    }

    /**
     *
     * @param sinArguments
     * @return
     */
    private static LValue interpretSin(final ArrayList<LValue> sinArguments) throws MSArgumentMismatchException {
        if (sinArguments.size() != 1) { throw new MSArgumentMismatchException("sin", 1, sinArguments.size()); }
        BigDecimal argument = sinArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.sin(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param cosArguments
     * @return
     */
    private static LValue interpretCos(final ArrayList<LValue> cosArguments) throws MSArgumentMismatchException {
        if (cosArguments.size() != 1) { throw new MSArgumentMismatchException("cos", 1, cosArguments.size()); }
        BigDecimal argument = cosArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.cos(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param tanArguments
     * @return
     */
    private static LValue interpretTan(final ArrayList<LValue> tanArguments) throws MSArgumentMismatchException {
        if (tanArguments.size() != 1) { throw new MSArgumentMismatchException("tan", 1, tanArguments.size()); }
        BigDecimal argument = tanArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.tan(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param sinArguments
     * @return
     */
    private static LValue interpretAsin(final ArrayList<LValue> sinArguments) throws MSArgumentMismatchException {
        if (sinArguments.size() != 1) { throw new MSArgumentMismatchException("asin", 1, sinArguments.size()); }
        BigDecimal argument = sinArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.asin(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param cosArguments
     * @return
     */
    private static LValue interpretAcos(final ArrayList<LValue> cosArguments) throws MSArgumentMismatchException {
        if (cosArguments.size() != 1) { throw new MSArgumentMismatchException("acos", 1, cosArguments.size()); }
        BigDecimal argument = cosArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.acos(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param tanArguments
     * @return
     */
    private static LValue interpretAtan(final ArrayList<LValue> tanArguments) throws MSArgumentMismatchException {
        if (tanArguments.size() != 1) { throw new MSArgumentMismatchException("atan", 1, tanArguments.size()); }
        BigDecimal argument = tanArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.atan(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param sinhArguments
     * @return
     */
    private static LValue interpretSinh(final ArrayList<LValue> sinhArguments) throws MSArgumentMismatchException {
        if (sinhArguments.size() != 1) { throw new MSArgumentMismatchException("sinh", 1, sinhArguments.size()); }
        BigDecimal argument = sinhArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.sinh(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param coshArguments
     * @return
     */
    private static LValue interpretCosh(final ArrayList<LValue> coshArguments) throws MSArgumentMismatchException {
        if (coshArguments.size() != 1) { throw new MSArgumentMismatchException("cosh", 1, coshArguments.size()); }
        BigDecimal argument = coshArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.cosh(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param tanhArguments
     * @return
     */
    private static LValue interpretTanh(final ArrayList<LValue> tanhArguments) throws MSArgumentMismatchException {
        if (tanhArguments.size() != 1) { throw new MSArgumentMismatchException("tanh", 1, tanhArguments.size()); }
        BigDecimal argument = tanhArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.tanh(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param sinhArguments
     * @return
     */
    private static LValue interpretAsinh(final ArrayList<LValue> sinhArguments) throws MSArgumentMismatchException {
        if (sinhArguments.size() != 1) { throw new MSArgumentMismatchException("asinh", 1, sinhArguments.size()); }
        BigDecimal argument = sinhArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.asinh(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param coshArguments
     * @return
     */
    private static LValue interpretAcosh(final ArrayList<LValue> coshArguments) throws MSArgumentMismatchException {
        if (coshArguments.size() != 1) { throw new MSArgumentMismatchException("acosh", 1, coshArguments.size()); }
        BigDecimal argument = coshArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.acosh(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param tanhArguments
     * @return
     */
    private static LValue interpretAtanh(final ArrayList<LValue> tanhArguments) throws MSArgumentMismatchException {
        if (tanhArguments.size() != 1) { throw new MSArgumentMismatchException("atanh", 1, tanhArguments.size()); }
        BigDecimal argument = tanhArguments.get(0).getNumberValue();
        return new LValue(BigDecimalMath.atanh(argument, MSNumberNode.PRECISION));
    }

    /**
     *
     * @param lessArguments
     * @return
     */
    private static LValue interpretLess(final ArrayList<LValue> lessArguments) throws MSArgumentMismatchException {
        if (lessArguments.size() != 2) { throw new MSArgumentMismatchException("<", 2, lessArguments.size()); }
        BigDecimal lhs = lessArguments.get(0).getNumberValue();
        BigDecimal rhs = lessArguments.get(1).getNumberValue();
        return new LValue(lhs.compareTo(rhs) < 0);
    }

    /**
     *
     * @param lessEqualArguments
     * @return
     */
    private static LValue interpretLessEqual(final ArrayList<LValue> lessEqualArguments) throws MSArgumentMismatchException {
        if (lessEqualArguments.size() != 2) { throw new MSArgumentMismatchException("<=", 2, lessEqualArguments.size()); }
        BigDecimal lhs = lessEqualArguments.get(0).getNumberValue();
        BigDecimal rhs = lessEqualArguments.get(1).getNumberValue();
        return new LValue(lhs.compareTo(rhs) <= 0);
    }

    /**
     *
     * @param greaterArguments
     * @return
     */
    private static LValue interpretGreater(final ArrayList<LValue> greaterArguments) throws MSArgumentMismatchException {
        if (greaterArguments.size() != 2) { throw new MSArgumentMismatchException(">", 2, greaterArguments.size()); }
        BigDecimal lhs = greaterArguments.get(0).getNumberValue();
        BigDecimal rhs = greaterArguments.get(1).getNumberValue();
        return new LValue(lhs.compareTo(rhs) > 0);
    }

    /**
     *
     * @param greaterEqualArguments
     * @return
     */
    private static LValue interpretGreaterEqual(final ArrayList<LValue> greaterEqualArguments) throws MSArgumentMismatchException {
        if (greaterEqualArguments.size() != 2) { throw new MSArgumentMismatchException(">=", 2, greaterEqualArguments.size()); }
        BigDecimal lhs = greaterEqualArguments.get(0).getNumberValue();
        BigDecimal rhs = greaterEqualArguments.get(1).getNumberValue();
        return new LValue(lhs.compareTo(rhs) >= 0);
    }

    /**
     *
     * @param numericEqualArguments
     * @return
     */
    private static LValue interpretNumericEqual(final ArrayList<LValue> numericEqualArguments) throws MSArgumentMismatchException {
        if (numericEqualArguments.size() != 2) { throw new MSArgumentMismatchException("=", 2, numericEqualArguments.size()); }
        BigDecimal lhs = numericEqualArguments.get(0).getNumberValue();
        BigDecimal rhs = numericEqualArguments.get(1).getNumberValue();
        return new LValue(lhs.compareTo(rhs) == 0);
    }

    /**
     *
     * @param notArguments
     * @return
     */
    private static LValue interpretNot(final ArrayList<LValue> notArguments) throws MSArgumentMismatchException {
        if (notArguments.size() != 1) { throw new MSArgumentMismatchException("not", 2, notArguments.size()); }
        LValue booleanArgument = notArguments.get(0);
        return new LValue(!booleanArgument.getBooleanValue());
    }

    /**
     *
     * @param andArguments
     * @return
     */
    private static LValue interpretAnd(final ArrayList<LValue> andArguments) {
        if (andArguments.isEmpty()) { return new LValue(false); }
        for (LValue argument : andArguments) {
            if (!argument.getBooleanValue()) {
                return new LValue(false);
            }
        }
        return new LValue(true);
    }

    /**
     *
     * @param orArguments
     * @return
     */
    private static LValue interpretOr(final ArrayList<LValue> orArguments) {
        if (orArguments.isEmpty()) { return new LValue(false); }
        for (LValue argument : orArguments) {
            if (argument.getBooleanValue()) {
                return new LValue(true);
            }
        }
        return new LValue(false);
    }

    /**
     *
     * @param equalArguments
     * @return
     */
    private static LValue interpretEqualFunction(final ArrayList<LValue> equalArguments) throws MSArgumentMismatchException {
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

    /**
     *
     * @param equalArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretEqFunction(final ArrayList<LValue> equalArguments) throws MSArgumentMismatchException {
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

    /**
     *
     * @param consArguments
     * @return
     */
    private static LValue interpretConsFunction(final ArrayList<LValue> consArguments) {
        MSSyntaxTree lhs = LValue.getAst(consArguments.get(0));
        MSSyntaxTree rhs = LValue.getAst(consArguments.get(1));
        return new LValue(new MSListNode(lhs, rhs));
    }

    /**
     *
     * @param listArguments
     * @return
     */
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

    /**
     *
     * @param carArguments
     * @return
     */
    private static LValue interpretCarFunction(final ArrayList<LValue> carArguments) throws MSArgumentMismatchException {
        if (carArguments.size() != 1) { throw new MSArgumentMismatchException("car", 1, carArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(carArguments.get(0));
        if (!argument.isList()) { throw new MSArgumentMismatchException("car", "list/cons pair", argument.getNodeType().toString()); }
        MSListNode listArgument = (MSListNode) argument;
        // Check to make sure we're not doing cdr on an empty list.
        if (listArgument.isEmptyList()) { throw new MSArgumentMismatchException("car", "non-empty list/cons pair", "()"); }
        return new LValue(listArgument.getCar());
    }

    /**
     *
     * @param cdrArguments
     * @return
     */
    private static LValue interpretCdrFunction(final ArrayList<LValue> cdrArguments) throws MSArgumentMismatchException {
        if (cdrArguments.size() != 1) { throw new MSArgumentMismatchException("cdr", 1, cdrArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(cdrArguments.get(0));
        if (!argument.isList()) { throw new MSArgumentMismatchException("cdr", "list/cons pair", argument.getNodeType().toString()); }
        MSListNode listArgument = (MSListNode) argument;
        // Check to make sure we're not doing cdr on an empty list.
        if (listArgument.isEmptyList()) { throw new MSArgumentMismatchException("cdr", "non-empty list/cons pair", "()"); }
        return new LValue(listArgument.getCdr());
    }

    /**
     *
     * @param vectorArguments
     * @return
     */
    private static LValue interpretVectorFunction(final ArrayList<LValue> vectorArguments) {
        ArrayList<MSSyntaxTree> vectorElements = new ArrayList<>();
        for (LValue lval : vectorArguments) { vectorElements.add(LValue.getAst(lval)); }
        return new LValue(new MSVectorNode(vectorElements));
    }

    /**
     *
     * @param vectorRefArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretVectorRefFunction(final ArrayList<LValue> vectorRefArguments) throws MSArgumentMismatchException {
        if (vectorRefArguments.size() != 2) { throw new MSArgumentMismatchException("vector-ref", 2, vectorRefArguments.size()); }
        MSSyntaxTree vector = LValue.getAst(vectorRefArguments.get(0));
        LValue index = vectorRefArguments.get(1);
        return new LValue(vector.getChild(index.getNumberValue().intValue()));
    }

    /**
     *
     * @param vectorLengthArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretVectorLengthFunction(final ArrayList<LValue> vectorLengthArguments) throws MSArgumentMismatchException {
        if (vectorLengthArguments.size() != 1) { throw new MSArgumentMismatchException("vector-length", 1, vectorLengthArguments.size()); }
        MSSyntaxTree vector = LValue.getAst(vectorLengthArguments.get(0));
        return new LValue(((MSVectorNode) vector).size());
    }

    /**
     *
     * @param nullArguments
     * @return
     */
    private static LValue interpretNullPredicate(final ArrayList<LValue> nullArguments) throws MSArgumentMismatchException {
        if (nullArguments.size() != 1) { throw new MSArgumentMismatchException("null?", 1, nullArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(nullArguments.get(0));
        if (!argument.isList()) { return new LValue(false); }
        return new LValue(((MSListNode) argument).isEmptyList());
    }

    /**
     *
     * @param numberArguments
     * @return
     */
    private static LValue interpretNumberPredicate(final ArrayList<LValue> numberArguments) throws MSArgumentMismatchException {
        if (numberArguments.size() != 1) { throw new MSArgumentMismatchException("number?", 1, numberArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(numberArguments.get(0));
        return new LValue(argument.isNumber());
    }

    /**
     *
     * @param characterArguments
     * @return
     */
    private static LValue interpretCharPredicate(final ArrayList<LValue> characterArguments) throws MSArgumentMismatchException {
        if (characterArguments.size() != 1) { throw new MSArgumentMismatchException("character?", 1, characterArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(characterArguments.get(0));
        return new LValue(argument.isCharacter());
    }

    /**
     *
     * @param stringArguments
     * @return
     */
    private static LValue interpretStringPredicate(final ArrayList<LValue> stringArguments) throws MSArgumentMismatchException {
        if (stringArguments.size() != 1) { throw new MSArgumentMismatchException("string?", 1, stringArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(stringArguments.get(0));
        return new LValue(argument.isString());
    }

    /**
     *
     * @param symbolArguments
     * @return
     */
    private static LValue interpretSymbolPredicate(final ArrayList<LValue> symbolArguments) throws MSArgumentMismatchException {
        if (symbolArguments.size() != 1) { throw new MSArgumentMismatchException("symbol?", 1, symbolArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(symbolArguments.get(0));
        return new LValue(argument.isSymbol() || argument.isVariable());
    }

    /**
     *
     * @param pairArguments
     * @return
     */
    private static LValue interpretPairPredicate(final ArrayList<LValue> pairArguments) throws MSArgumentMismatchException {
        if (pairArguments.size() != 1) { throw new MSArgumentMismatchException("pair?", 1, pairArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(pairArguments.get(0));
        if (!argument.isList()) { return new LValue(false); }
        MSListNode listArgument = (MSListNode) argument;
        return new LValue(!listArgument.isEmptyList());
    }

    /**
     *
     * @param listArguments
     * @return
     */
    private static LValue interpretListPredicate(final ArrayList<LValue> listArguments) throws MSArgumentMismatchException {
        if (listArguments.size() != 1) { throw new MSArgumentMismatchException("list?", 1, listArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(listArguments.get(0));
        if (!argument.isList()) { return new LValue(false); }
        MSListNode listArgument = (MSListNode) argument;
        return new LValue(listArgument.isProper());
    }

    /**
     *
     * @param vectorArguments
     * @return
     */
    private static LValue interpretVectorPredicate(final ArrayList<LValue> vectorArguments) throws MSArgumentMismatchException {
        if (vectorArguments.size() != 1) { throw new MSArgumentMismatchException("vector?", 1, vectorArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(vectorArguments.get(0));
        return new LValue(argument.isVector());
    }

    /**
     *
     * @param stringAppendArguments
     * @return
     * @throws MSArgumentMismatchException
     */
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

    /**
     *
     * @param stringLengthArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringLengthFunction(final ArrayList<LValue> stringLengthArguments) throws MSArgumentMismatchException {
        if (stringLengthArguments.size() != 1) { throw new MSArgumentMismatchException("string-length", 1, stringLengthArguments.size()); }
        MSStringNode stringArgument = (MSStringNode) LValue.getAst(stringLengthArguments.get(0));
        return new LValue(stringArgument.length());
    }

    /**
     *
     * @param stringLessArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringLess(final ArrayList<LValue> stringLessArguments) throws MSArgumentMismatchException {
        if (stringLessArguments.size() != 2) { throw new MSArgumentMismatchException("string<?", 2, stringLessArguments.size()); }
        String leftArgument = stringLessArguments.get(0).getStringValue();
        String rightArgument = stringLessArguments.get(1).getStringValue();
        return new LValue(leftArgument.compareTo(rightArgument) < 0);
    }

    /**
     *
     * @param stringLessEqualArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringLessEqual(final ArrayList<LValue> stringLessEqualArguments) throws MSArgumentMismatchException {
        if (stringLessEqualArguments.size() != 2) { throw new MSArgumentMismatchException("string<=?", 2, stringLessEqualArguments.size()); }
        String leftArgument = stringLessEqualArguments.get(0).getStringValue();
        String rightArgument = stringLessEqualArguments.get(1).getStringValue();
        return new LValue(leftArgument.compareTo(rightArgument) <= 0);
    }

    /**
     *
     * @param stringGreaterArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringGreater(final ArrayList<LValue> stringGreaterArguments) throws MSArgumentMismatchException {
        if (stringGreaterArguments.size() != 2) { throw new MSArgumentMismatchException("string>?", 2, stringGreaterArguments.size()); }
        String leftArgument = stringGreaterArguments.get(0).getStringValue();
        String rightArgument = stringGreaterArguments.get(1).getStringValue();
        return new LValue(leftArgument.compareTo(rightArgument) > 0);
    }

    /**
     *
     * @param stringGreaterEqualArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringGreaterEqual(final ArrayList<LValue> stringGreaterEqualArguments) throws MSArgumentMismatchException {
        if (stringGreaterEqualArguments.size() != 2) { throw new MSArgumentMismatchException("string>=?", 2, stringGreaterEqualArguments.size()); }
        String leftArgument = stringGreaterEqualArguments.get(0).getStringValue();
        String rightArgument = stringGreaterEqualArguments.get(1).getStringValue();
        return new LValue(leftArgument.compareTo(rightArgument) >= 0);
    }

    /**
     *
     * @param numberStringArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretNumberStringFunction(final ArrayList<LValue> numberStringArguments) throws MSArgumentMismatchException {
        if (numberStringArguments.size() != 1) { throw new MSArgumentMismatchException("number->string", 1, numberStringArguments.size()); }
        LValue argument = numberStringArguments.get(0);
        return new LValue(argument.getNumberValue().toPlainString());
    }

    /**
     *
     * @param stringNumberArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringNumberFunction(final ArrayList<LValue> stringNumberArguments) throws MSArgumentMismatchException {
        if (stringNumberArguments.size() != 1) { throw new MSArgumentMismatchException("string->number", 1, stringNumberArguments.size()); }
        LValue argument = stringNumberArguments.get(0);
        return new LValue(argument.getStringValue());
    }

    /**
     *
     * @param listStringArguments
     * @return
     * @throws MSArgumentMismatchException
     */
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

    /**
     *
     * @param stringListArguments
     * @return
     * @throws MSArgumentMismatchException
     */
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
}