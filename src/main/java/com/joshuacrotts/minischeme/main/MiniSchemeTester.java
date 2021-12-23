package com.joshuacrotts.minischeme.main;

import com.joshuacrotts.minischeme.MiniSchemeLexer;
import com.joshuacrotts.minischeme.MiniSchemeParser;
import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.parser.MSListener;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class MiniSchemeTester {

    /**
     * Command line interface -- one argument is filename, and if omitted then input is taken from
     * standard input.
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
            assert parser != null;
            MSSyntaxTree tree = parser.getSyntaxTree();
            if (tree == null) {
                System.exit(1);
            }

            MiniSchemeInterpreter interpreter = new MiniSchemeInterpreter(tree);
            interpreter.execute();
        }
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

    private static MSListener parseStream(CharStream input) {
        // "input" is the character-by-character input - connect to lexer
        MiniSchemeLexer lexer = new MiniSchemeLexer(input);

        // Connect token stream to lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Connect parser to token stream
        MiniSchemeParser parser = new MiniSchemeParser(tokens);
        ParseTree tree = parser.miniScheme();

        // Now do the parsing, and walk the parse tree with our listeners
        ParseTreeWalker walker = new ParseTreeWalker();
        MSListener compiler = new MSListener(parser);
        walker.walk(compiler, tree);

        return compiler;
    }
}