package com.joshuacrotts.minischeme.ast;

public interface Callable {

    MSSyntaxTree getBody();

    int getArgumentIndex(final String idx);
}
