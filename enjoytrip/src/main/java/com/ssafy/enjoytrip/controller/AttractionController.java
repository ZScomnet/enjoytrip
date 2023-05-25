package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.*;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.service.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attraction")
@CrossOrigin("*")
public class AttractionController {
    private final AttractionService attractionService;
    @GetMapping
    public List<AttractionInfo> getAllUser(){
        return attractionService.getAllAttraction();
    }
    @GetMapping("/{id}")
    public AttractionInfo getMemberById(@PathVariable Long id){
        return attractionService.getDetailAttraction(id);
    }

    @GetMapping("/sg/{sido}/{gugun}")
    public List<AttractionInfo> getSidoGugunAttraction(@PathVariable int sido,@PathVariable int gugun ){
        return attractionService.getSidoGugunAttraction(sido,gugun);
    }

    @GetMapping("/type/{type}")
    public List<AttractionInfo> getTypeAttraction(@PathVariable long type){
        return attractionService.getTypeAttraction(type);
    }

    @PutMapping("/plan/{plan_id}/like/{user_id}")
    public void updateLike(@PathVariable int plan_id,@PathVariable Long user_id){
//        attractionService.like(plan_id);
        System.out.println("userid : "+user_id);
        attractionService.insertlikes(plan_id,user_id);
    }

    @DeleteMapping("/plan/{plan_id}/deleteLike/{user_id}")
    public void deleteLike(@PathVariable int plan_id,@PathVariable Long user_id){
//        attractionService.like(plan_id);
        System.out.println("userid : "+user_id);
        attractionService.deletelikes(plan_id,user_id);
    }

    @GetMapping("/plan/{plan_id}/likeCnt")
    public Long LikeCnt(@PathVariable int plan_id){
        return attractionService.LikeCnt(plan_id);
    }

    //좋아요 여부 했는지 안했는지 여부에 따라 판단
    //있으면 1 없으면 0
    @GetMapping("/plan/{plan_id}/checkLike/{user_id}")
    public Long checkLike(@PathVariable int plan_id, @PathVariable Long user_id){
        Long num = attractionService.checkLike(user_id,plan_id);
        if(num!=0){
            return 1L;
        }else return 0L;

    }

    @GetMapping("/plan/{plan_id}/planRank")
    public String planRank(@PathVariable int plan_id){
         return  attractionService.planRank(plan_id);
    }

//    @PutMapping("/plan/insertPlan")
//    public void insertPlan(@RequestBody AttractionDto attractionDto){
//        Plan plan =attractionService.insertPlan(attractionDto.getPlan_name(),attractionDto.getUser_id());
//
//        System.out.println(plan.getPlan_id());
////        int planID = plan.getPlan_id();
//
//    }
    @PostMapping("/plan")
    public void insertDetailPlan(@RequestBody PlanDetailDto planDetailDto){

        attractionService.insertDetailPlan(planDetailDto);
        System.out.println("DetailPlan 완료");
//        int planID = plan.getPlan_id();
    }

    @PatchMapping("/plan/deletePlan")
    public void deletePlan(@RequestBody AttractionDto attractionDto){
        attractionService.deletePlan(attractionDto.getPlan_name(),attractionDto.getUser_id());
    }

    @GetMapping("/myplanList/{plan_id}")
    public PlanDetailDto selectPlanList(@PathVariable int plan_id){
        return attractionService.selectPlanList(plan_id);
    }

    @PostMapping("/myplanUpdateList/{plan_id}")
    public void updatePlanList(@PathVariable int plan_id, @RequestBody PlanDetailDto planDetailDto){
        attractionService.updatePlanList(plan_id, planDetailDto);
    }

    @GetMapping("/myplanLists/{username}")
    public List<MyPlanListsDto> myplanLists(@PathVariable String username){
        return attractionService.myplanLists(username);
    }

    @GetMapping("/allPlanLists")//like순 정렬
    public List<PlanRankingDto> getAllPlanLists(){
        return attractionService.getAllPlanLists();
    }

    @GetMapping("/allNewPlanLists")//최신순 정렬
    public List<MyPlanListsDto> getNewAllPlanLists(){
        return attractionService.getNewAllPlanLists();
    }

    @GetMapping("/LikeAttractionList/{page}")//like순 정렬
    public List<AttractionInfo> LikeAttractionList(@PathVariable int page){
        return attractionService.LikeAttractionList(page);
    }


    @GetMapping("/getDescription/{content_id}")//like순 정렬
    public String LikeAttractionList(@PathVariable Long content_id){
        return attractionService.getDescription(content_id);
    }
    @GetMapping("/ranking/attr")
    public List<AttractionRankingDto> getAttractionRanking() {
        return attractionService.getAttractionRanking();
    }

}
