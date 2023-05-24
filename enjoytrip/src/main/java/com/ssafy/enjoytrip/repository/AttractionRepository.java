package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MyPlanListsDto;
import com.ssafy.enjoytrip.dto.PlanDetailDto;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.model.PlanInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

//    public void like(int plan_id);

    public void insertlikes(int plan_id, Long user_id);

    public void deletelikes(int plan_id, Long user_id);

    public Long LikeCnt(int plan_id);

    public Long checkLike(Long user_id, int plan_id);



//    public Plan insertPlan(String plan_name, Long user_id);

    public void insertDetailPlan(PlanDetailDto planDetailDto);

    public void deletePlan(String plan_name, Long user_id);

    public PlanDetailDto selectPlanList(int plan_id);

    public void updatePlanList(int plan_id, PlanDetailDto planDetailDto);


    public List<MyPlanListsDto> myplanLists(String usename);

    public List<MyPlanListsDto> getAllPlanLists();

    public List<MyPlanListsDto> getNewAllPlanLists();



    public List<AttractionInfo> LikeAttractionList(int page);







}