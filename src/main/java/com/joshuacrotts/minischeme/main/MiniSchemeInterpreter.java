package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.*;
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
     *
     */
    private final EnvironmentStack bindings;

    /**
     * MSSyntaxTree associated with this interpreter.
     */
    private MSSyntaxTree tree;

    public MiniSchemeInterpreter(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
        this.bindings = new EnvironmentStack();
        this.bindings.addEnvironment();
    }

    public MiniSchemeInterpreter() {
        this(null);
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
            case BOOLEAN: return this.interpretBoolean((MSBooleanNode) tree);
            case CHARACTER: return this.interpretCharacter((MSCharacterNode) tree);
            case STRING: return this.interpretString((MSStringNode) tree);
            case VARIABLE: return this.interpretVariable((MSVariableNode) tree);
            case SYMBOL: return this.interpretSymbol((MSSymbolNode) tree);
            case DECLARATION: return this.interpretDeclaration((MSDeclaration) tree);
            case COND: return this.interpretCond((MSCondNode) tree);
            case LAMBDA: return this.interpretLambda((MSLambdaNode) tree);
            case APPLICATION: return this.interpretApplication((MSApplicationNode) tree);
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
     * @param booleanNode
     * @return
     */
    private LValue interpretBoolean(MSBooleanNode booleanNode) {
        return new LValue(booleanNode);
    }

    /**
     *
     * @param stringNode
     * @return
     */
    private LValue interpretString(MSStringNode stringNode) {
        return new LValue(stringNode);
    }

    /**
     *
     * @param characterNode
     * @return
     */
    private LValue interpretCharacter(MSCharacterNode characterNode) { return new LValue(characterNode); }

    /**
     *
     * @param variableNode
     * @return
     */
    private LValue interpretVariable(MSVariableNode variableNode) throws MSSemanticException {
        // We REALLY need to check and see if the data is a primitive operator or not...
        MSSyntaxTree variableData = this.bindings.lookup(variableNode);
        if (variableData != null) {
            return new LValue(variableData);
        } else if (BuiltinOperator.isBuiltinOperator(variableNode)) {
            return new LValue(variableNode);
        } else {
            throw new MSSemanticException("undefined identifier '" + variableNode.getStringRep() + "'");
        }
    }

    /**
     *
     * @param symbolNode
     * @return
     */
    private LValue interpretSymbol(MSSymbolNode symbolNode) {
        return new LValue(symbolNode.getValue());
    }

    /**
     *
     * @param declarationNode
     * @return
     */
    private LValue interpretDeclaration(MSDeclaration declarationNode) throws MSSemanticException {
        MSSyntaxTree rExpr = LValue.getAst(this.interpretTree(declarationNode.getExpression()));
        this.bindings.bind(declarationNode.getVariable(), rExpr);
        if (rExpr.isLambda()) { ((MSLambdaNode) rExpr).setClosureEnvironment(this.bindings.peekEnvironment()); }
        return null;
    }

    /**
     *
     * @param condNode
     * @return
     */
    private LValue interpretCond(MSCondNode condNode) throws MSSemanticException {
        ArrayList<MSSyntaxTree> condPredicateList = condNode.getPredicateList();
        ArrayList<MSSyntaxTree> condConsequentList = condNode.getConsequentList();

        for (int i = 0; i < condPredicateList.size(); i++) {
            MSSyntaxTree currPredicate = condPredicateList.get(i);
            LValue currPredicateLValue = this.interpretTree(currPredicate);
            if (currPredicateLValue.getBooleanValue()) {
                return this.interpretTree(condConsequentList.get(i));
            }
        }

        if (condNode.hasElse()) { return this.interpretTree(condConsequentList.get(condConsequentList.size() - 1)); }
        throw new MSSemanticException("Cannot evaluate an undefined expression");
    }

    /**
     *
     * @param lambdaNode
     * @return
     */
    private LValue interpretLambda(MSLambdaNode lambdaNode) {
        return new LValue(lambdaNode);
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
            LValue lhs = this.interpretTree(rhsArg);
            evaluatedArguments.add(lhs);
        }

        // Now, check to see if it's a primitive.
        MSSyntaxTree expressionLVal = LValue.getAst(this.interpretTree(applicationNode.getExpression()));
        if (BuiltinOperator.isBuiltinOperator(expressionLVal)) { return BuiltinOperator.interpretBuiltinOperator(expressionLVal, evaluatedArguments); }
        else {
            // Create the bindings and interpret the body.
            MSLambdaNode lambdaNode = (MSLambdaNode) expressionLVal;
            Environment lambdaEnvironment = lambdaNode.isClosure() ? lambdaNode.getClosureEnvironment() : new Environment();
            ArrayList<MSSyntaxTree> lambdaParameters = lambdaNode.getLambdaParameters();
            MSSyntaxTree lambdaBody = lambdaNode.getLambdaBody();

            // Before we bind, check arity.
            if (lambdaParameters.size() != evaluatedArguments.size()) {
                throw new MSSemanticException("arity mismatch. Expected: "
                        + lambdaParameters.size() + ", Got: " + evaluatedArguments.size());
            }

            // Now, do the bindings.
            for (int i = 0; i < lambdaParameters.size(); i++) {
                // If there's a preexisting mapping, use that.
                // Causes test 16 to fail...
                if (lambdaEnvironment.findInEnvironment(lambdaParameters.get(i)) == null) {
                    lambdaEnvironment.bind(lambdaParameters.get(i), LValue.getAst(evaluatedArguments.get(i)));
                }
            }

            // Push the lambda's environment to the stack... This may not be right.
            this.bindings.addEnvironment(lambdaEnvironment);
            LValue lambdaLValue = this.interpretTree(lambdaBody);
            this.bindings.popEnvironment();
            return lambdaLValue;
        }
    }

    public void setInterpreterTree(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
    }
}
