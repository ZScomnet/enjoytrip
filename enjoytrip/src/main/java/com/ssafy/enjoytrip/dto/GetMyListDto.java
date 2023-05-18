package com.ssafy.enjoytrip.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMyListDto {
//    private Long content_id; // primary key
//    private Long content_type_id; // foreign key
//    private String sido_code; // foreign key
//    private String gugun_code; // foreign key
//
//    private String title;
//    private String addr1;
//    private String addr2;
//    private String zipcode;
//    private String tel;
//    private String first_image;
//    private String first_image2;
//    private int readcount;
//    private String latitude;
//    private String longitude;
//    private String mlevel;

    private AttractionDto attractionDto;
//    PlanInfo.plan_id, PlanInfo.plan_day,PlanInfo.plan_day_idx
    private int plan_id;
    private int plan_day;
    private int plan_day_idx;
    private String plan_name;
}
