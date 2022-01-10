package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.MiniSchemeParser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A "set" operation refers to the act of changing a variable that is already
 * defined in the environment. We can change variables e.g., x, y, z, and the
 * car/cdr of pairs.
 *
 * @author Joshua Crotts
 * @version 12/31/2021
 */
public class MSSetNode extends MSSyntaxTree {

    /**
     * Set type operation.
     */
    private final int opType;

    public MSSetNode(final int opType, final MSSyntaxTree identifierNode, final ArrayList<MSSyntaxTree> setData) {
        super(MSNodeType.SET, identifierNode);
        this.opType = opType;
        setData.forEach(this::addChild);
    }

    public MSSetNode(final int opType, final MSSyntaxTree identifierNode, final MSSyntaxTree setData) {
        super(MSNodeType.SET, identifierNode);
        this.opType = opType;
        this.addChild(setData);
    }

    @Override
    public MSSyntaxTree copy() {
        MSSyntaxTree idCopy = this.getChild(0).copy();
        ArrayList<MSSyntaxTree> setDataCopy = new ArrayList<>();
        for (int i = 0; i < this.getChildrenSize(); i++) {
            setDataCopy.add(this.getChild(i + 1).copy());
        }

        return new MSSetNode(this.opType, idCopy, setDataCopy);
    }

    @Override
    public String getStringRep() {
        return this.getSetOpTypeString(this.opType);
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public int getOpType() {
        return this.opType;
    }

    public MSSyntaxTree getIdentifier() {
        return this.getChild(0);
    }

    public ArrayList<MSSyntaxTree> getData() {
        ArrayList<MSSyntaxTree> setData = new ArrayList<>();
        for (int i = 1; i < this.getChildrenSize(); i++) {
            setData.add(this.getChild(i));
        }
        return setData;
    }

    private String getSetOpTypeString(final int opType) {
        String literalName = MiniSchemeParser.VOCABULARY.getLiteralName(opType);
        return literalName.substring(1, literalName.length() - 1);
    }
}
