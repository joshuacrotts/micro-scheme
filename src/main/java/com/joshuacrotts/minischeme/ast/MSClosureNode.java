package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.symbol.Environment;

import java.util.ArrayList;

/**
 *
 */
public class MSClosureNode extends MSSyntaxTree {

    /**
     *
     */
    private final int NUM_LET_DECLS;

    /**
     *
     */
    private Environment localEnvironment;

    public MSClosureNode(ArrayList<MSSyntaxTree> letDecls, MSSyntaxTree lambdaDeclaration) {
        super(MSNodeType.CLOSURE);
        this.localEnvironment = new Environment();
        this.NUM_LET_DECLS = letDecls.size();
        letDecls.forEach(this::addChild);
        this.addChild(lambdaDeclaration);
    }

    private MSClosureNode(ArrayList<MSSyntaxTree> letDecls, MSSyntaxTree lambdaDeclaration, Environment env) {
        this(letDecls, lambdaDeclaration);
        this.localEnvironment = env;
    }

    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> letDeclsCopy = new ArrayList<>();
        for (int i = 0; i < this.NUM_LET_DECLS; i++) {
            letDeclsCopy.add(this.getChild(i).copy());
        }

        MSSyntaxTree lambdaDeclCopy = this.getLambdaDeclaration().copy();
        return new MSClosureNode(letDeclsCopy, lambdaDeclCopy, this.localEnvironment);
    }

    public ArrayList<MSSyntaxTree> getLetDeclarations() {
        ArrayList<MSSyntaxTree> letDecls = new ArrayList<>();
        for (int i = 0; i < this.NUM_LET_DECLS; i++) {
            letDecls.add(this.getChild(i));
        }
        return letDecls;
    }

    public MSSyntaxTree getLambdaDeclaration() {
        return this.getChild(this.getChildrenSize() - 1);
    }

    public Environment getEnvironment() {
        return this.localEnvironment;
    }
}
