package ru.javarush.cryptoanaliser.rakhimov.util;

import java.io.File;

public class PathFinder {

    public PathFinder() {
    }

    public static String getRoot() {
        String root = System.getProperty("user.dir");
        return root + File.separator + "text" + File.separator;
    }
}
