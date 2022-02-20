package com.joshuacrotts.microscheme.turtle;

import com.joshuacrotts.microscheme.ast.MSSyntaxTree;
import com.joshuacrotts.microscheme.main.BuiltinOperator;
import com.joshuacrotts.microscheme.main.Environment;
import com.joshuacrotts.microscheme.main.LValue;
import com.joshuacrotts.microscheme.main.MSUtils;
import com.joshuacrotts.microscheme.parser.MSArgumentMismatchException;
import com.joshuacrotts.microscheme.parser.MSFunction;
import com.joshuacrotts.microscheme.parser.MSSemanticException;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class TurtleOperator {

    /**
     * Mapping of string procedure identifiers to their respective function calls.
     */
    private static final Map<String, MSFunction<ArrayList<LValue>, LValue>> TURTLE_OPERATORS;

    /**
     *
     */
    private static TurtleFrame turtleFrame;

    static {
        TURTLE_OPERATORS = new HashMap<>();
        TURTLE_OPERATORS.put("turtle-init", TurtleOperator::interpretTurtleInit);
        TURTLE_OPERATORS.put("forward", TurtleOperator::interpretTurtleForward);
        TURTLE_OPERATORS.put("turn-left", TurtleOperator::interpretTurtleTurnLeft);
        TURTLE_OPERATORS.put("set-pen-down", TurtleOperator::interpretTurtleSetPenDown);
        TURTLE_OPERATORS.put("change-color", TurtleOperator::interpretTurtleChangeColor);
        TURTLE_OPERATORS.put("change-bg-color", TurtleOperator::interpretTurtleChangeBgColor);
    }

    /**
     *
     * @param expressionNode
     * @return
     */
    public static boolean isTurtleOperator(final MSSyntaxTree expressionNode) {
        return TurtleOperator.TURTLE_OPERATORS.containsKey(expressionNode.getStringRep());
    }

    public static LValue interpretTurtleOperator(final MSSyntaxTree expressionNode,
                                                  final ArrayList<LValue> evalArguments,
                                                  final Environment env) throws MSSemanticException {
        if (!expressionNode.isVariable()) { return null; }
        return TurtleOperator.TURTLE_OPERATORS.get(expressionNode.getStringRep()).apply(evalArguments);
    }

    public static LValue interpretTurtleInit(final ArrayList<LValue> initOperands) {
        if (initOperands.size() != 4) { throw new MSArgumentMismatchException("turtle-init", 4, initOperands.size()); }
        int centerX = initOperands.get(0).getNumberValue().re.intValue();
        int centerY = initOperands.get(1).getNumberValue().re.intValue();
        int width = initOperands.get(2).getNumberValue().re.intValue();
        int height = initOperands.get(3).getNumberValue().re.intValue();
        turtleFrame = new TurtleFrame(width, height, centerX, centerY);
        return null;
    }

    public static LValue interpretTurtleForward(final ArrayList<LValue> forwardOperands) {
        if (forwardOperands.size() != 1) { throw new MSArgumentMismatchException("forward", 1, forwardOperands.size()); }
        int steps = forwardOperands.get(0).getNumberValue().re.intValue();
        turtleFrame.getTurtle().moveForward(steps);
        return null;
    }

    public static LValue interpretTurtleTurnLeft(final ArrayList<LValue> turnLeftOperands) {
        if (turnLeftOperands.size() != 1) { throw new MSArgumentMismatchException("turn-left", 1, turnLeftOperands.size()); }
        int angle = turnLeftOperands.get(0).getNumberValue().re.intValue();
        turtleFrame.getTurtle().turn(angle);
        return null;
    }

    public static LValue interpretTurtleSetPenDown(final ArrayList<LValue> setPenDownOperands) {
        if (setPenDownOperands.size() != 1) { throw new MSArgumentMismatchException("set-pen-down", 1, setPenDownOperands.size()); }
        boolean isDown = setPenDownOperands.get(0).getBooleanValue();
        turtleFrame.getTurtle().setPenDown(isDown);
        return null;
    }

    public static LValue interpretTurtleChangeColor(final ArrayList<LValue> changeColorOperands) {
        if (changeColorOperands.size() != 1) { throw new MSArgumentMismatchException("change-color", 1, changeColorOperands.size()); }
        String sColor = changeColorOperands.get(0).getTree().getStringRep();
        turtleFrame.getTurtle().setPenColor(MSUtils.extractStringHexColor(sColor));
        return null;
    }

    public static LValue interpretTurtleChangeBgColor(final ArrayList<LValue> changeBgColorOperands) {
        if (changeBgColorOperands.size() != 1) { throw new MSArgumentMismatchException("change-bg-color", 1, changeBgColorOperands.size()); }
        String color = changeBgColorOperands.get(0).getTree().getStringRep();
        try {
            turtleFrame.getPanel().setBgColor((Color) Class.forName("java.awt.Color").getField(color).get(null));
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
