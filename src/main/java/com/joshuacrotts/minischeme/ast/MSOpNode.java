package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.MiniSchemeParser;

/**
 * Defines an operation expression. An operation expression can be either unary or n-ary, where unary
 * takes one argument and n-ary takes n.
 *
 * CHILD 0...n-1: expressions to evaluate with the operator.
 *
 * @author Joshua Crotts
 * @version 12/20/2021
 */
public class MSOpNode extends MSSyntaxTree {

    /**
     * Operation type. This should be a token type from the parser.
     */
    private final int opType;

    /**
     *
     */
    private final int opGroup;

    public MSOpNode(int opType, int opGroup, MSSyntaxTree... children) {
        super(MSNodeType.OP, children);
        this.opType = opType;
        this.opGroup = opGroup;
    }

    private MSOpNode(int opType, int opGroup) {
        super(MSNodeType.OP);
        this.opType = opType;
        this.opGroup = opGroup;
    }

    @Override
    public MSSyntaxTree copy() {
        MSOpNode exp = new MSOpNode(this.opType, this.opGroup);
        for (MSSyntaxTree ch : this.getChildren()) {
            exp.addChild(ch.copy());
        }
        return exp;
    }

    @Override
    public String getStringRep() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(").append(getOpTypeString(this.opType)).append(" ");
        for (int i = 0; i < this.getChildrenSize() - 1; i++) {
            stringBuilder.append(this.getChild(i).getStringRep()).append(" ");
        }
        if (this.getChildrenSize() > 0) {
            stringBuilder.append(this.getChild(this.getChildrenSize() - 1).getStringRep());
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "OP " + getOpTypeString(this.opType);
    }

    public int getOpType() {
        return this.opType;
    }

    public boolean isUnary() { return this.opGroup == MiniSchemeParser.RULE_unaryop; }

    public boolean isBinary() { return this.opGroup == MiniSchemeParser.RULE_binaryop; }

    public boolean isTernary() { return this.opGroup == MiniSchemeParser.RULE_ternaryop; }

    /**
     * Returns the literal string representation of an operation. In the lexer, the operation
     * has a string representation and this is used to determine which operation is used.
     *
     * @param opType - token type from the parser.
     *
     * @return string representation of op.
     */
    private String getOpTypeString(int opType) {
        String literalName = MiniSchemeParser.VOCABULARY.getLiteralName(opType);
        return literalName.substring(1, literalName.length() - 1);
    }
}
