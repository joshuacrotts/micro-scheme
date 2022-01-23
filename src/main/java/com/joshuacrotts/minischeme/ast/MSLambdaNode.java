package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.main.BuiltinOperator;
import com.joshuacrotts.minischeme.main.Environment;
import org.antlr.v4.runtime.misc.Pair;

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
    private Environment closureEnvironment;

    /**
     *
     */
    private boolean isClosure;

    public MSLambdaNode(ArrayList<MSSyntaxTree> lambdaParameters,
                        MSSyntaxTree lambdaBody) {
        super(MSNodeType.LAMBDA);
        this.closureEnvironment = new Environment();
        this.NUM_LAMBDA_PARAMETERS = lambdaParameters.size();
        lambdaParameters.forEach(this::addChild);
        this.addChild(lambdaBody);
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> lambdaParametersCopy = new ArrayList<>();
        for (int i = 0; i < this.NUM_LAMBDA_PARAMETERS; i++) {
            lambdaParametersCopy.add(this.getChild(i).copy());
        }
        MSSyntaxTree lambdaBodyCopy = this.getChild(this.getChildrenSize() - 1).copy();
        return new MSLambdaNode(lambdaParametersCopy, lambdaBodyCopy);
    }

    @Override
    public String getStringRep() {
        StringBuilder sb = new StringBuilder();
        sb.append("(lambda");

        ArrayList<MSSyntaxTree> params =this.getLambdaParameters();
        sb.append(" (");
        for (int i = 0; i < params.size() - 1; i++) {
            sb.append(params.get(i).getStringRep());
            sb.append(" ");
        }
        sb.append(params.get(params.size() - 1).getStringRep()).append(")");
        sb.append(" ");
        sb.append(this.getLambdaBody().getStringRep());
        sb.append(")");
        return sb.toString();
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
        this.determineIfClosure();
    }

    public boolean isClosure() {
        return this.isClosure;
    }

    /**
     *
     */
    private void determineIfClosure() {
        Queue<Pair<MSSyntaxTree, MSLambdaNode>> queue = new LinkedList<>();
        Pair<MSSyntaxTree, MSLambdaNode> root = new Pair<>(this, this);
        queue.add(root);
        while (!queue.isEmpty()) {
            Pair<MSSyntaxTree, MSLambdaNode> curr = queue.poll();
            // If it's a lambda, then we want to add all of its children and
            // tell its pair that a is the lambda we want to check.
            if (curr.a.isLambda()) {
                for (MSSyntaxTree ch : curr.a.getChildren()) {
                    queue.add(new Pair<>(ch, (MSLambdaNode) curr.a));
                }
                continue;
            } else if (curr.a.isVariable()) {
                // Otherwise, if it's a variable, check it against its lambda parent.
                if (this.closureConditions((MSVariableNode) curr.a, curr.b)) {
                    this.isClosure = true;
                    return;
                }
            }

            // If it's not a variable or it was found as a match, just add all the children.
            for (MSSyntaxTree ch : curr.a.getChildren()) {
                queue.add(new Pair<>(ch, curr.b));
            }
        }

        this.isClosure = false;
    }

    /**
     *
     * @param var
     * @param lambdaNode
     * @return
     */
    private boolean closureConditions(MSVariableNode var, MSLambdaNode lambdaNode) {
        boolean matchesParam = this.matchesParameter(var, lambdaNode);
        boolean matchesEnvSymbol = this.matchesEnvironmentSymbol(var);
        boolean isBuiltin = BuiltinOperator.isBuiltinOperator(var);
        return !matchesParam && !matchesEnvSymbol && !isBuiltin;
    }

    /**
     *
     * @param var
     * @param lambdaRoot
     * @return
     */
    private boolean matchesParameter(MSVariableNode var, MSLambdaNode lambdaRoot) {
        ArrayList<MSSyntaxTree> lambdaParameters = lambdaRoot.getLambdaParameters();
        for (int i = 0; i < lambdaRoot.NUM_LAMBDA_PARAMETERS; i++) {
            MSVariableNode param = (MSVariableNode) lambdaParameters.get(i);
            if (param.getStringRep().equals(var.getStringRep())) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param var
     * @return
     */
    private boolean matchesEnvironmentSymbol(MSVariableNode var) {
        for (int i = 0; i < this.closureEnvironment.numberOfBindings(); i++) {
            MSSyntaxTree envVar = this.closureEnvironment.findInEnvironment(var);
            if (envVar != null && envVar.isLambda()) {
                return true;
            }
        }
        return false;
    }
}
