package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.BoardGroup;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.repository.AttractionRepository;
import com.ssafy.enjoytrip.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    public void insertBoard(Board board){
         boardRepository.insertBoard(board);
    }

    public List<BoardGroup> getBoardGroup(){ return boardRepository.getBoardGroup(); }

    public List<Board> getCategoryBoard(int groupId){ return  boardRepository.getCategoryBoard(groupId);}

    public void deleteBoard(int BoardId){boardRepository.deleteBoard(BoardId);}

    public void updateBoard(Board board){ boardRepository.updateBoard(board);}


    public List<Board> getAllBoardList() { return boardRepository.getAllBoardList();}

    public Board getBoardDetail(int boardId){return  boardRepository.getBoardDetail(boardId);}


}