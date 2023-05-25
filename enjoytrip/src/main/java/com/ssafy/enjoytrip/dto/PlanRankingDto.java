package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class PlanRankingDto {
    private Long plan_id;
    private String plan_name;
    private String thumbnail;
    private String username;
    private Long user_id;
    private int count;
    private String profileimg;
}
