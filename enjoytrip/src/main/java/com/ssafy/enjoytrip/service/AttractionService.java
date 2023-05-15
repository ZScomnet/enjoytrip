package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface AttractionService {
    public List<AttractionInfo> getAllAttraction();
    public AttractionInfo getDetailAttraction(long content_id);

    public List<AttractionInfo> getSidoGugunAttraction(int sido, int gugun);

    public List<AttractionInfo> getTypeAttraction(long type);

    public void like(int plan_id);

    public void insertlikes(int plan_id, Long user_id);

    public Plan insertPlan(String plan_name, Long user_id);

    public void insertDetailPlan(int plan_id, String plan_date, List<Integer> contentIdList);



}