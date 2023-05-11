package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.User;
import com.ssafy.enjoytrip.service.AttractionService;
import com.ssafy.enjoytrip.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attraction")
@CrossOrigin("http://localhost:5000/")
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
        attractionService.like(plan_id);
        System.out.println("userid : "+user_id);
        attractionService.insertlikes(plan_id,user_id);
    }

}
