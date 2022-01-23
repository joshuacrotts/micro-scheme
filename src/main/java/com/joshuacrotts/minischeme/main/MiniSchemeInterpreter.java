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
     * MSSyntaxTree associated with this interpreter.
     */
    private MSSyntaxTree tree;

    public MiniSchemeInterpreter(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
    }

    public MiniSchemeInterpreter() {
        this(null);
    }

    /**
     *
     */
    public void execute() {
        Environment globals = new Environment(null);
        for (int i = 0; i < this.tree.getChildrenSize(); i++) {
            MSSyntaxTree currNode = this.tree.getChild(i);
            try {
                LValue result = this.interpretTree(currNode, globals);
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
    private LValue interpretTree(MSSyntaxTree tree, Environment env) throws MSSemanticException {
        switch (tree.getNodeType()) {
            case NUMBER: return this.interpretNumber((MSNumberNode) tree);
            case BOOLEAN: return this.interpretBoolean((MSBooleanNode) tree);
            case CHARACTER: return this.interpretCharacter((MSCharacterNode) tree);
            case STRING: return this.interpretString((MSStringNode) tree);
            case VARIABLE: return this.interpretVariable((MSVariableNode) tree, env);
            case SYMBOL: return this.interpretSymbol((MSSymbolNode) tree);
            case DECLARATION: return this.interpretDeclaration((MSDeclaration) tree, env);
            case COND: return this.interpretCond((MSCondNode) tree, env);
            case LAMBDA: return this.interpretLambda((MSLambdaNode) tree, env);
            case APPLICATION: return this.interpretApplication((MSApplicationNode) tree, env);
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
    private LValue interpretVariable(MSVariableNode variableNode, Environment env) throws MSSemanticException {
        // We REALLY need to check and see if the data is a primitive operator or not...
        LValue variableData = env.lookup(variableNode.getIdentifier());
        if (variableData != null) { return variableData; }
        else if (BuiltinOperator.isBuiltinOperator(variableNode)) { return new LValue(variableNode, env); }
        else { throw new MSSemanticException("undefined identifier '" + variableNode.getStringRep() + "'"); }
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
    private LValue interpretDeclaration(MSDeclaration declarationNode, Environment env) throws MSSemanticException {
        LValue rExpr = this.interpretTree(declarationNode.getExpression(), env);
        env.bind(declarationNode.getVariable(), rExpr);
        return null;
    }

    /**
     *
     * @param condNode
     * @return
     */
    private LValue interpretCond(MSCondNode condNode, Environment env) throws MSSemanticException {
        ArrayList<MSSyntaxTree> condPredicateList = condNode.getPredicateList();
        ArrayList<MSSyntaxTree> condConsequentList = condNode.getConsequentList();

        for (int i = 0; i < condPredicateList.size(); i++) {
            MSSyntaxTree currPredicate = condPredicateList.get(i);
            LValue currPredicateLValue = this.interpretTree(currPredicate, env);
            if (currPredicateLValue.getBooleanValue()) {
                return this.interpretTree(condConsequentList.get(i), env);
            }
        }

        if (condNode.hasElse()) { return this.interpretTree(condConsequentList.get(condConsequentList.size() - 1), env); }
        throw new MSSemanticException("Cannot evaluate an undefined expression");
    }

    /**
     *
     * @param lambdaNode
     * @return
     */
    private LValue interpretLambda(MSLambdaNode lambdaNode, Environment env) {
        return new LValue(lambdaNode, env);
    }

    /**
     *
     * @param applicationNode
     * @return
     */
    private LValue interpretApplication(MSApplicationNode applicationNode, Environment env) throws MSSemanticException {
        // First, interpret all the children.
        ArrayList<MSSyntaxTree> rhsArguments = applicationNode.getArguments();
        ArrayList<LValue> evaluatedArguments = new ArrayList<>();
        for (MSSyntaxTree rhsArg : rhsArguments) {
            LValue lhs = this.interpretTree(rhsArg, env);
            evaluatedArguments.add(lhs);
        }

        // Now, check to see if it's a primitive.
        LValue lv = this.interpretTree(applicationNode.getExpression(), env);
        MSSyntaxTree expressionLVal = LValue.getAst(lv);
        if (BuiltinOperator.isBuiltinOperator(expressionLVal)) {
            // --- DOES NOT FIX --- //
            return BuiltinOperator.interpretBuiltinOperator(expressionLVal, evaluatedArguments, env);
        }
        else {
            // Create the bindings and interpret the body.
            MSLambdaNode lambdaNode = (MSLambdaNode) expressionLVal;
            // --- DOES NOT FIX --- //
            Environment lambdaEnvironment = lv.env;//lambdaNode.isClosure() ? lambdaNode.getClosureEnvironment() : new Environment();
            ArrayList<MSSyntaxTree> lambdaParameters = lambdaNode.getLambdaParameters();
            MSSyntaxTree lambdaBody = lambdaNode.getLambdaBody();

            // Before we bind, check arity.
            if (lambdaParameters.size() != evaluatedArguments.size()) {
                throw new MSSemanticException("arity mismatch. Expected: "
                        + lambdaParameters.size() + ", Got: " + evaluatedArguments.size());
            }


            Environment e1 = lambdaEnvironment.createChildEnvironment(lambdaParameters, evaluatedArguments);
            return this.interpretTree(lambdaBody, e1);
        }
    }

    public void setInterpreterTree(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
    }
}
