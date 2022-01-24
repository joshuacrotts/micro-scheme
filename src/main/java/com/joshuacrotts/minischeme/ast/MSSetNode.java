package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;

import java.util.ArrayList;

/**
 *
 * @author Joshua Crotts
 * @version 01/23/2022
 */
public class MSSetNode extends MSSyntaxTree {

    /**
     *
     */
    private final int TYPE;

    public MSSetNode(int type, ArrayList<MSSyntaxTree> setData) {
        super(MSSetNode.getCorrespondingNodeType(type));
        this.TYPE = type;
        for (MSSyntaxTree setElementData : setData) { this.addChild(setElementData); }
    }

    @Override
    public MSSyntaxTree copy() {
        ArrayList<MSSyntaxTree> setDataCopy = new ArrayList<>();
        for (int i = 0; i < this.getChildrenSize(); i++) { setDataCopy.add(this.getChild(i).copy()); }
        return new MSSetNode(this.TYPE, setDataCopy);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    private static MSNodeType getCorrespondingNodeType(int parserType) {
        switch (parserType) {
            case MiniSchemeParser.SET: return MSNodeType.SET;
            case MiniSchemeParser.SETCAR: return MSNodeType.SETCAR;
            case MiniSchemeParser.SETCDR: return MSNodeType.SETCDR;
            case MiniSchemeParser.SETVECTOR: return MSNodeType.SETVECTOR;
            default:
                throw new MSInterpreterException("Cannot get node type from parser type " + parserType);
        }
    }
}
