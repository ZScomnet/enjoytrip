package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlanDetailDto {

    private String plan_name;
    private List<List<AttractionDto>> contendIdList;

}
