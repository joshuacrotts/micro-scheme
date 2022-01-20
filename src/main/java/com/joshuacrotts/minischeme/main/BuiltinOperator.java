package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSApplicationNode;
import com.joshuacrotts.minischeme.ast.MSNumberNode;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.ast.MSVariableNode;
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
}
