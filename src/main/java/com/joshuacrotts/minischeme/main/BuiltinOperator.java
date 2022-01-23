package com.joshuacrotts.minischeme.main;

import ch.obermuhlner.math.big.BigDecimalMath;
import ch.obermuhlner.math.big.BigFloat;
import com.joshuacrotts.minischeme.ast.*;
import com.joshuacrotts.minischeme.parser.MSArgumentMismatchException;
import com.joshuacrotts.minischeme.parser.MSSemanticException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Joshua Crotts
 * @version 01/19/2022
 */
public class BuiltinOperator {

    /**
     *
     * @param expressionNode
     * @return
     */
    public static boolean isBuiltinOperator(MSSyntaxTree expressionNode) {
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
            case "sinh":
            case "cosh":
            case "tanh":
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
            case "null?":
            case "pair?":
            case "list?":
            case "string-append":
            case "string-length":
            case "set!":
                return true;
            default:
                return false;
        }
    }

    /**
     *
     * @param expressionNode
     * @param evalArguments
     * @return
     */
    public static LValue interpretBuiltinOperator(MSSyntaxTree expressionNode, ArrayList<LValue> evalArguments, Environment env) throws MSSemanticException {
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
            case "sinh": return BuiltinOperator.interpretSinh(evalArguments);
            case "cosh": return BuiltinOperator.interpretCosh(evalArguments);
            case "tanh": return BuiltinOperator.interpretTanh(evalArguments);
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
            case "null?": return BuiltinOperator.interpretNullPredicate(evalArguments);
            case "pair?": return BuiltinOperator.interpretPairPredicate(evalArguments);
            case "list?": return BuiltinOperator.interpretListPredicate(evalArguments);
            case "string-append": return BuiltinOperator.interpretStringAppendFunction(evalArguments);
            case "string-length": return BuiltinOperator.interpretStringLengthFunction(evalArguments);
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
    private static LValue interpretDisplay(ArrayList<LValue> displayArguments) throws MSArgumentMismatchException {
        if (displayArguments.size() != 1) { throw new MSArgumentMismatchException("display", 1, displayArguments.size());}
        return displayArguments.get(0);
    }

    /**
     *
     * @param addArguments
     * @return
     */
    private static LValue interpretAdd(ArrayList<LValue> addArguments) {
        BigDecimal result = addArguments.get(0).getNumberValue();
        for (int i = 1; i < addArguments.size(); i++) { result = result.add(addArguments.get(i).getNumberValue()); }
        return new LValue(new MSNumberNode(result));
    }

    /**
     *
     * @param subtractArguments
     * @return
     */
    private static LValue interpretSubtract(ArrayList<LValue> subtractArguments) {
        BigDecimal result = subtractArguments.get(0).getNumberValue();
        for (int i = 1; i < subtractArguments.size(); i++) { result = result.subtract(subtractArguments.get(i).getNumberValue()); }
        return new LValue(new MSNumberNode(result));
    }

    /**
     *
     * @param multiplyArguments
     * @return
     */
    private static LValue interpretMultiply(ArrayList<LValue> multiplyArguments) {
        BigDecimal result = multiplyArguments.get(0).getNumberValue();
        for (int i = 1; i < multiplyArguments.size(); i++) { result = result.multiply(multiplyArguments.get(i).getNumberValue()); }
        return new LValue(new MSNumberNode(result));
    }

    /**
     *
     * @param divideArguments
     * @return
     */
    private static LValue interpretDivide(ArrayList<LValue> divideArguments) throws MSSemanticException {
        if (divideArguments.size() != 2) { throw new MSArgumentMismatchException("/", 2, divideArguments.size());}
        BigDecimal dividend = divideArguments.get(0).getNumberValue();
        BigDecimal divisor = divideArguments.get(1).getNumberValue();

        if (divisor.equals(BigDecimal.ZERO)) { throw new MSSemanticException("division by zero"); }
        return new LValue(new MSNumberNode(dividend.divide(divisor, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param powerArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretPower(ArrayList<LValue> powerArguments) throws MSArgumentMismatchException {
        if (powerArguments.size() != 2) { throw new MSArgumentMismatchException("**", 2, powerArguments.size()); }
        BigDecimal base = powerArguments.get(0).getNumberValue();
        BigDecimal power = powerArguments.get(1).getNumberValue();
        return new LValue(new MSNumberNode(BigDecimalMath.pow(base, power, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param logArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretLog(ArrayList<LValue> logArguments) throws MSArgumentMismatchException {
        if (logArguments.size() != 1) { throw new MSArgumentMismatchException("log", 1, logArguments.size()); }
        BigDecimal antilogarithm = logArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(BigDecimalMath.log(antilogarithm, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param floorArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretFloor(ArrayList<LValue> floorArguments) throws MSArgumentMismatchException {
        if (floorArguments.size() != 1) { throw new MSArgumentMismatchException("floor", 1, floorArguments.size()); }
        BigDecimal floorArgument = floorArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(floorArgument.setScale(0, RoundingMode.FLOOR)));
    }

    /**
     *
     * @param ceilingArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretCeiling(ArrayList<LValue> ceilingArguments) throws MSArgumentMismatchException {
        if (ceilingArguments.size() != 1) { throw new MSArgumentMismatchException("ceiling", 1, ceilingArguments.size()); }
        BigDecimal ceilingArgument = ceilingArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(ceilingArgument.setScale(0, RoundingMode.CEILING)));
    }

    /**
     *
     * @param roundArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretRound(ArrayList<LValue> roundArguments) throws MSArgumentMismatchException {
        if (roundArguments.size() != 1) { throw new MSArgumentMismatchException("round", 1, roundArguments.size()); }
        BigDecimal roundArgument = roundArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(roundArgument.setScale(0, RoundingMode.HALF_UP)));
    }

    /**
     *
     * @param truncateArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretTruncate(ArrayList<LValue> truncateArguments) throws MSArgumentMismatchException {
        if (truncateArguments.size() != 1) { throw new MSArgumentMismatchException("truncate", 1, truncateArguments.size()); }
        BigDecimal truncateArgument = truncateArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(truncateArgument.toBigInteger().toString()));
    }

    /**
     *
     * @param moduloArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretModulo(ArrayList<LValue> moduloArguments) throws MSArgumentMismatchException {
        if (moduloArguments.size() != 2) { throw new MSArgumentMismatchException("modulo", 2, moduloArguments.size()); }
        BigDecimal dividend = moduloArguments.get(0).getNumberValue();
        BigDecimal divisor = moduloArguments.get(1).getNumberValue();
        return new LValue(new MSNumberNode(dividend.remainder(divisor, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param remainderArguments
     * @return
     */
    private static LValue interpretRemainder(ArrayList<LValue> remainderArguments) throws MSArgumentMismatchException {
        if (remainderArguments.size() != 2) { throw new MSArgumentMismatchException("remainder", 2, remainderArguments.size()); }
        BigDecimal dividend = remainderArguments.get(0).getNumberValue();
        BigDecimal divisor = remainderArguments.get(1).getNumberValue();
        BigDecimal remainder = dividend.remainder(divisor, MSNumberNode.PRECISION).multiply(BigDecimal.valueOf(dividend.signum()));
        return new LValue(new MSNumberNode(remainder));
    }

    /**
     *
     * @param sinArguments
     * @return
     */
    private static LValue interpretSin(ArrayList<LValue> sinArguments) throws MSArgumentMismatchException {
        if (sinArguments.size() != 1) { throw new MSArgumentMismatchException("sin", 1, sinArguments.size()); }
        BigDecimal argument = sinArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(BigDecimalMath.sin(argument, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param cosArguments
     * @return
     */
    private static LValue interpretCos(ArrayList<LValue> cosArguments) throws MSArgumentMismatchException {
        if (cosArguments.size() != 1) { throw new MSArgumentMismatchException("cos", 1, cosArguments.size()); }
        BigDecimal argument = cosArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(BigDecimalMath.cos(argument, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param tanArguments
     * @return
     */
    private static LValue interpretTan(ArrayList<LValue> tanArguments) throws MSArgumentMismatchException {
        if (tanArguments.size() != 1) { throw new MSArgumentMismatchException("tan", 1, tanArguments.size()); }
        BigDecimal argument = tanArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(BigDecimalMath.tan(argument, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param sinhArguments
     * @return
     */
    private static LValue interpretSinh(ArrayList<LValue> sinhArguments) throws MSArgumentMismatchException {
        if (sinhArguments.size() != 1) { throw new MSArgumentMismatchException("sinh", 1, sinhArguments.size()); }
        BigDecimal argument = sinhArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(BigDecimalMath.sinh(argument, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param coshArguments
     * @return
     */
    private static LValue interpretCosh(ArrayList<LValue> coshArguments) throws MSArgumentMismatchException {
        if (coshArguments.size() != 1) { throw new MSArgumentMismatchException("cosh", 1, coshArguments.size()); }
        BigDecimal argument = coshArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(BigDecimalMath.cosh(argument, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param tanhArguments
     * @return
     */
    private static LValue interpretTanh(ArrayList<LValue> tanhArguments) throws MSArgumentMismatchException {
        if (tanhArguments.size() != 1) { throw new MSArgumentMismatchException("tanh", 1, tanhArguments.size()); }
        BigDecimal argument = tanhArguments.get(0).getNumberValue();
        return new LValue(new MSNumberNode(BigDecimalMath.tanh(argument, MSNumberNode.PRECISION)));
    }

    /**
     *
     * @param lessArguments
     * @return
     */
    private static LValue interpretLess(ArrayList<LValue> lessArguments) throws MSArgumentMismatchException {
        if (lessArguments.size() != 2) { throw new MSArgumentMismatchException("<", 2, lessArguments.size()); }
        BigDecimal lhs = lessArguments.get(0).getNumberValue();
        BigDecimal rhs = lessArguments.get(1).getNumberValue();
        return new LValue(new MSBooleanNode(lhs.compareTo(rhs) < 0));
    }

    /**
     *
     * @param lessEqualArguments
     * @return
     */
    private static LValue interpretLessEqual(ArrayList<LValue> lessEqualArguments) throws MSArgumentMismatchException {
        if (lessEqualArguments.size() != 2) { throw new MSArgumentMismatchException("<=", 2, lessEqualArguments.size()); }
        BigDecimal lhs = lessEqualArguments.get(0).getNumberValue();
        BigDecimal rhs = lessEqualArguments.get(1).getNumberValue();
        return new LValue(new MSBooleanNode(lhs.compareTo(rhs) <= 0));
    }

    /**
     *
     * @param greaterArguments
     * @return
     */
    private static LValue interpretGreater(ArrayList<LValue> greaterArguments) throws MSArgumentMismatchException {
        if (greaterArguments.size() != 2) { throw new MSArgumentMismatchException(">", 2, greaterArguments.size()); }
        BigDecimal lhs = greaterArguments.get(0).getNumberValue();
        BigDecimal rhs = greaterArguments.get(1).getNumberValue();
        return new LValue(new MSBooleanNode(lhs.compareTo(rhs) > 0));
    }

    /**
     *
     * @param greaterEqualArguments
     * @return
     */
    private static LValue interpretGreaterEqual(ArrayList<LValue> greaterEqualArguments) throws MSArgumentMismatchException {
        if (greaterEqualArguments.size() != 2) { throw new MSArgumentMismatchException(">=", 2, greaterEqualArguments.size()); }
        BigDecimal lhs = greaterEqualArguments.get(0).getNumberValue();
        BigDecimal rhs = greaterEqualArguments.get(1).getNumberValue();
        return new LValue(new MSBooleanNode(lhs.compareTo(rhs) >= 0));
    }

    /**
     *
     * @param numericEqualArguments
     * @return
     */
    private static LValue interpretNumericEqual(ArrayList<LValue> numericEqualArguments) throws MSArgumentMismatchException {
        if (numericEqualArguments.size() != 2) { throw new MSArgumentMismatchException("=", 2, numericEqualArguments.size()); }
        BigDecimal lhs = numericEqualArguments.get(0).getNumberValue();
        BigDecimal rhs = numericEqualArguments.get(1).getNumberValue();
        return new LValue(new MSBooleanNode(lhs.equals(rhs)));
    }

    /**
     *
     * @param notArguments
     * @return
     */
    private static LValue interpretNot(ArrayList<LValue> notArguments) throws MSArgumentMismatchException {
        if (notArguments.size() != 1) { throw new MSArgumentMismatchException("not", 2, notArguments.size()); }
        LValue booleanArgument = notArguments.get(0);
        return new LValue(new MSBooleanNode(!booleanArgument.getBooleanValue()));
    }

    /**
     *
     * @param andArguments
     * @return
     */
    private static LValue interpretAnd(ArrayList<LValue> andArguments) {
        if (andArguments.isEmpty()) { return new LValue(new MSBooleanNode(false)); }
        for (LValue argument : andArguments) {
            if (!argument.getBooleanValue()) {
                return new LValue(new MSBooleanNode(false));
            }
        }
        return new LValue(new MSBooleanNode(true));
    }

    /**
     *
     * @param orArguments
     * @return
     */
    private static LValue interpretOr(ArrayList<LValue> orArguments) {
        if (orArguments.isEmpty()) { return new LValue(new MSBooleanNode(false)); }
        for (LValue argument : orArguments) {
            if (argument.getBooleanValue()) {
                return new LValue(new MSBooleanNode(true));
            }
        }
        return new LValue(new MSBooleanNode(false));
    }

    /**
     *
     * @param equalArguments
     * @return
     */
    private static LValue interpretEqualFunction(ArrayList<LValue> equalArguments) throws MSArgumentMismatchException {
        if (equalArguments.size() != 2) { throw new MSArgumentMismatchException("equal?", 2, equalArguments.size()); }
        LValue lhs = equalArguments.get(0);
        LValue rhs = equalArguments.get(1);

        if (lhs == rhs) { return new LValue(new MSBooleanNode(true)); }
        else if (lhs.getTree().getNodeType() == rhs.getTree().getNodeType()) {
            // Check the type.
            switch (lhs.getTree().getNodeType()) {
                case NUMBER: return new LValue(new MSBooleanNode(lhs.getNumberValue().equals(rhs.getNumberValue())));
                case STRING: return new LValue(new MSBooleanNode(lhs.getStringValue().equals(rhs.getStringValue())));
                case BOOLEAN: return new LValue(new MSBooleanNode(lhs.getBooleanValue() == rhs.getBooleanValue()));
                case CHARACTER: return new LValue(new MSBooleanNode(lhs.getCharacterValue() == rhs.getCharacterValue()));
                case SYMBOL: return new LValue(new MSBooleanNode(lhs.getSymbolValue().getStringRep().equals(rhs.getSymbolValue().getStringRep())));
                case LIST: return new LValue(new MSBooleanNode(lhs.getTree().getStringRep().equals(rhs.getTree().getStringRep())));
                default:
                    break;
            }
        }

        return new LValue(new MSBooleanNode(false));
    }

    /**
     *
     * @param equalArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretEqFunction(ArrayList<LValue> equalArguments) throws MSArgumentMismatchException {
        if (equalArguments.size() != 2) { throw new MSArgumentMismatchException("eq?", 2, equalArguments.size()); }
        LValue lhs = equalArguments.get(0);
        LValue rhs = equalArguments.get(1);
        if (lhs == rhs) { return new LValue(new MSBooleanNode(true)); }
        else if (lhs.getTree().getNodeType() == rhs.getTree().getNodeType()) {
            // Check the type.
            switch (lhs.getTree().getNodeType()) {
                case NUMBER: return new LValue(new MSBooleanNode(lhs.getNumberValue().equals(rhs.getNumberValue())));
                case STRING: return new LValue(new MSBooleanNode(lhs.getStringValue().equals(rhs.getStringValue())));
                case CHARACTER: return new LValue(new MSBooleanNode(lhs.getCharacterValue() == rhs.getCharacterValue()));
                case BOOLEAN: return new LValue(new MSBooleanNode(lhs.getBooleanValue() == rhs.getBooleanValue()));
                case SYMBOL: return new LValue(new MSBooleanNode(lhs.getSymbolValue().getStringRep().equals(rhs.getSymbolValue().getStringRep())));
                case VARIABLE: return new LValue(new MSBooleanNode(lhs.getTree().getStringRep().equals(rhs.getTree().getStringRep())));
                default:
                    break;
            }
        }

        return new LValue(new MSBooleanNode(false));
    }

    /**
     *
     * @param consArguments
     * @return
     */
    private static LValue interpretConsFunction(ArrayList<LValue> consArguments) {
        MSSyntaxTree lhs = LValue.getAst(consArguments.get(0));
        MSSyntaxTree rhs = LValue.getAst(consArguments.get(1));
        return new LValue(new MSListNode(lhs, rhs));
    }

    /**
     *
     * @param listArguments
     * @return
     */
    private static LValue interpretListFunction(ArrayList<LValue> listArguments) {
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
    private static LValue interpretCarFunction(ArrayList<LValue> carArguments) throws MSArgumentMismatchException {
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
    private static LValue interpretCdrFunction(ArrayList<LValue> cdrArguments) throws MSArgumentMismatchException {
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
     * @param nullArguments
     * @return
     */
    private static LValue interpretNullPredicate(ArrayList<LValue> nullArguments) throws MSArgumentMismatchException {
        if (nullArguments.size() != 1) { throw new MSArgumentMismatchException("null?", 1, nullArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(nullArguments.get(0));
        if (!argument.isList()) { return new LValue(new MSBooleanNode(false)); }
        return new LValue(new MSBooleanNode(((MSListNode) argument).isEmptyList()));
    }

    /**
     *
     * @param pairArguments
     * @return
     */
    private static LValue interpretPairPredicate(ArrayList<LValue> pairArguments) throws MSArgumentMismatchException {
        if (pairArguments.size() != 1) { throw new MSArgumentMismatchException("pair?", 1, pairArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(pairArguments.get(0));
        if (!argument.isList()) { return new LValue(new MSBooleanNode(false)); }
        MSListNode listArgument = (MSListNode) argument;
        return new LValue(new MSBooleanNode(!listArgument.isEmptyList()));
    }

    /**
     *
     * @param listArguments
     * @return
     */
    private static LValue interpretListPredicate(ArrayList<LValue> listArguments) throws MSArgumentMismatchException {
        if (listArguments.size() != 1) { throw new MSArgumentMismatchException("list?", 1, listArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(listArguments.get(0));
        if (!argument.isList()) { return new LValue(new MSBooleanNode(false)); }
        MSListNode listArgument = (MSListNode) argument;
        return new LValue(new MSBooleanNode(listArgument.isProper()));
    }

    /**
     *
     * @param stringAppendArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringAppendFunction(ArrayList<LValue> stringAppendArguments) throws MSArgumentMismatchException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringAppendArguments.size(); i++) {
            LValue currentArgument = stringAppendArguments.get(i);
            if (!currentArgument.getTree().isString()) {
                throw new MSArgumentMismatchException("string-append", i + 1, "string", currentArgument.getTree().getNodeType().toString());
            }
            stringBuilder.append(currentArgument.getTree().getStringRep());
        }
        return new LValue(new MSStringNode(stringBuilder.toString()));
    }

    /**
     *
     * @param stringLengthArguments
     * @return
     * @throws MSArgumentMismatchException
     */
    private static LValue interpretStringLengthFunction(ArrayList<LValue> stringLengthArguments) throws MSArgumentMismatchException {
        if (stringLengthArguments.size() != 1) { throw new MSArgumentMismatchException("string-length", 1, stringLengthArguments.size()); }
        MSStringNode stringArgument = (MSStringNode) LValue.getAst(stringLengthArguments.get(0));
        return new LValue(new MSNumberNode(stringArgument.length()));
    }

}
