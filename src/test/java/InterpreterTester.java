import com.joshuacrotts.minischeme.ast.MSSyntaxTree;
import com.joshuacrotts.minischeme.main.MiniSchemeInterpreter;
import com.joshuacrotts.minischeme.main.MiniSchemeTester;
import com.joshuacrotts.minischeme.parser.MSListener;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.opentest4j.AssertionFailedError;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class InterpreterTester {

    private static final int NUM_TESTS = 165;

    /**
     * Helper function to count number of newlines in a string
     *
     * @param s the string
     * @return the number of newlines
     */
    private static int countNLs(String s) {
        if (s == null) { return 0; }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\n') { count++; }
        }
        return count;
    }

    /**
     * Compares to byte array token by token, where a "token" is either an
     * identifier, a number, or an individual character. All whitespace
     * is skipped over and not used for the comparison, so the outputs can
     * be formatted/spaced entirely differently.
     *
     * @param got    the bytes printed out by the program under test
     * @param expect the expected output
     */
    private static void compare(byte[] got, byte[] expect) {
        String result = null;
        Scanner gotScanner = new Scanner(new ByteArrayInputStream(got));
        Scanner expScanner = new Scanner(new ByteArrayInputStream(expect));
        expScanner.useDelimiter("\\n");
        int gotLine = 1;
        int expLine = 1;

        Pattern tokPattern = Pattern.compile("([A-Za-z_][A-Za-z_0-9]*)|([0-9]+)|(.)");
        Pattern skipPattern = Pattern.compile("[ \\r\\t\\n]*");
        Pattern nlPattern = Pattern.compile("\\n");

        boolean done = false;
        while (!done) {
            String skipped = expScanner.findWithinHorizon(skipPattern, 1000);
            expLine += countNLs(skipped);
            String expToken = expScanner.findWithinHorizon(tokPattern, 1000);

            skipped = gotScanner.findWithinHorizon(skipPattern, 1000);
            gotLine += countNLs(skipped);
            String gotToken = gotScanner.findWithinHorizon(tokPattern, 1000);
            if (expToken != null) {
                if (gotToken != null) {
                    if (!expToken.equals(gotToken)) {
                        result = "Error. Got line " + gotLine + " has \"" + gotToken
                                + "\"; expected line " + expLine + " is \"" + expToken + "\"";
                        done = true;
                    }
                } else {
                    result = "Produced output ended too early - expected \""
                            + expToken + "\" (line " + expLine + ")";
                    done = true;
                }
            } else {
                if (gotToken != null) {
                    result = "Got extra output: unexpected \"" + gotToken
                            + "\" (line " + gotLine + ")";
                }
                done = true;
            }
        }

        assertNull(result, result);
    }


    /**
     * The testing engine for a valid LittleC program (which should parse and
     * produce a LCSyntaxTree). Both the input LittleC program and the expected
     * syntax tree output file must be provided as files with ".in" and ".out"
     * extensions, respectively. Runs input file through the
     * ParserTest.parseFromFile() method, gets the syntax tree and calls the
     * user-written printSyntaxTree() method to get a text representation,
     * which is matched token-by-token with the expected output.
     *
     * @param testName the base name of the test case; files are stored in the
     *                 tests project directory, with ".in" and ".out"
     *                 extensions.
     */
    private static void goodFileTest(String testName) {
        String inName = "tests/" + testName + ".in";
        String expName = "tests/" + testName + ".out";

        PrintStream origOut = System.out;
        PrintStream origErr = System.err;
        ByteArrayOutputStream captureOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captureOut));
        System.setErr(new PrintStream(captureOut));
        MSListener parser = MiniSchemeTester.parseFromFile(inName);
        if (parser == null) { throw new AssertionFailedError("Failed reading test input file " + inName); }
        MSSyntaxTree syntaxTree = parser.getSyntaxTree();
        MiniSchemeInterpreter interpreter = new MiniSchemeInterpreter(syntaxTree);
        interpreter.execute();
        System.setErr(origErr);
        System.setOut(origOut);
        byte[] actual = captureOut.toByteArray();

        byte[] expected;
        try {
            expected = Files.readAllBytes(Paths.get(expName));
        } catch (IOException e) {
            throw new AssertionFailedError("Missing expected output file " + expName);
        }
        //compare(actual, expected);
        assertTimeoutPreemptively(Duration.ofSeconds(2), () ->
                compare(actual, expected));
        InterpreterTester.cleanup();
    }

    /**
     * Cleanup function. I originally used this with the @AfterEach tag, but because
     * there are group tests that rely on a cleanup, I had to force this into a
     * function and call it before the assertion in runICTest(...).
     * <p>
     * Removes the errors.
     */
    private static void cleanup() {
    }

    private static Stream<String> fileNameSource() {
        return IntStream.range(1, NUM_TESTS + 1).mapToObj(i -> String.format("test%03d", i));
    }

    @ParameterizedTest
    @MethodSource("fileNameSource")
    public void test(final String fileName) {
        goodFileTest(fileName);
    }
}
