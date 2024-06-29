package com.github.lovept.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuperBed {
    String token;
    String format = "json";
    String categories;
    String startDate;
    String endDate;
    String page;
    String size;
}
