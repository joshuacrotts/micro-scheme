package com.joshuacrotts.minischeme.main;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import com.joshuacrotts.minischeme.MiniSchemeLexer;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.parser.MSListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class MiniSchemeTester {

    private static MSListener parseStream(CharStream input) {
        // "input" is the character-by-character input - connect to lexer
        MiniSchemeLexer lexer = new MiniSchemeLexer(input);

        // Connect token stream to lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Connect parser to token stream
        MiniSchemeParser parser = new MiniSchemeParser(tokens);
        ParseTree tree = parser.minischeme();

        // Now do the parsing, and walk the parse tree with our listeners
        ParseTreeWalker walker = new ParseTreeWalker();
        MSListener compiler = new MSListener(parser);
        walker.walk(compiler, tree);

        return compiler;
    }

    /**
     * Public static method to run the parser on an input file.
     *
     * @param fileName the name of the file to use for input
     */
    public static MSListener parseFromFile(String fileName) {
        try {
            return parseStream(CharStreams.fromFileName(fileName));
        } catch (IOException e) {
            if (e instanceof NoSuchFileException) {
                System.err.println("Could not open file " + fileName);
            } else {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Public static method to run the parser on the standard input stream.
     */
    public static MSListener parseFromStdin() {
        try {
            return parseStream(CharStreams.fromStream(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Command line interface -- one argument is filename, and if omitted then input
     * is taken from standard input.
     *
     * @param argv command line arguments
     */
    public static void main(String[] argv) {
        MSListener parser;
        if (argv.length > 1) {
            System.err.println("Can provide at most one command line argument (an input filename)");
            return;
        } else if (argv.length == 1) {
            parser = parseFromFile(argv[0]);
        } else {
            parser = parseFromStdin();
            MSSyntaxTree tree = parser.getSyntaxTree();
            if (tree == null) { System.exit(1); }

//            System.out.println(tree.toString());

            MiniSchemeInterpreter interpreter = new MiniSchemeInterpreter(tree);
            for (MSSyntaxTree ch : tree.getChildren()) {
                System.out.println(interpreter.interpretTree(ch));
            }
        }
    }
}