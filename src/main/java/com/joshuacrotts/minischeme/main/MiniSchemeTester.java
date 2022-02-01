package com.joshuacrotts.minischeme.main;

import ch.obermuhlner.math.big.BigComplex;
import com.joshuacrotts.minischeme.ast.MSNumberNode;

public class MiniSchemeTester {

    public static void main(String[] args) {
        BigComplex c1 = BigComplex.valueOf(5432);
        BigComplex c2 = c1.add(32);
        BigComplex c3 = c1.add(BigComplex.valueOf(3, -4));

        System.out.println(MSNumberNode.extractComplexFromString("5+4i"));
        System.out.println(MSNumberNode.extractComplexFromString("5-4i"));
        System.out.println(MSNumberNode.extractComplexFromString("-5+4i"));
        System.out.println(MSNumberNode.extractComplexFromString("-5-4i"));
    }
}
