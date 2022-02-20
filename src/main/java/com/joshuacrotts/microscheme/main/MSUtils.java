/******************************************************************************
 *  File: MSUtils.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 01/25/2022
 *
 *
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.main;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class MSUtils {

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
     * Determines if there is a connection to the internet (as the name implies!). Sends a
     * connection request to google.com (which we assume is always online :D) and if it finds the
     * connection, we return true and false otherwise.
     *
     * @return
     */
    public static boolean connectedToNet() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @param sets
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> Set<T> union(final Collection<T>... sets) {
        Set<T> distinct = new HashSet<T>();
        for (Collection<T> list : sets) {
            distinct.addAll(list);
        }
        return distinct;
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

    /**
     * @param _s1
     * @param _s2
     * @return
     */
    public static int sbCompareTo(final StringBuilder _s1, final StringBuilder _s2) {
        return _s1.toString().compareTo(_s2.toString());
    }

    public static Color extractStringHexColor(String hex) {
        if (!hex.startsWith("#")) {
            throw new IllegalArgumentException("Hex string must start with #");
        } else if (hex.length() != 7 && hex.length() != 9) {
            throw new IllegalArgumentException("Hex string must be 24 or 32bit");
        } else {
            return new Color(Integer.parseInt(hex.substring(1), 16));
        }
    }
}