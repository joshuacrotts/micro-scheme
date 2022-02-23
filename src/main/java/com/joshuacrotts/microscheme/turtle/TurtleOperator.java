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
        TURTLE_OPERATORS.put("pos-x", TurtleOperator::interpretTurtlePosX);
        TURTLE_OPERATORS.put("pos-y", TurtleOperator::interpretTurtlePosY);
        TURTLE_OPERATORS.put("goto", TurtleOperator::interpretTurtleGoTo);
        TURTLE_OPERATORS.put("turn-left", TurtleOperator::interpretTurtleTurnLeft);
        TURTLE_OPERATORS.put("begin-fill", TurtleOperator::interpretTurtleBeginFill);
        TURTLE_OPERATORS.put("end-fill", TurtleOperator::interpretTurtleEndFill);
        TURTLE_OPERATORS.put("set-pen-down", TurtleOperator::interpretTurtleSetPenDown);
        TURTLE_OPERATORS.put("set-pen-filled", TurtleOperator::interpretTurtleSetPenFilled);
        TURTLE_OPERATORS.put("change-pen-width", TurtleOperator::interpretTurtleChangePenWidth);
        TURTLE_OPERATORS.put("change-color", TurtleOperator::interpretTurtleChangeColor);
        TURTLE_OPERATORS.put("change-fill-color", TurtleOperator::interpretTurtleChangeFillColor);
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
        if (initOperands.size() != 4) {
            throw new MSArgumentMismatchException("turtle-init", 4, initOperands.size());
        }
        int centerX = initOperands.get(0).getNumberValue().re.intValue();
        int centerY = initOperands.get(1).getNumberValue().re.intValue();
        int width = initOperands.get(2).getNumberValue().re.intValue();
        int height = initOperands.get(3).getNumberValue().re.intValue();
        turtleFrame = new TurtleFrame(width, height, centerX, centerY);
        return null;
    }

    public static LValue interpretTurtlePosX(final ArrayList<LValue> posXOperands) {
        if (posXOperands.size() != 0) { throw new MSArgumentMismatchException("pos-x", 0, posXOperands.size()); }
        return new LValue(turtleFrame.getTurtle().getX());
    }

    public static LValue interpretTurtlePosY(final ArrayList<LValue> posYOperands) {
        if (posYOperands.size() != 0) { throw new MSArgumentMismatchException("pos-y", 0, posYOperands.size()); }
        return new LValue(turtleFrame.getTurtle().getY());
    }

    public static LValue interpretTurtleForward(final ArrayList<LValue> forwardOperands) {
        if (forwardOperands.size() != 1) { throw new MSArgumentMismatchException("forward", 1, forwardOperands.size()); }
        int steps = forwardOperands.get(0).getNumberValue().re.intValue();
        turtleFrame.getTurtle().moveForward(steps);
        return null;
    }

    public static LValue interpretTurtleGoTo(final ArrayList<LValue> goToOperands) {
        if (goToOperands.size() != 2) { throw new MSArgumentMismatchException("goto", 1, goToOperands.size()); }
        double x = goToOperands.get(0).getNumberValue().re.doubleValue();
        double y = goToOperands.get(1).getNumberValue().re.doubleValue();
        turtleFrame.getTurtle().goTo(x, y);
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

    public static LValue interpretTurtleSetPenFilled(final ArrayList<LValue> setPenFilledOperands) {
        if (setPenFilledOperands.size() != 1) { throw new MSArgumentMismatchException("set-pen-filled", 1, setPenFilledOperands.size()); }
        boolean penFilled = setPenFilledOperands.get(0).getBooleanValue();
        turtleFrame.getTurtle().setPenFilled(penFilled);
        return null;
    }

    public static LValue interpretTurtleBeginFill(final ArrayList<LValue> beginFillOperands) {
        if (beginFillOperands.size() != 0) { throw new MSArgumentMismatchException("begin-fill", 1, beginFillOperands.size()); }
        turtleFrame.getTurtle().beginFill();
        return null;
    }

    public static LValue interpretTurtleEndFill(final ArrayList<LValue> endFillOperands) {
        if (endFillOperands.size() != 0) { throw new MSArgumentMismatchException("end-fill", 1, endFillOperands.size()); }
        turtleFrame.getTurtle().endFill();
        return null;
    }

    public static LValue interpretTurtleChangePenWidth(final ArrayList<LValue> changePenWidthOperands) {
        if (changePenWidthOperands.size() != 1) { throw new MSArgumentMismatchException("change-pen-width", 1, changePenWidthOperands.size()); }
        int penWidth = changePenWidthOperands.get(0).getNumberValue().re.intValue();
        turtleFrame.getTurtle().setPenWidth(penWidth);
        return null;
    }

    public static LValue interpretTurtleChangeColor(final ArrayList<LValue> changeColorOperands) {
        if (changeColorOperands.size() != 1) { throw new MSArgumentMismatchException("change-color", 1, changeColorOperands.size()); }
        String sColor = changeColorOperands.get(0).getTree().getStringRep();
        turtleFrame.getTurtle().setPenColor(MSUtils.extractStringHexColor(sColor));
        return null;
    }

    public static LValue interpretTurtleChangeFillColor(final ArrayList<LValue> changeFillColorOperands) {
        if (changeFillColorOperands.size() != 1) { throw new MSArgumentMismatchException("change-fill-color", 1, changeFillColorOperands.size()); }
        String sColor = changeFillColorOperands.get(0).getTree().getStringRep();
        turtleFrame.getTurtle().setFillColor(MSUtils.extractStringHexColor(sColor));
        return null;
    }

    public static LValue interpretTurtleChangeBgColor(final ArrayList<LValue> changeBgColorOperands) {
        if (changeBgColorOperands.size() != 1) { throw new MSArgumentMismatchException("change-bg-color", 1, changeBgColorOperands.size()); }
        String sColor = changeBgColorOperands.get(0).getTree().getStringRep();
        turtleFrame.getPanel().setBgColor(MSUtils.extractStringHexColor(sColor));
        return null;
    }
}
