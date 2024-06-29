package com.github.lovept.controller;

import com.github.lovept.pojo.SuperBed;
import com.github.lovept.service.RandomImageService;
import com.github.lovept.utils.PathUtils;
import com.github.lovept.utils.RandomUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class RandomImageController {
    @Resource
    private RandomImageService service;

    @GetMapping("/random")
    public void getRandomImages(HttpServletResponse response) {
        SuperBed superBed = new SuperBed();
        superBed.setToken("you_key");
        superBed.setFormat("json");
        superBed.setCategories("wallpaper");
        superBed.setStartDate("2023-04-28");
        superBed.setEndDate("2023-05-02");
        superBed.setPage("1");
        superBed.setSize("50");
        String url = service.randomUrl(service.fetchSuperBed(superBed));
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/zrn", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] randomZRN() {

        String resourcePath = PathUtils.getResourcePath("images/zrn");
        return RandomUtils.randomImage(resourcePath);
    }

    @RequestMapping(value = "/ym", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] randomYM() {

        String resourcePath = PathUtils.getResourcePath("images/ym");
        return RandomUtils.randomImage(resourcePath);
    }
}
