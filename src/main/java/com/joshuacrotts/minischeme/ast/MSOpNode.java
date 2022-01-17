package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.MiniSchemeParser;

/**
 * Defines an operation expression. An operation expression can be either unary or n-ary, where unary
 * takes one argument and n-ary takes n.
 * <p>
 * CHILD 0...n-1: expressions to evaluate with the operator.
 *
 * @author Joshua Crotts
 * @version 12/20/2021
 */
public class MSOpNode extends MSSyntaxTree {

    /**
     * Operation type. This should be a token type from the parser.
     */
    private final int OP_TYPE;

    /**
     * Group associated with the operator. The group refers to the parser
     * rule denoting how many arguments the operator takes.
     */
    private final int OP_GROUP;

    public MSOpNode(final int opType, final int opGroup, final MSSyntaxTree... children) {
        super(MSNodeType.OP, children);
        this.OP_TYPE = opType;
        this.OP_GROUP = opGroup;
    }

    private MSOpNode(final int opType, final int opGroup) {
        super(MSNodeType.OP);
        this.OP_TYPE = opType;
        this.OP_GROUP = opGroup;
    }

    @Override
    public MSSyntaxTree copy() {
        MSOpNode exp = new MSOpNode(this.OP_TYPE, this.OP_GROUP);
        for (MSSyntaxTree ch : this.getChildren()) {
            exp.addChild(ch.copy());
        }
        return exp;
    }

    @Override
    public String getStringRep() {
        return this.getOpTypeString(this.OP_TYPE);
    }

    @Override
    public String toString() {
        return "OP " + getOpTypeString(this.OP_TYPE);
    }

    public int getOpType() {
        return this.OP_TYPE;
    }

    public boolean isUnary() {
        return this.OP_GROUP == MiniSchemeParser.RULE_unaryop;
    }

    public boolean isBinary() {
        return this.OP_GROUP == MiniSchemeParser.RULE_binaryop;
    }

    public boolean isTernary() {
        return this.OP_GROUP == MiniSchemeParser.RULE_ternaryop;
    }

    /**
     * Returns the literal string representation of an operation. In the lexer, the operation
     * has a string representation and this is used to determine which operation is used.
     *
     * @param opType - token type from the parser.
     * @return string representation of op.
     */
    private String getOpTypeString(int opType) {
        String literalName = MiniSchemeParser.VOCABULARY.getLiteralName(opType);
        return literalName.substring(1, literalName.length() - 1);
    }
}
