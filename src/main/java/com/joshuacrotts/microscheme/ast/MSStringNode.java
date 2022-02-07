/******************************************************************************
 *  File: MSStringNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 02/06/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

public class MSStringNode extends MSSyntaxTree {

    /**
     * String associated with this node.
     */
    private final String VALUE;

    public MSStringNode(final String value) {
        super(MSNodeType.STRING);
        this.VALUE = this.unescapeString(value);
    }

    @Override
    public String getStringRep() {
        if (this.VALUE.startsWith("\"")
                && this.VALUE.endsWith("\"")
                && this.VALUE.length() >= 2) {
            return this.VALUE.substring(1, this.VALUE.length() - 1);
        }
        return this.VALUE;
    }

    @Override
    public String toString() {
        return "(STRING " + this.VALUE + ")";
    }

    public String getValue() {
        return this.VALUE;
    }

    public int length() {
        return this.getStringRep().length();
    }

    private String unescapeString(final String str) {
        StringBuilder sb = new StringBuilder("\"");
        int j = str.endsWith("\"") ? str.length() - 1 : str.length();
        for (int i = str.startsWith("\"") ? 1 : 0; i < j; i++) {
            char ch = str.charAt(i);
            if (ch == '\\') {
                // Get next char and compare it.
                i++;
                switch (str.charAt(i)) {
                    case 'n': sb.append('\n');
                              break;
                    case 't': sb.append('\t');
                              break;
                    case 'b': sb.append('\b');
                              break;
                    case 'r': sb.append('\r');
                              break;
                    case 'f': sb.append('\f');
                              break;
                    case '\'': sb.append('\'');
                               break;
                    case '\"': sb.append('\"');
                               break;
                    case '\\': sb.append('\\');
                               break;
                    default:
                        throw new IllegalArgumentException("Unknown escape character " + str.charAt(i));
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.append("\"").toString();
    }
}
