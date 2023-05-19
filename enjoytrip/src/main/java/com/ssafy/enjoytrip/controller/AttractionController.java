package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.AttractionDto;
import com.ssafy.enjoytrip.dto.PlanDetailDto;
import com.ssafy.enjoytrip.dto.MyPlanListsDto;
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

    @PutMapping("/plan/{plan_id}")
    public void updateLike(@PathVariable int plan_id,@RequestBody Long user_id){
//        attractionService.like(plan_id);
        System.out.println("userid : "+user_id);
        attractionService.insertlikes(plan_id,user_id);
    }

    @DeleteMapping("/plan/{plan_id}/deleteLike")
    public void deleteLike(@PathVariable int plan_id,@RequestBody Long user_id){
//        attractionService.like(plan_id);
        System.out.println("userid : "+user_id);
        attractionService.deletelikes(plan_id,user_id);
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


}
