package com.joshuacrotts.minischeme.main;

import ch.obermuhlner.math.big.BigDecimalMath;
import ch.obermuhlner.math.big.BigFloat;
import com.joshuacrotts.minischeme.ast.MSBooleanNode;
import com.joshuacrotts.minischeme.ast.MSNumberNode;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.parser.MSArgumentMismatchException;
import com.joshuacrotts.minischeme.parser.MSSemanticException;

import java.math.BigDecimal;
import java.util.ArrayList;

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
}
