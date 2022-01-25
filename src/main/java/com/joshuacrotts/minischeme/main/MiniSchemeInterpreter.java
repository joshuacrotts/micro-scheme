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
            case DO: return this.interpretDo((MSDoNode) tree, env);
            case APPLICATION: return this.interpretApplication((MSApplicationNode) tree, env);
            default:
                throw new MSInterpreterException("Unsupported node type " + tree.getNodeType());
        }
    }

    /**
     * Converts a MSNumberNode AST into an LValue.
     *  
     * @param numberNode AST
     * 
     * @return LValue with number.
     */
    private LValue interpretNumber(final MSNumberNode numberNode) {
        return new LValue(numberNode);
    }

    /**
     * Converts a MSBooleanNode AST into an LValue.
     * 
     * @param booleanNode AST
     * 
     * @return LValue with boolean.
     */
    private LValue interpretBoolean(final MSBooleanNode booleanNode) {
        return new LValue(booleanNode);
    }

    /**
     * Converts a MSStringNode AST into an LValue.
     * 
     * @param stringNode AST
     * 
     * @return LValue with string.
     */
    private LValue interpretString(final MSStringNode stringNode) {
        return new LValue(stringNode);
    }

    /**
     * Converts a MSCharacterNode AST into an LValue.
     * 
     * @param characterNode AST
     * 
     * @return LValue with character.
     */
    private LValue interpretCharacter(final MSCharacterNode characterNode) { return new LValue(characterNode); }

    /**
     * Converts a MSSymbolNode AST into an LValue.
     * 
     * @param symbolNode AST
     * 
     * @return LValue with symbol.
     */
    private LValue interpretSymbol(final MSSymbolNode symbolNode) { return new LValue(symbolNode.getValue()); }

    /**
     * Interprets a variable in the provided environment. If it is not found in the passed environment or its
     * parent and it's not an operator, an error is displayed.
     * 
     * @param variableNode AST
     * @param env Environment to look variable up in
     * 
     * @return LValue of variable data in environment. If it is a builtin operator, we just return the variableNode.
     * 
     * @throws MSSemanticException if variableNode is not found in env and it's not builtin.
     */
    private LValue interpretVariable(final MSVariableNode variableNode, final Environment env) throws MSSemanticException {
        LValue variableData = env.lookup(variableNode.getIdentifier());
        if (variableData != null) { return variableData; }
        else if (BuiltinOperator.isBuiltinOperator(variableNode)) { return new LValue(variableNode, env); }
        else { throw new MSSemanticException("undefined identifier '" + variableNode.getStringRep() + "'"); }
    }

    /**
     * Interprets a declaration. We first evaluate the right-hand side, then bind it to the passed environment.
     * 
     * @param declarationNode AST.
     * @param env Environment to store declaration in.
     * 
     * @return null (declarations do not return any values).
     * 
     * @throws MSSemanticException if an exception is thrown when interpreting the rhs.
     */
    private LValue interpretDeclaration(final MSDeclaration declarationNode, final Environment env) throws MSSemanticException {
        LValue rExpr = this.interpretTree(declarationNode.getExpression(), env);
        env.bind(declarationNode.getVariable().getStringRep(), rExpr);
        return null;
    }

    /**
     * Interprets a sequence of expressions.
     * 
     * @param sequence AST
     * @param env Environment to interpret the sequence of expressions in.
     * 
     * @return LValue of last expression evaluated in the sequence.
     * 
     * @throws MSSemanticException if an exception is thrown when interpreting the sequence.
     */
    private LValue interpretSequence(final MSSequenceNode sequence, final Environment env) throws MSSemanticException {
        LValue returnValue = null;
        for (int i = 0; i < sequence.getChildrenSize(); i++) {
            returnValue = this.interpretTree(sequence.getChild(i), env);
        }
        return returnValue;
    }

    /**
     * Interprets a conditional (cond or if). Each conditional is evaluated from left-to-right, and if
     * this conditional is true, we evaluate its corresponding consequent expression. If there is an
     * else expression and none of the cond expressions are true, it is evaluated last.
     * 
     * @param condNode AST of either a COND or an IF.
     * @param env Environment to evaluate the conditionals in.
     * 
     * @return LValue of consequent expression evaluated.
     * 
     * @throws MSSemanticException if the conditional does not have an else but requires one (all cases fall through).
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
     * Interprets a lambda AST.
     * 
     * @param lambdaNode AST
     * @param env Environment to evaluate the lambda in.
     * 
     * @return LValue wrapping the lambda and its current environment.
     */
    private LValue interpretLambda(final MSLambdaNode lambdaNode, final Environment env) {
        return new LValue(lambdaNode, env);
    }

    /**
     * Interprets a DO iterative loop. A do loop in Scheme has a declaration section, "variable steps",
     * a test, a list of "true" expressions, and then the body. 
     * 
     * Do sets up the variable bindings in a new local environment. It then evaluates the do test, and 
     * if it is true, we evaluate the "do true" expressions. Otherwise, we evaluate the body of the 
     * do loop. Finally, the "variable steps" (i.e., a list of SETs) are processed. 
     * 
     * @param doNode AST
     * @param env parent environment of the do node.
     * 
     * @return LValue of the last "true" expression evaluated.
     * 
     * @throws MSSemanticException if the "test" is not boolean expression, or a sub-evaluation throws
     *         an exception.
     */
    private LValue interpretDo(final MSDoNode doNode, final Environment env) throws MSSemanticException {
        // First, set up the declarations and evaluate their expressions.
        ArrayList<MSSyntaxTree> doFormals = new ArrayList<>();
        ArrayList<LValue> evalDoArguments = new ArrayList<>();
        for (MSSyntaxTree formal : doNode.getDoDeclarations()) {
            MSDeclaration declaration = (MSDeclaration) formal;
            doFormals.add(declaration.getVariable());
            evalDoArguments.add(this.interpretTree(declaration.getExpression(), env));
        }

        // Then, create the local environment used for the do.
        Environment doEnv = env.createChildEnvironment(doFormals, evalDoArguments);
        while (true) {
            // Evaluate the test expression. If true, evaluate the true args and return the LValue of the last.
            LValue testLVal = this.interpretTree(doNode.getDoTest(), doEnv);
            if (testLVal.getBooleanValue()) {
                LValue trueLVal = null;
                for (MSSyntaxTree trueExpr : doNode.getDoTrueExpressions()) {
                    trueLVal = this.interpretTree(trueExpr, doEnv);
                }
                return trueLVal;
            } else {
                // Otherwise, evaluate the body then do the step. LValues are irrelevant.
                LValue body = this.interpretTree(doNode.getDoBody(), doEnv);
                if (body != null)
                    System.out.println(body);
                for (MSSyntaxTree setExpr : doNode.getDoSetExpressions()) {
                    LValue setLVal = this.interpretTree(setExpr, doEnv);
                }
            }
        }
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
        Environment curr = env;
        while (curr != null) {
            LValue lookupSymbol = curr.lookup(id);
            if (lookupSymbol != null) { curr.bind(id, evaluatedExpression); }
            curr = curr.getParent();
        }
        return null;
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
