package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.ast.*;
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
                if (result != null) { System.out.println(result); }
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
    private LValue interpretTree(final MSSyntaxTree tree, final Environment env) throws MSSemanticException {
        switch (tree.getNodeType()) {
            case NUMBER: return this.interpretNumber((MSNumberNode) tree);
            case BOOLEAN: return this.interpretBoolean((MSBooleanNode) tree);
            case CHARACTER: return this.interpretCharacter((MSCharacterNode) tree);
            case STRING: return this.interpretString((MSStringNode) tree);
            case SYMBOL: return this.interpretSymbol((MSSymbolNode) tree);
            case VARIABLE: return this.interpretVariable((MSVariableNode) tree, env);
            case SEQUENCE: return this.interpretSequence((MSSequenceNode) tree, env);
            case DECLARATION: return this.interpretDeclaration((MSDeclaration) tree, env);
            case SET: return this.interpretSet((MSSetNode) tree, env);
            case SETCAR: return this.interpretSetCar((MSSetNode) tree, env);
            case SETCDR: return this.interpretSetCdr((MSSetNode) tree, env);
            case SETVECTOR: return this.interpretSetVector((MSSetNode) tree, env);
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
    private LValue interpretNumber(final MSNumberNode numberNode) {
        return new LValue(numberNode);
    }

    /**
     *
     * @param booleanNode
     * @return
     */
    private LValue interpretBoolean(final MSBooleanNode booleanNode) {
        return new LValue(booleanNode);
    }

    /**
     *
     * @param stringNode
     * @return
     */
    private LValue interpretString(final MSStringNode stringNode) {
        return new LValue(stringNode);
    }

    /**
     *
     * @param characterNode
     * @return
     */
    private LValue interpretCharacter(final MSCharacterNode characterNode) { return new LValue(characterNode); }

    /**
     *
     * @param symbolNode
     * @return
     */
    private LValue interpretSymbol(final MSSymbolNode symbolNode) { return new LValue(symbolNode.getValue()); }

    /**
     *
     * @param variableNode
     * @return
     */
    private LValue interpretVariable(final MSVariableNode variableNode, final Environment env) throws MSSemanticException {
        LValue variableData = env.lookup(variableNode.getIdentifier());
        if (variableData != null) { return variableData; }
        else if (BuiltinOperator.isBuiltinOperator(variableNode)) { return new LValue(variableNode, env); }
        else { throw new MSSemanticException("undefined identifier '" + variableNode.getStringRep() + "'"); }
    }

    /**
     *
     * @param declarationNode
     * @return
     */
    private LValue interpretDeclaration(final MSDeclaration declarationNode, final Environment env) throws MSSemanticException {
        LValue rExpr = this.interpretTree(declarationNode.getExpression(), env);
        env.bind(declarationNode.getVariable().getStringRep(), rExpr);
        return null;
    }

    /**
     *
     * @param sequence
     * @param env
     * @return
     * @throws MSSemanticException
     */
    private LValue interpretSequence(final MSSequenceNode sequence, final Environment env) throws MSSemanticException {
        LValue returnValue = null;
        for (int i = 0; i < sequence.getChildrenSize(); i++) {
            returnValue = this.interpretTree(sequence.getChild(i), env);
        }
        return returnValue;
    }

    /**
     *
     * @param condNode
     * @return
     */
    private LValue interpretCond(final MSCondNode condNode, final Environment env) throws MSSemanticException {
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
    private LValue interpretLambda(final MSLambdaNode lambdaNode, final Environment env) {
        return new LValue(lambdaNode, env);
    }

    /**
     *
     * @param applicationNode
     * @return
     */
    private LValue interpretApplication(final MSApplicationNode applicationNode, final Environment env) throws MSSemanticException {
        // First, interpret all the children.
        ArrayList<MSSyntaxTree> rhsArguments = applicationNode.getArguments();
        ArrayList<LValue> evaluatedArguments = new ArrayList<>();
        for (MSSyntaxTree rhsArg : rhsArguments) {
            LValue lhs = this.interpretTree(rhsArg, env);
            evaluatedArguments.add(lhs);
        }

        // Now, check to see if the left-hand side (the caller) is a primitive.
        LValue lhsLValue = this.interpretTree(applicationNode.getExpression(), env);
        MSSyntaxTree expressionLVal = LValue.getAst(lhsLValue);
        if (BuiltinOperator.isBuiltinOperator(expressionLVal)) { return BuiltinOperator.interpretBuiltinOperator(expressionLVal, evaluatedArguments, env); }
        else {
            // Create the bindings and interpret the body.
            MSLambdaNode lambdaNode = (MSLambdaNode) expressionLVal;
            Environment lambdaEnvironment = lhsLValue.getEnvironment();
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

    /**
     *
     * @param setNode
     * @param env
     * @return
     * @throws MSSemanticException
     */
    private LValue interpretSet(final MSSetNode setNode, final Environment env) throws MSSemanticException {
        MSSyntaxTree assignee = setNode.getChild(0);
        LValue evaluatedExpression = this.interpretTree(setNode.getChild(1), env);
        if (!assignee.isVariable()) { throw new MSArgumentMismatchException("set!", 0, "variable", assignee.getNodeType().toString()); }
        String id = ((MSVariableNode) assignee).getIdentifier();
        LValue lookupSymbol = env.lookup(id);
        if (lookupSymbol != null) { env.bind(id, evaluatedExpression); return null; }
        throw new MSSemanticException("undefined identifier '" + id + "'");
    }

    /**
     *
     * @param setNode
     * @param env
     * @return
     * @throws MSSemanticException
     */
    private LValue interpretSetCar(final MSSetNode setNode, final Environment env) throws MSSemanticException {
        LValue evaluatedAssignee = this.interpretTree(setNode.getChild(0), env);
        LValue evaluatedExpression = this.interpretTree(setNode.getChild(1), env);

        MSSyntaxTree assigneeAst = LValue.getAst(evaluatedAssignee);
        if (!assigneeAst.isList()) { throw new MSArgumentMismatchException("set-car!", 0, "list/cons pair", assigneeAst.getNodeType().toString()); }
        ((MSListNode) assigneeAst).setCar(LValue.getAst(evaluatedExpression));
        return null;
    }

    /**
     *
     * @param setNode
     * @param env
     * @return
     * @throws MSSemanticException
     */
    private LValue interpretSetCdr(final MSSetNode setNode, final Environment env) throws MSSemanticException {
        LValue evaluatedAssignee = this.interpretTree(setNode.getChild(0), env);
        LValue evaluatedExpression = this.interpretTree(setNode.getChild(1), env);

        MSSyntaxTree assigneeAst = LValue.getAst(evaluatedAssignee);
        if (!assigneeAst.isList()) { throw new MSArgumentMismatchException("set-cdr!", 0, "list/cons pair", assigneeAst.getNodeType().toString()); }
        ((MSListNode) assigneeAst).setCdr(LValue.getAst(evaluatedExpression));
        return null;
    }

    /**
     *
     * @param setNode
     * @param env
     * @return
     * @throws MSSemanticException
     */
    private LValue interpretSetVector(final MSSetNode setNode, final Environment env) throws MSSemanticException {
        LValue evaluatedAssignee = this.interpretTree(setNode.getChild(0), env);
        LValue vectorIdx = this.interpretTree(setNode.getChild(1), env);
        LValue evaluatedExpression = this.interpretTree(setNode.getChild(2), env);
        MSSyntaxTree assigneeAst = LValue.getAst(evaluatedAssignee);
        if (!assigneeAst.isVector()) { throw new MSArgumentMismatchException("vector-set!", 0, "vector", assigneeAst.getNodeType().toString()); }
        ((MSVectorNode) assigneeAst).setChild(vectorIdx.getNumberValue().intValue(), LValue.getAst(evaluatedExpression));
        return null;
    }

    public void setInterpreterTree(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
    }
}
