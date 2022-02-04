/******************************************************************************
 *  File: MSQuasiSymbolNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

import java.util.ArrayList;

public class MSQuasiSymbolNode extends MSSyntaxTree {

    public MSQuasiSymbolNode(final ArrayList<MSSyntaxTree> symbolList) {
        super(MSNodeType.QUASISYMBOL);
        symbolList.forEach(this::addChild);
    }

    @Override
    public String getStringRep() {
        return this.getStringNodeType();
    }

    public ArrayList<MSSyntaxTree> getSymbolList() {
        return new ArrayList<>(this.getChildren());
    }
}
