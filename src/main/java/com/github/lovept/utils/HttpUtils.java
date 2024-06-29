package com.github.lovept.utils;

import java.io.IOException;
import java.net.*;

public class HttpUtils {

    public static HttpURLConnection getHttpURLConnection(String api) {
        URL url;
        HttpURLConnection conn;
        try {
            url = URI.create(api).toURL();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
