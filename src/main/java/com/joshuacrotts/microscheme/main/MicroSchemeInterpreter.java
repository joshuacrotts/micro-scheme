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
    private LValue interpretTree(final MSSyntaxTree tree, final Environment env) throws MSSemanticException {
        if (System.nanoTime() - this.startTime > MicroSchemeRunner.interpreterTimeout) {
            System.err.println("Computation timed out!");
            System.exit(1);
        }
        switch (tree.getNodeType()) {
            case NUMBER: return this.interpretNumber((MSNumberNode) tree);
            case BOOLEAN: return this.interpretBoolean((MSBooleanNode) tree);
            case CHARACTER: return this.interpretCharacter((MSCharacterNode) tree);
            case STRING: return this.interpretString((MSStringNode) tree);
            case SYMBOL: return this.interpretSymbol((MSSymbolNode) tree);
            case QUASISYMBOL: return this.interpretQuasiSymbol((MSQuasiSymbolNode) tree, env);
            case VARIABLE: return this.interpretVariable((MSVariableNode) tree, env);
            case SEQUENCE: return this.interpretSequence((MSSequenceNode) tree, env);
            case DECLARATION: return this.interpretDeclaration((MSDeclarationNode) tree, env);
            case SET: return this.interpretSet((MSSetNode) tree, env);
            case SETCAR: return this.interpretSetCar((MSSetNode) tree, env);
            case SETCDR: return this.interpretSetCdr((MSSetNode) tree, env);
            case SETVECTOR: return this.interpretSetVector((MSSetNode) tree, env);
            case AND: return this.interpretAnd((MSAndNode) tree, env);
            case OR: return this.interpretOr((MSOrNode) tree, env);
            case COND: return this.interpretCond((MSCondNode) tree, env);
            case LETREC: return this.interpretLetRec((MSLetRecNode) tree, env);
            case LAMBDA: return this.interpretLambda((MSLambdaNode) tree, env);
            case DO: return this.interpretDo((MSDoNode) tree, env);
            case EVAL: return this.interpretEval((MSEvalNode) tree, env);
            case APPLY: return this.interpretApply((MSApplyNode) tree, env);
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
     * Interprets a quasi-symbol. A quasi-symbol is just a quoted expression that has the potential to have
     * "unquote" sections. An unquoted section is a section that should get evaluated when interpreting the
     * parent expression.
     *
     * Example: `(1 2 ,(+ 3 4) 5) evaluates to (1 2 7 5) because the comma (,) indicates that the next expression
     *          is to be evaluated.
     *
     * List splicing is also supported. List splicing allows elements of a sublist to be added to the current
     * list.
     *
     * Example: `(1 2 ,@(list 3 4 5) 6) evaluates to (1 2 3 4 5 6) because the list expands outward. If we
     *          did not have the @ operator, it would evaluate to (1 2 (3 4 5) 6).
     *
     * @param quasiSymbolNode AST.
     * @param env Environment to evaluate this quasi-symbol in.
     * @return LValue containing the list constructed from this quasi quote.
     */
    private LValue interpretQuasiSymbol(final MSQuasiSymbolNode quasiSymbolNode, final Environment env) {
        // If it's a quasi-list, we need to create a new list and interpret each element that is unquoted.
        ArrayList<MSSyntaxTree> quasiNodes = quasiSymbolNode.getSymbolList();
        MSListNode currList = null;
        for (int i = quasiNodes.size() - 1; i >= 0; i--) {
            MSSyntaxTree quasi = quasiNodes.get(i);
            // If it's not a symbol, we just need to evaluate it.
            if (!quasi.isSymbol()) { quasi = LValue.getAst(this.interpretTree(quasi, env)); }
            else {
                MSSymbolNode symbol = (MSSymbolNode) quasi;
                // If it's a "quasi-at-symbol", then we need to store its elements *inside* the list.
                if (symbol.isQuasiAtSymbol()) {
                    MSSyntaxTree symbolValue = LValue.getAst(this.interpretTree(symbol.getValue(), env));
                    if (!symbolValue.isList()) {
                        throw new MSArgumentMismatchException(",@", "list/cons pair", symbolValue.getStringNodeType());
                    }
                    // Extract each element from the symbol list and append it to the curr list.
                    ArrayList<MSSyntaxTree> symbolList = ((MSListNode) symbolValue).getListAsArrayList();
                    for (int j = symbolList.size() - 1; j >= 0; j--) {
                        MSSyntaxTree rhsElement = symbolList.get(j);
                        currList = new MSListNode(rhsElement, currList);
                    }
                    continue;
                }
            }
            currList = new MSListNode(quasi, currList);
        }
        return new LValue(Optional.ofNullable(currList).orElse(MSListNode.EMPTY_LIST), env);
    }

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
     * Interprets a boolean AND node. Short-circuits the operands; if one expression is false,
     * the remaining expressions are not evaluated.
     *
     * @param andNode AST.
     * @param env Environment to evaluate AND in.
     * @return LValue true if all expressions evaluate to true, false otherwise.
     */
    private LValue interpretAnd(final MSAndNode andNode, final Environment env) {
        for (MSSyntaxTree operand : andNode.getChildren()) {
            LValue lhs = this.interpretTree(operand, env);
            if (!lhs.getBooleanValue()) { return new LValue(false); }
        }
        return new LValue(true);
    }

    /**
     * Interprets a boolean OR node. Short-circuits the operands; if one expression is true,
     * the remaining expressions are not evaluated.
     *
     * @param orNode AST.
     * @param env Environment to evaluate OR in.
     * @return LValue true if at least one expression evaluates to true, false otherwise.
     */
    private LValue interpretOr(final MSOrNode orNode, final Environment env) {
        for (MSSyntaxTree operand : orNode.getChildren()) {
            LValue lhs = this.interpretTree(operand, env);
            if (lhs.getBooleanValue()) { return new LValue(true); }
        }
        return new LValue(false);
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
                return this.interpretTree(condConsequentList.get(i), env);
            }
        }

        if (condNode.hasElse()) { return this.interpretTree(condConsequentList.get(condConsequentList.size() - 1), env); }
        return null;
    }

    /**
     * Interprets a DO iterative loop. A do loop in Scheme has a declaration section, "variable steps",
     * a test, a list of "true" expressions, and then the body.
     *
     * Do sets up the variable bindings in a new local environment. It then evaluates the do test, and
     * if it is true, we evaluate the "do true" expressions. Otherwise, we evaluate the body of the
     * do loop. Finally, the "variable steps" (i.e., a list of SETs) are processed.
     *
     * @param doNode AST.
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
            MSDeclarationNode declaration = (MSDeclarationNode) formal;
            doFormals.add(declaration.getVariable());
            evalDoArguments.add(this.interpretTree(declaration.getExpression(), env));
        }

        // Then, create the local environment used for the do.
        Environment doEnv = env.createChildEnvironment(doFormals, evalDoArguments);
        while (true) {
            // Evaluate the test expression. If true, evaluate the true args and return the LValue of the last.
            LValue testLVal = this.interpretTree(doNode.getDoTest(), doEnv);
            MSSyntaxTree testAst = LValue.getAst(testLVal);
            if (!testAst.isBoolean()) {
                throw new MSArgumentMismatchException("do test", "predicate/true/false", testAst.getStringNodeType());
            } else {
                // If the do loop test passes, evaluate each "true" expression.
                if (testLVal.getBooleanValue()) {
                    LValue trueLVal = null;
                    for (MSSyntaxTree trueExpr : doNode.getDoTrueExpressions()) {
                        trueLVal = this.interpretTree(trueExpr, doEnv);
                    }
                    return trueLVal;
                } else {
                    // Otherwise, evaluate the body then do the step. LValues are irrelevant.
                    LValue body = this.interpretTree(doNode.getDoBody(), doEnv);
                    if (body != null) { System.out.println(body); }
                    for (MSSyntaxTree setExpr : doNode.getDoSetExpressions()) {
                        LValue setLVal = this.interpretTree(setExpr, doEnv);
                    }
                }
            }
        }
    }

    /**
     * Evaluates a letrec expression. A letrec is a recursive let procedure (i.e., allows the programmer to
     * call a lambda procedure defined in the let declarations inside the body of the let. This probably isn't
     * the most useful thing in the world, but it's here because it can't be converted into a lambda.
     *
     * The bindings are created inside an environment with env as the parent. This environment must be constructed
     * because any lambdas in the bindings must have their env set as the new environment or they won't be
     * recognized.
     *
     * @param letRecNode AST.
     * @param env parent Environment to create the child Environment for the let from.
     * @return LValue of evaluated let body.
     */
    private LValue interpretLetRec(final MSLetRecNode letRecNode, final Environment env) {
        ArrayList<LValue> expressionList = new ArrayList<>();

        // Create the new environment so we can bind our let declarations in it.
        Environment newEnv = new Environment(env);
        for (MSSyntaxTree ast : letRecNode.getDeclarationList()) {
            MSDeclarationNode decl = (MSDeclarationNode) ast;
            expressionList.add(new LValue(decl.getExpression(), newEnv));
        }

        newEnv.createBindings(letRecNode.getVariableList(), expressionList);
        return this.interpretTree(letRecNode.getBody(), newEnv);
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
     * Interprets an "apply" node. Apply works very similarly to "application" nodes with the exception
     * that apply takes an operator and a list rather than separate arguments. The procedure is collectively
     * applied to (as the name suggests!) each element of the list. To make things easier, after checking
     * the node, we create a MSApplicationNode since it does all the heavy lifting.
     *
     * @param applyNode AST.
     * @param env current environment to evaluate ApplyNode in.
     *
     * @return LValue of creating and interpreting the new MSApplicationNode.
     *
     * @throws MSArgumentMismatchException if we try to pass a non list/cons pair to apply.
     */
    private LValue interpretApply(final MSApplyNode applyNode, final Environment env) throws MSArgumentMismatchException {
        // First, we want to resolve the apply node's argument.
        MSSyntaxTree argument = applyNode.getArgumentList();
        ArrayList<MSSyntaxTree> applyArguments = new ArrayList<>();
        if (argument.isVariable()) { argument = LValue.getAst(this.interpretTree(argument, env)); }
        // Now check to make sure it's a symbol or list.
        if (argument.isSymbol()) { argument = ((MSSymbolNode) argument).getValue(); }
        // Finally, check to make sure it's a list.
        if (!argument.isList()) { throw new MSArgumentMismatchException("apply", 1, "list/cons pair", argument.getStringNodeType()); }

        MSListNode curr = (MSListNode) argument;
        while (true) {
            if (curr.isEmptyList()) { break; }
            else {
                applyArguments.add(curr.getCar());
                curr = (MSListNode) curr.getCdr();
            }
        }

        MSSyntaxTree procedure = applyNode.getProcedure();
        return this.interpretTree(new MSApplicationNode(procedure, applyArguments), env);
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
        if (BuiltinOperator.isBuiltinOperator(expressionLVal)) { return BuiltinOperator.interpretBuiltinOperator(expressionLVal, evaluatedArguments, env); }
        else if (TurtleOperator.isTurtleOperator(expressionLVal)) { return TurtleOperator.interpretTurtleOperator(expressionLVal, evaluatedArguments, env); }
        else {
            // If we're trying to call on a non-lambda, throw an exception.
            if (!expressionLVal.isLambda()) { throw new MSSemanticException("cannot call " + expressionLVal.getStringRep()); }

            // Otherwise, create the new environment, child bindings, and interpret the body.
            MSLambdaNode lambdaNode = (MSLambdaNode) expressionLVal;
            Environment lambdaEnvironment = lhsLValue.getEnvironment();
            ArrayList<MSSyntaxTree> lambdaParameters = lambdaNode.getLambdaParameters();
            MSSyntaxTree lambdaBody = lambdaNode.getLambdaBody();
            Environment childEnvironment;

            // Check to see if this lambda is a varargs lambda. If so, convert the arguments to a list.
            if (lambdaNode.isVariableArguments()) {
                childEnvironment = new Environment(lambdaEnvironment);
                childEnvironment.createBindings(lambdaParameters, new ArrayList<>
                        (Collections.singletonList(new LValue(new MSListNode(evaluatedArguments), lambdaEnvironment))));
            } else {
                // Before we bind, check arity (only on non-varargs procedures).
                if (lambdaParameters.size() != evaluatedArguments.size()) {
                    throw new MSArgumentMismatchException(lambdaParameters.size(), evaluatedArguments.size());
                }
                childEnvironment = lambdaEnvironment.createChildEnvironment(lambdaParameters, evaluatedArguments);
            }
            return this.interpretTree(lambdaBody, childEnvironment);
        }
    }

    /**
     * Interprets a SET! Scheme procedure. SET! is only used for redefining a variable as a new expression.
     * With this in mind, the left-hand side can only be a variable - not something that reduces to an 
     * lvalue. The set expression is evaluated in the current environment, which it is then bound to. We 
     * bind the value at every environment that is a parent of the current env.
     * 
     * @param setNode AST with set assignee and expression to assign.
     * @param env current Environment.
     * 
     * @return null, because set expressions never return a value.
     * 
     * @throws MSArgumentMismatchException if the lhs expression is not a variable.
     * @throws MSUndefinedSymbolException if either the expression throws an exception while evaluating, or the 
     *                                    variable is not bound in the environment or its parent.
     */
    private LValue interpretSet(final MSSetNode setNode, final Environment env) throws MSSemanticException {
        MSSyntaxTree assignee = setNode.getChild(0);
        LValue evaluatedExpression = this.interpretTree(setNode.getChild(1), env);
        if (!assignee.isVariable()) { throw new MSArgumentMismatchException("set!", 0, "variable", assignee.getStringNodeType()); }
        // Walk up the environment tree to find the identifier.
        String id = ((MSVariableNode) assignee).getIdentifier();
        Environment curr = env;
        boolean found = false;
        while (curr != null) {
            LValue lookupSymbol = curr.lookup(id);
            if (lookupSymbol != null) { found = true; curr.bind(id, evaluatedExpression); }
            curr = curr.getParent();
        }
        
        if (!found) { throw new MSUndefinedSymbolException(id); }
        return null;
    }

    /**
     * Interprets a set-car! expression. set-car! takes an lvalue that is equivalent to a list or cons pair,
     * then assigns its CAR as the expression in the SetNode AST.
     * 
     * @param setNode AST passed as a reference. The list/cons pair, itself, is rebound, rather than the symbol
     *                in its environment.
     * @param env current Environment.
     * 
     * @return null, because set expressions never return a value.
     * 
     * @throws MSArgumentMismatchException if the first argument is not a list or cons pair.
     */
    private LValue interpretSetCar(final MSSetNode setNode, final Environment env) throws MSSemanticException {
        LValue evaluatedAssignee = this.interpretTree(setNode.getChild(0), env);
        LValue evaluatedExpression = this.interpretTree(setNode.getChild(1), env);
        MSSyntaxTree assigneeAst = LValue.getAst(evaluatedAssignee);
        if (!assigneeAst.isList()) { throw new MSArgumentMismatchException("set-car!", 0, "list/cons pair", assigneeAst.getStringNodeType()); }
        ((MSListNode) assigneeAst).setCar(LValue.getAst(evaluatedExpression));
        return null;
    }

    /**
     * Interprets a set-cdr! expression. set-cdr! takes an lvalue that is equivalent to a list or cons pair,
     * then assigns its CDR as the expression in the SetNode AST.
     * 
     * @param setNode AST passed as a reference. The list/cons pair, itself, is rebound, rather than the symbol
     *                in its environment.
     * @param env current Environment.
     * 
     * @return null, because set expressions never return a value.
     * 
     * @throws MSArgumentMismatchException if the first argument is not a list or cons pair.
     */
    private LValue interpretSetCdr(final MSSetNode setNode, final Environment env) throws MSSemanticException {
        LValue evaluatedAssignee = this.interpretTree(setNode.getChild(0), env);
        LValue evaluatedExpression = this.interpretTree(setNode.getChild(1), env);
        MSSyntaxTree assigneeAst = LValue.getAst(evaluatedAssignee);
        if (!assigneeAst.isList()) { throw new MSArgumentMismatchException("set-cdr!", 0, "list/cons pair", assigneeAst.getStringNodeType()); }
        ((MSListNode) assigneeAst).setCdr(LValue.getAst(evaluatedExpression));
        return null;
    }

    /**
     * Interprets a vector-set! expression. vector-set! takes an lvalue that is equivalent to a vector,
     * then assigns the provided index to the evaluated expression.
     * 
     * @param setNode AST passed as a reference. The vector, itself, is rebound, rather than the symbol
     *                in its environment.
     * @param env current Environment.
     * 
     * @return null, because set expressions never return a value.
     * 
     * @throws MSArgumentMismatchException if the first argument is not a list or cons pair.
     *                                     It also throws an exception when vectorIdx is not a number.
     */
    private LValue interpretSetVector(final MSSetNode setNode, final Environment env) throws MSSemanticException {
        LValue evaluatedAssignee = this.interpretTree(setNode.getChild(0), env);
        LValue vectorIdx = this.interpretTree(setNode.getChild(1), env);
        LValue evaluatedExpression = this.interpretTree(setNode.getChild(2), env);
        MSSyntaxTree assigneeAst = LValue.getAst(evaluatedAssignee);
        MSSyntaxTree vectorIdxAst = LValue.getAst(vectorIdx);
        if (!assigneeAst.isVector()) { throw new MSArgumentMismatchException("vector-set!", 0, "vector", assigneeAst.getStringNodeType()); }
        else if (!LValue.getAst(vectorIdx).isNumber()) { throw new MSArgumentMismatchException("vector-set!", 1, "number", vectorIdxAst.getStringNodeType()); }
        assigneeAst.setChild(vectorIdx.getNumberValue().re.intValue(), LValue.getAst(evaluatedExpression));
        return null;
    }

    public void setInterpreterTree(final MSSyntaxTree interpreterTree) {
        this.tree = interpreterTree;
    }
}
