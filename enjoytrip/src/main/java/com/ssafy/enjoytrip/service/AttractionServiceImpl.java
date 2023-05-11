package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.User;
import com.ssafy.enjoytrip.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{
    private final AttractionRepository attractionRepository;
    public List<AttractionInfo> getAllAttraction(){
        return attractionRepository.getAllAttraction();
    }

    public AttractionInfo getDetailAttraction(long content_id){
        return attractionRepository.getDetailAttraction(content_id);
    }

    public List<AttractionInfo> getSidoGugunAttraction(int sido, int gugun){
        return attractionRepository.getSidoGugunAttraction(sido,gugun);
    }

    public List<AttractionInfo> getTypeAttraction(long type){
        return attractionRepository.getTypeAttraction(type);
    }

    public void like(int plan_id){
        attractionRepository.like(plan_id);
    }
    public void insertlikes(int plan_id, Long user_id){
        attractionRepository.insertlikes(plan_id,user_id);
    }
}