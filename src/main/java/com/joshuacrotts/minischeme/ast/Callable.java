package com.joshuacrotts.minischeme.ast;

import java.util.ArrayList;

public interface Callable {

    ArrayList<MSSyntaxTree> getParameters();

    MSSyntaxTree getBody();

    int getArgumentIndex(final String idx);
}
