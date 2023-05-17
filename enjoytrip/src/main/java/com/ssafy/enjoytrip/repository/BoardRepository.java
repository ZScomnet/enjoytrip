package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.BoardGroup;
import com.ssafy.enjoytrip.model.Plan;

import java.util.List;

public interface BoardRepository {
    public void insertBoard(Board board);

    public void insertBoardPermission(BoardGroup boardGroup);



}