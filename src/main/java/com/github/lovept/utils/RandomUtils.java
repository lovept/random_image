package com.github.lovept.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Random;

public class RandomUtils {
    public static byte[] randomImage(String folder) {

        File resourceFile = new File(folder);
        File[] files = resourceFile.listFiles();

        Random random = new Random();
        if (files == null) {
            return null;
        }

        int index = random.nextInt(files.length);
        File file = files[index];
        try (InputStream is = Files.newInputStream(file.toPath())) {
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                throw new IOException("File is too large to be processed!");
            }
            byte[] bytes = new byte[(int) length];
            int read = is.read(bytes);
            if (read != length) {
                throw new IOException("Could not read the entire file: " + file.getName());
            }
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
