package com.github.lovept.service;

import cn.hutool.json.JSON;
import com.github.lovept.pojo.SuperBed;

public interface RandomImageService {

    JSON fetchSuperBed(SuperBed superBed);

    String randomUrl(JSON json);

}
