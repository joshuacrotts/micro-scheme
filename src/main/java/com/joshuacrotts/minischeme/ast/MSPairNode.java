package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public class MSPairNode extends MSSyntaxTree {

    /**
     *
     */
    private MSSyntaxTree car;

    /**
     *
     */
    private ArrayList<MSSyntaxTree> cdr;

    public MSPairNode(MSSyntaxTree car, ArrayList<MSSyntaxTree> cdr) {
        super(MSNodeType.MS_PAIR);
        throw new UnsupportedOperationException("Unsupported");
    }
}
