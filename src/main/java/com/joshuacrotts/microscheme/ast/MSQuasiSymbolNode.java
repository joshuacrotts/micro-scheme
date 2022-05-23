/******************************************************************************
 *  File: MSQuasiSymbolNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  Quasi quotes take the form `(...) with commas , to denote expressions to
 *  evaluate. For example, suppose a = 2 and b = 3 and we do `(,a . ,b).
 *  This results in the cons pair (2 . 3). We can use @ to evaluate every element
 *  in a list.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

import java.util.ArrayList;

public final class MSQuasiSymbolNode extends MSSyntaxTree {

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
