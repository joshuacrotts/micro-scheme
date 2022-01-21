package com.joshuacrotts.minischeme.main;

import ch.obermuhlner.math.big.BigDecimalMath;
import ch.obermuhlner.math.big.BigFloat;
import com.joshuacrotts.minischeme.ast.MSBooleanNode;
import com.joshuacrotts.minischeme.ast.MSListNode;
import com.joshuacrotts.minischeme.ast.MSNumberNode;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.parser.MSArgumentMismatchException;
import com.joshuacrotts.minischeme.parser.MSSemanticException;

import java.math.BigDecimal;
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
        return false;
    }

    /**
     *
     * @param expressionNode
     * @param evalArguments
     * @return
     */
    public static LValue interpretBuiltinOperator(MSSyntaxTree expressionNode, ArrayList<LValue> evalArguments) throws MSSemanticException {
        if (!expressionNode.isVariable()) { return null; }
        switch (expressionNode.getStringRep()) {
            case "+": return BuiltinOperator.interpretAdd(evalArguments);
            case "-": return BuiltinOperator.interpretSubtract(evalArguments);
            case "*": return BuiltinOperator.interpretMultiply(evalArguments);
            case "/": return BuiltinOperator.interpretDivide(evalArguments);
            case "**": return BuiltinOperator.interpretPower(evalArguments);
            case "<": return BuiltinOperator.interpretLess(evalArguments);
            case "<=": return BuiltinOperator.interpretLessEqual(evalArguments);
            case ">": return BuiltinOperator.interpretGreater(evalArguments);
            case ">=": return BuiltinOperator.interpretGreaterEqual(evalArguments);
            case "=": return BuiltinOperator.interpretNumericEqual(evalArguments);
            case "cons": return BuiltinOperator.interpretConsFunction(evalArguments);
            case "list": return BuiltinOperator.interpretListFunction(evalArguments);
            case "car": return BuiltinOperator.interpretCarFunction(evalArguments);
            case "cdr": return BuiltinOperator.interpretCdrFunction(evalArguments);
            case "null?": return BuiltinOperator.interpretNullFunction(evalArguments);
            default:
                return null;
        }
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
        return new LValue(new MSNumberNode(dividend.divide(divisor)));
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
    private static LValue interpretNullFunction(ArrayList<LValue> nullArguments) throws MSArgumentMismatchException {
        if (nullArguments.size() != 1) { throw new MSArgumentMismatchException("null?", 1, nullArguments.size()); }
        MSSyntaxTree argument = LValue.getAst(nullArguments.get(0));
        if (!argument.isList()) { return new LValue(new MSBooleanNode(false)); }
        return new LValue(new MSBooleanNode(argument.getChildrenSize() == 0));
    }

}
