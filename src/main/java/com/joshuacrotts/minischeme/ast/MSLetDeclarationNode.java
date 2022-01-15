package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

/**
 *
 *
 * @author Joshua Crotts
 * @version 12/30/2021
 */
public class MSLetDeclarationNode extends MSDeclaration {

    /**
     * Number of variables declared in this let block.
     */
    private final int NUM_DECLARATIONS;

    /**
     * Keeps track of what "type" of let declaration this is. For instance,
     * let vs let* vs letrec.
     */
    private final LetType LET_TYPE;

    public MSLetDeclarationNode(final LetType letType, final ArrayList<MSSyntaxTree> declarations,
                                final MSSyntaxTree letBody) {
        super(MSNodeType.LET_DECL);
        this.LET_TYPE = letType;
        this.NUM_DECLARATIONS = declarations.size();
        declarations.forEach(this::addChild);
        this.addChild(letBody);
    }

    public MSLetDeclarationNode(final LetType letType, final MSSyntaxTree identifier,
                                ArrayList<MSSyntaxTree> declarations, final MSSyntaxTree letBody) {
        super(MSNodeType.LET_DECL);
        this.LET_TYPE = letType;
        this.NUM_DECLARATIONS = declarations.size();
        this.addChild(identifier);
        declarations.forEach(this::addChild);
        this.addChild(letBody);
    }

    public MSProcedureDeclarationNode createProcedureDeclaration() {
        if (this.LET_TYPE == LetType.LET_NAMED) {
            ArrayList<MSSyntaxTree> parameters = new ArrayList<>();
            for (MSSyntaxTree decl : this.getDeclarations()) {
                parameters.add(((MSVariableDeclarationNode) decl).getIdentifier());
            }
            return new MSProcedureDeclarationNode(this.getChild(0), parameters, this.getBody());
        }

        throw new IllegalArgumentException("Internal interpreter error - this" +
                "let declaration should be a named let declaration");
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> declarationsCopy = new ArrayList<>();
        for (int i = 0; i < this.NUM_DECLARATIONS; i++) {
            declarationsCopy.add(this.getChild(i).copy());
        }
        MSSyntaxTree letBodyCopy = this.getChild(this.getChildrenSize() - 1).copy();
        return new MSLetDeclarationNode(this.LET_TYPE, declarationsCopy, letBodyCopy);
    }

    public LetType getLetType() {
        return this.LET_TYPE;
    }

    public MSIdentifierNode getIdentifier() {
        if (this.LET_TYPE == LetType.LET_NAMED) {
            return (MSIdentifierNode) this.getChild(0);
        }
        throw new IllegalArgumentException("unable to get identifier for non-named let");
    }

    public ArrayList<MSSyntaxTree> getDeclarations() {
        int offset = this.LET_TYPE == LetType.LET_NAMED ? 1 : 0;
        ArrayList<MSSyntaxTree> declarations = new ArrayList<>();
        for (int i = 0; i < this. NUM_DECLARATIONS; i++) {
            declarations.add(this.getChild(i + offset));
        }
        return declarations;
    }

    public MSSyntaxTree getBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public enum LetType {
        LET,
        LET_STAR,
        LET_REC,
        LET_NAMED
    }
}
