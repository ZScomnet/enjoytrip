package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.model.PlanInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface AttractionRepository {
    public List<AttractionInfo> getAllAttraction();

    public AttractionInfo getDetailAttraction(long content_id);

    public List<AttractionInfo> getSidoGugunAttraction(int sido, int gugun);

    public List<AttractionInfo> getTypeAttraction(long type);

//    public void insertPlan(long type);
//
//    public void insertPlanInfo(long type);

    public void like(int plan_id);

    public void insertlikes(int plan_id, Long user_id);

    public Plan insertPlan(String plan_name, Long user_id);

    public void insertDetailPlan(int plan_id, String plan_date, List<Integer> contentIdList);


}