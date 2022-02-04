/******************************************************************************
 *  File: MicroSchemeRunner.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.main;

import com.joshuacrotts.microscheme.MicroSchemeLexer;
import com.joshuacrotts.microscheme.MicroSchemeParser;
import com.joshuacrotts.microscheme.ast.MSSyntaxTree;
import com.joshuacrotts.microscheme.parser.MSListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.NoSuchFileException;

public class MicroSchemeRunner {

    /**
     * Command line interface -- one argument is filename, and if omitted then input is taken from
     * standard input.
     *
     * @param argv command line arguments
     */
    public static void main(final String[] argv) throws IOException {
        if (argv.length > 2) {
            System.err.println("Can provide at most two command line arguments (an input filename and mode)");
            return;
        }

        MicroSchemeInterpreter interpreter = new MicroSchemeInterpreter();
        if (argv.length == 1 && !argv[0].equals("-i")) {
            interpretParser(interpreter, parseFromFile(argv[0]));
        } else if (argv.length == 1) {
            interpretParser(interpreter, parseStream(CharStreams.fromStream(System.in)));
        } else {
            System.out.println("MicroScheme 0.0.1");
            System.out.println("Type \"help\" for more information on commands.");
            while (true) {
                interpretParser(interpreter, parseFromStdin());
            }
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        StringBuilder out = new StringBuilder();
        System.out.print("> ");
        try {
            // Read input until we encounter either a newline or backslash then \n
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("\\")) {
                    out.append(line, 0, line.length() - 1);
                    System.out.print("... ");
                } else {
                    out.append(line);
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return out.length() != 0 ? parseStream(CharStreams.fromString(out.toString())) : null;
    }

    private static MSListener parseStream(final CharStream input) {
        // "input" is the character-by-character input - connect to lexer
        MicroSchemeLexer lexer = new MicroSchemeLexer(input);

        // Connect token stream to lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Connect parser to token stream
        MicroSchemeParser parser = new MicroSchemeParser(tokens);
        ParseTree tree = parser.microScheme();

        // Now do the parsing, and walk the parse tree with our listeners
        ParseTreeWalker walker = new ParseTreeWalker();
        MSListener compiler = new MSListener();
        walker.walk(compiler, tree);

        return compiler;
    }

    /**
     * @param parser
     */
    private static void interpretParser(final MicroSchemeInterpreter interpreter, final MSListener parser) {
        if (parser == null) { return; }
        MSSyntaxTree tree = parser.getSyntaxTree();
        if (tree == null) { System.exit(1); }
        interpreter.setInterpreterTree(tree);
        interpreter.execute();
    }
}