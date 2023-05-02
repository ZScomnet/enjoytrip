package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.AttractionInfo;

import java.util.List;

public interface AttractionRepository {
    public List<AttractionInfo> getAllAttraction();

    public AttractionInfo getDetailAttraction(long content_id);

    public List<AttractionInfo> getSidoGugunAttraction(int sido, int gugun);

    public List<AttractionInfo> getTypeAttraction(long type);


}