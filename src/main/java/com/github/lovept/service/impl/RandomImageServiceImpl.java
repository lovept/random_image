package com.github.lovept.service.impl;

import cn.hutool.json.JSON;
import com.github.lovept.pojo.SuperBed;
import com.github.lovept.service.RandomImageService;
import com.github.lovept.utils.SuperBedUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
public class RandomImageServiceImpl implements RandomImageService {
    @Override
    public JSON fetchSuperBed(SuperBed superBed) {
        try {
            return SuperBedUtils.getImages(superBed);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String randomUrl(JSON json) {
        String urlStr = json.getByPath("docs.url").toString();
        String[] urls = urlStr.substring(urlStr.indexOf('[') + 1, urlStr.lastIndexOf(']')).split(", ");

        Random random = new Random();
        int index = random.nextInt(urls.length);
        return urls[index];
    }


}
