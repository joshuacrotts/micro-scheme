package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.main.BuiltinOperator;
import com.joshuacrotts.minischeme.main.Environment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Joshua Crotts
 * @version 01/20/2022
 */
public class MSLambdaNode extends MSSyntaxTree {

    /**
     *
     */
    private final int NUM_LAMBDA_PARAMETERS;

    /**
     *
     */
    private final MSSyntaxTree IDENTIFIER;

    /**
     *
     */
    private Environment closureEnvironment;

    /**
     *
     */
    private boolean isClosure;

    public MSLambdaNode(MSSyntaxTree id, ArrayList<MSSyntaxTree> lambdaParameters,
                        MSSyntaxTree lambdaBody) {
        super(MSNodeType.LAMBDA);
        this.IDENTIFIER = id;
        this.closureEnvironment = new Environment();
        this.NUM_LAMBDA_PARAMETERS = lambdaParameters.size();
        lambdaParameters.forEach(this::addChild);
        this.addChild(lambdaBody);
        this.determineIfClosure();
    }

    public MSLambdaNode(ArrayList<MSSyntaxTree> lambdaParameters,
                        MSSyntaxTree lambdaBody) {
        this(null, lambdaParameters, lambdaBody);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> lambdaParametersCopy = new ArrayList<>();
        for (int i = 0; i < this.NUM_LAMBDA_PARAMETERS; i++) {
            lambdaParametersCopy.add(this.getChild(i).copy());
        }
        MSSyntaxTree lambdaBodyCopy = this.getChild(this.getChildrenSize() - 1).copy();
        return new MSLambdaNode(this.IDENTIFIER, lambdaParametersCopy, lambdaBodyCopy);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public ArrayList<MSSyntaxTree> getLambdaParameters() {
        ArrayList<MSSyntaxTree> lambdaParameters = new ArrayList<>();
        for (int i = 0; i < this.NUM_LAMBDA_PARAMETERS; i++) {
            lambdaParameters.add(this.getChild(i));
        }
        return lambdaParameters;
    }

    public MSSyntaxTree getLambdaBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }

    public Environment getClosureEnvironment() { return this.closureEnvironment; }

    public void setClosureEnvironment(final Environment closureEnvironment) {
        this.closureEnvironment = closureEnvironment;
    }

    public boolean isClosure() {
        return this.isClosure;
    }

    private void determineIfClosure() {
        MSSyntaxTree root = this;
        Queue<MSSyntaxTree> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            MSSyntaxTree curr = queue.poll();
            if (curr.isVariable()) {
                MSVariableNode v = (MSVariableNode) curr;
                if (this.closureConditions(v)) {
                    this.isClosure = true;
                    return;
                }
            }
            queue.addAll(curr.getChildren());
        }

        this.isClosure = false;
    }

    private boolean closureConditions(MSVariableNode var) {
        boolean matchesParam = this.matchesParameter(var);
        boolean isBuiltin = BuiltinOperator.isBuiltinOperator(var);
        if (this.IDENTIFIER != null) {
            return !matchesParam && !isBuiltin && !var.getStringRep().equals(this.IDENTIFIER.getStringRep());
        }
        return !matchesParam && !isBuiltin;
    }

    private boolean matchesParameter(MSVariableNode var) {
        ArrayList<MSSyntaxTree> lambdaParameters = this.getLambdaParameters();
        for (int i = 0; i < this.NUM_LAMBDA_PARAMETERS; i++) {
            MSVariableNode param = (MSVariableNode) lambdaParameters.get(i);
            if (param.getStringRep().equals(var.getStringRep())) {
                return true;
            }
        }

        return false;
    }

}
