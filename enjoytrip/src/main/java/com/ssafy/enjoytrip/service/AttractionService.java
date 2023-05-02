package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.User;

import java.util.List;

public interface AttractionService {
    public List<AttractionInfo> getAllAttraction();
    public AttractionInfo getDetailAttraction(long content_id);

    public List<AttractionInfo> getSidoGugunAttraction(int sido, int gugun);

    public List<AttractionInfo> getTypeAttraction(long type);


}