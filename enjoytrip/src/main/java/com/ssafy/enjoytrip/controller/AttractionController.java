package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.User;
import com.ssafy.enjoytrip.service.AttractionService;
import com.ssafy.enjoytrip.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attraction")
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

}
