package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.MSApplicationNode;
import com.joshuacrotts.minischeme.ast.MSNumberNode;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.ast.MSVariableNode;
import com.joshuacrotts.minischeme.parser.MSArgumentMismatchException;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;
import com.joshuacrotts.minischeme.parser.MSSemanticException;

import java.util.ArrayList;

/**
 *
 * @author Joshua Crotts
 * @version 01/19/2022
 */
public class MiniSchemeInterpreter {

    /**
     * MSSyntaxTree associated with this interpreter.
     */
    private MSSyntaxTree tree;

    public MiniSchemeInterpreter(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
    }

    public MiniSchemeInterpreter() {
    }

    /**
     *
     */
    public void execute() {
        for (int i = 0; i < this.tree.getChildrenSize(); i++) {
            MSSyntaxTree currNode = this.tree.getChild(i);
            try {
                LValue result = this.interpretTree(currNode);
                if (result != null) {
                    System.out.println(result);
                }
            } catch (MSSemanticException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    /**
     *
     * @param tree
     * @return
     */
    private LValue interpretTree(MSSyntaxTree tree) throws MSSemanticException {
        switch (tree.getNodeType()) {
            case NUMBER: return this.interpretNumber((MSNumberNode) tree);
            case APPLICATION: return this.interpretApplication((MSApplicationNode) tree);
            case VARIABLE: return this.interpretVariable((MSVariableNode) tree);
            default:
                throw new MSInterpreterException("Unsupported node type " + tree.getNodeType());
        }
    }

    /**
     *
     * @param numberNode
     * @return
     */
    private LValue interpretNumber(MSNumberNode numberNode) {
        return new LValue(numberNode);
    }

    /**
     *
     * @param variableNode
     * @return
     */
    private LValue interpretVariable(MSVariableNode variableNode) {
        return new LValue(variableNode);
    }

    /**
     *
     * @param applicationNode
     * @return
     */
    private LValue interpretApplication(MSApplicationNode applicationNode) throws MSSemanticException {
        // First, interpret all the children.
        ArrayList<MSSyntaxTree> rhsArguments = applicationNode.getArguments();
        ArrayList<LValue> evaluatedArguments = new ArrayList<>();
        for (MSSyntaxTree rhsArg : rhsArguments) {
            evaluatedArguments.add(this.interpretTree(rhsArg));
        }

        // Now, check to see if it's a primitive.
        MSSyntaxTree expressionLVal = LValue.getAst(this.interpretTree(applicationNode.getExpression()));
        LValue primitiveLVal = BuiltinOperator.interpretBuiltinOperator(expressionLVal, evaluatedArguments);

        if (primitiveLVal != null) { return primitiveLVal; }
        else { throw new MSInterpreterException("It's not a primitive, but we can't support that yet!"); }
    }

    public void setInterpreterTree(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
    }
}
