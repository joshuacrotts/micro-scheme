/******************************************************************************
 *  File: MicroSchemeInterpreter.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/26/2022
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.main;

import com.joshuacrotts.microscheme.ast.*;
import com.joshuacrotts.microscheme.parser.MSArgumentMismatchException;
import com.joshuacrotts.microscheme.parser.MSInterpreterException;
import com.joshuacrotts.microscheme.parser.MSSemanticException;
import com.joshuacrotts.microscheme.parser.MSUndefinedSymbolException;
import com.joshuacrotts.microscheme.turtle.TurtleOperator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class MicroSchemeInterpreter {

    /**
     * MSSyntaxTree associated with this interpreter.
     */
    private MSSyntaxTree tree;
    private MSSyntaxTree ptree;

    private Environment penv;

    /**
     * Starting time of interpreter. Reset each time we go to a new node in the AST.
     */
    private long startTime;

    public MicroSchemeInterpreter(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
    }

    public MicroSchemeInterpreter() {
        this(null);
    }

    public void execute() {
        Environment globals = new Environment(null);
        for (int i = 0; i < this.tree.getChildrenSize(); i++) {
            this.startTime = System.nanoTime();
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
     * Evaluates a tree in a given environment.
     *
     * @param tree AST.
     * @param env Environment to use.
     * @return LValue of interpreted tree.
     *
     * @throws MSSemanticException if one of the submethod calls throws an exception.
     */
    private LValue interpretTree(MSSyntaxTree tree, Environment env) throws MSSemanticException {

        while (true) {
            switch (tree.getNodeType()) {
                case NUMBER:
                    return this.interpretNumber((MSNumberNode) tree);
                case BOOLEAN:
                    return this.interpretBoolean((MSBooleanNode) tree);
                case SYMBOL:
                    return this.interpretSymbol((MSSymbolNode) tree);
                case VARIABLE:
                    return this.interpretVariable((MSVariableNode) tree, env);
                case SEQUENCE:
                    tree = ((MSSequenceNode) tree).getChildren().get(0);
                    continue;
                case DECLARATION:
                    return this.interpretDeclaration((MSDeclarationNode) tree, env);
                case COND:
                    this.interpretCond((MSCondNode) tree, env);
                    tree = this.ptree;
                    env = this.penv;
                    continue;
                case LAMBDA:
                    return this.interpretLambda((MSLambdaNode) tree, env);
                case APPLICATION: {
                    LValue lv = this.interpretApplication((MSApplicationNode) tree, env);
                    if (lv != null) {
                        return lv;
                    } else {
                        tree = ptree;
                        env = penv;
                    }
                    continue;
                }
                default:
                    throw new MSInterpreterException("Unsupported node type " + tree.getNodeType());
            }
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
        else if (BuiltinOperator.isBuiltinOperator(variableNode) || TurtleOperator.isTurtleOperator(variableNode)) { return new LValue(variableNode, env); }
        else { throw new MSUndefinedSymbolException(variableNode.getStringRep()); }
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
    private LValue interpretDeclaration(final MSDeclarationNode declarationNode, final Environment env) throws MSSemanticException {
        LValue rExpr = this.interpretTree(declarationNode.getExpression(), env);
        if (BuiltinOperator.isBuiltinOperator(declarationNode.getVariable())) {
            throw new MSSemanticException("cannot define variable with builtin name " + declarationNode.getVariable().getStringRep());
        }
        env.bind(declarationNode.getVariable().getStringRep(), rExpr);
        return null;
    }

    /**
     * Interprets an eval. An eval simply takes in an expression, quoted or unquoted, and attempts to
     * evaluate it. If it is a variable, this is resolved to its value in the environment. From there,
     * if it is a symbol, we remove the quote. Then, there are two choices:
     *
     * 1. If the expression is not a list, we just pass down the unquoted symbol.
     * 2. Otherwise, we create a MSApplyNode with the CAR as the operator and CDR as operand.
     *
     * @param evalNode AST.
     * @param env current environment to evaluate inside.
     *
     * @return LValue of interpreting either the unquoted expression or the apply, whichever is applicable.
     */
    private LValue interpretEval(final MSEvalNode evalNode, final Environment env) throws MSSemanticException {
        // First, we want to resolve the expr argument. If it's a variable, retrieve it.
        MSSyntaxTree expression = LValue.getAst(this.interpretTree(evalNode.getExpression(), env));
        if (expression.isVariable()) { expression = LValue.getAst(this.interpretTree(expression, env)); }
        // Now, if it's a symbol, resolve that (i.e., get its value).
        if (expression.isSymbol()) { expression = ((MSSymbolNode) expression).getValue(); }
        // If it's a list, create an "apply" out of it.
        if (!expression.isList()) { return this.interpretTree(expression, env); }
        MSListNode listNode = (MSListNode) expression;
        return this.interpretTree(new MSApplyNode(listNode.getCar(), listNode.getCdr()), env);
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
        for (int i = 0; i < sequence.getChildrenSize(); i++) { returnValue = this.interpretTree(sequence.getChild(i), env); }
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
     * @throws MSArgumentMismatchException if the cond's predicate is not a predicate (i.e., does not reduce to boolean).
     * @throws MSSemanticException if the conditional does not have an else but requires one (all cases fall through).
     */
    private LValue interpretCond(final MSCondNode condNode, final Environment env) throws MSSemanticException {
        ArrayList<MSSyntaxTree> condPredicateList = condNode.getPredicateList();
        ArrayList<MSSyntaxTree> condConsequentList = condNode.getConsequentList();

        for (int i = 0; i < condPredicateList.size(); i++) {
            MSSyntaxTree currPredicate = condPredicateList.get(i);
            LValue currPredicateLValue = this.interpretTree(currPredicate, env);
            MSSyntaxTree predicateAst = LValue.getAst(currPredicateLValue);
            // If they don't enter a boolean, instead of throwing a type error, just interpret it as true.
            if (!predicateAst.isBoolean() || currPredicateLValue.getBooleanValue()) {
                ptree = condConsequentList.get(i);
                penv = env;
                return null;
            }
        }

        if (condNode.hasElse()) {
            ptree = condConsequentList.get(condConsequentList.size() - 1);
            penv = env;
        }
        return null;
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
     * Interprets an application node. An application is, effectively the "apply" function in many
     * Scheme interpreters. It takes the node (which contains the operator and operands/arguments,
     * as well as an environment to evaluate the arguments in.
     * 
     * Each argument is evaluated in env prior to application. A new environment E' is constructed 
     * with these arguments bound to E'. The body is then evaluated in E'.
     * 
     * @param applicationNode AST with operand and arguments. 
     * @param env Environment to evaluate arguments in, and to create child env.
     * 
     * @return LValue of interpreted application.
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
        if (BuiltinOperator.isBuiltinOperator(expressionLVal)) {
            return BuiltinOperator.interpretBuiltinOperator(expressionLVal, evaluatedArguments, env); }
        else {
            // If we're trying to call on a non-lambda, throw an exception.
            if (!expressionLVal.isLambda()) { throw new MSSemanticException("cannot call " + expressionLVal.getStringRep()); }

            // Otherwise, create the new environment, child bindings, and interpret the body.
            MSLambdaNode lambdaNode = (MSLambdaNode) expressionLVal;
            Environment lambdaEnvironment = lhsLValue.getEnvironment();
            ArrayList<MSSyntaxTree> lambdaParameters = lambdaNode.getLambdaParameters();

            // Check to see if this lambda is a varargs lambda. If so, convert the arguments to a list.
                // Before we bind, check arity (only on non-varargs procedures).
            this.ptree = lambdaNode.getLambdaBody();
            this.penv = lambdaEnvironment.createChildEnvironment(lambdaParameters, evaluatedArguments);
            return null;
        }
    }

    public void setInterpreterTree(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
    }
}
