package com.ssafy.enjoytrip.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class AttractionRankingDto {
    private Long contentId;
    private Long count;
    private String imgURI;
    private String title;
    private String addr1;
}
