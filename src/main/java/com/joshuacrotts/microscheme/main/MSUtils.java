/******************************************************************************
 *  File: MSUtils.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  MSUtils is a list of static functions for other files to use, e.g.,
 *  random numbers and repeating strings n times (Java 8 does not have a built-in
 *  function).
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.main;

import java.util.concurrent.ThreadLocalRandom;

public final class MSUtils {

    /**
     * Returns a random integer between min and max.
     *
     * @param min
     * @param max
     * @return random integer
     */
    public static int randomInt(final int min, final int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be smaller than min.");
        }

        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Returns a random integer between 0 and max.
     *
     * @param max
     * @return random integer
     */
    public static int randomInt(final int max) {
        return randomInt(0, max);
    }

    /**
     * @param min
     * @param max
     * @return
     */
    public static double randomDouble(final double min, final double max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be smaller than min.");
        }

        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    /**
     * @param _n
     * @param _str
     * @return
     */
    public static String repeatString(final int _n, final String _str) {
        StringBuilder b = new StringBuilder(_n * _str.length());
        for (int i = 0; i < _n; ++i) {
            b.append(_str);
        }
        return b.toString();
    }
}