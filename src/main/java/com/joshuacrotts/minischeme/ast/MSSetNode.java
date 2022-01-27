/******************************************************************************
 *  File: MSSetNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.minischeme.ast;

import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.parser.MSInterpreterException;

import java.util.ArrayList;

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
