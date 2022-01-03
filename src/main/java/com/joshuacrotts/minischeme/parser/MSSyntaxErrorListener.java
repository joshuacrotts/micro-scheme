package com.joshuacrotts.minischeme.parser;

import org.antlr.v4.runtime.*;

public class MSSyntaxErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg,
                            RecognitionException e) {
        System.out.println("line " + line + ":" + charPositionInLine + " " + msg);
        underlineError(recognizer, (Token) offendingSymbol,
                line, charPositionInLine);
    }

    private void underlineError(Recognizer recognizer, Token offendingToken,
                                int line, int charPositionInLine) {
        CommonTokenStream tokens = (CommonTokenStream) recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");
        String errorLine = lines[line - 1];
        System.out.printf("%s\n", errorLine);
        for (int i = 0; i < charPositionInLine; i++) { System.out.print(" "); }
        int start = offendingToken.getStartIndex();
        int stop = offendingToken.getStopIndex();
        for (int i = start; i <= stop; i++) { System.out.print("^"); }
        System.out.println();
    }
}
