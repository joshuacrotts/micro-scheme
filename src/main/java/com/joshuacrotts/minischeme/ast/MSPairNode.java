package com.joshuacrotts.minischeme.ast;

public class MSPairNode extends MSSyntaxTree {

    /**
     *
     */
    private MSSyntaxTree car;

    /**
     *
     */
    private MSSyntaxTree cdr;

    public MSPairNode(MSSyntaxTree car, MSSyntaxTree cdr) {
        super(MSNodeType.MS_PAIR, car, cdr);
    }
}
