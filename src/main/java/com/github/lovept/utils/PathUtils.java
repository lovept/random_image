package com.github.lovept.utils;

import com.sun.tools.javac.Main;

import java.net.URL;

public class PathUtils {

    public static String getResourcePath(String folder) {
        String path = "";
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(folder);
        if (resource != null) {
             path = resource.getPath();
        }
        return path;
    }
}
