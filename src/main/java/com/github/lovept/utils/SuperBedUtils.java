package com.github.lovept.utils;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.github.lovept.constent.RandomImageConstant;
import com.github.lovept.pojo.SuperBed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SuperBedUtils {

    public static JSON getImages(SuperBed superBed) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(RandomImageConstant.SUPER_BED_API);
        urlBuilder.append("?")
                .append("token=").append(URLEncoder.encode(superBed.getToken(), StandardCharsets.UTF_8))
                .append("&f=").append(URLEncoder.encode(superBed.getFormat(), StandardCharsets.UTF_8));

        String categories = superBed.getCategories();
        if (categories != null && !categories.isEmpty()) {
            urlBuilder.append("&categories=").append(URLEncoder.encode(categories, StandardCharsets.UTF_8));
        }

        String startDate = superBed.getStartDate();
        if (startDate != null && !startDate.isEmpty()) {
            urlBuilder.append("&startdate=").append(URLEncoder.encode(startDate, StandardCharsets.UTF_8));
        }

        String endDate = superBed.getEndDate();
        if (endDate != null && !endDate.isEmpty()) {
            urlBuilder.append("&enddate=").append(URLEncoder.encode(endDate, StandardCharsets.UTF_8));
        }

        String page = superBed.getPage() == null ? "1" : superBed.getPage();
        String size = superBed.getSize() == null ? "50" : superBed.getSize();
        urlBuilder.append("&page=").append(page).append("&size=").append(size);

        HttpURLConnection conn = HttpUtils.getHttpURLConnection(urlBuilder.toString());
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return JSONUtil.parse(response);
        } else {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }
    }
}
