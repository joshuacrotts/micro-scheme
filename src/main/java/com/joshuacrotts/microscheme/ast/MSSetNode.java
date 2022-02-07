/******************************************************************************
 *  File: MSSetNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 02/06/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

import com.joshuacrotts.microscheme.MicroSchemeParser;
import com.joshuacrotts.microscheme.parser.MSInterpreterException;

import java.util.ArrayList;

public class MSSetNode extends MSSyntaxTree {

    public MSSetNode(final int type, final ArrayList<MSSyntaxTree> setData) {
        super(MSSetNode.getCorrespondingNodeType(type));
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
            case MicroSchemeParser.SET: return MSNodeType.SET;
            case MicroSchemeParser.SETCAR: return MSNodeType.SETCAR;
            case MicroSchemeParser.SETCDR: return MSNodeType.SETCDR;
            case MicroSchemeParser.SETVECTOR: return MSNodeType.SETVECTOR;
            default:
                throw new MSInterpreterException("Cannot get node type from parser type " + parserType);
        }
    }
}
