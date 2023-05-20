package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.AttractionDto;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.BoardGroup;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.service.AttractionService;
import com.ssafy.enjoytrip.service.BoardService;
import com.ssafy.enjoytrip.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/insertBoard")
    public void insertBoard(@RequestBody Board board){
        //board table 추가
        boardService.insertBoard(board);

    }

    @GetMapping("/getBoardGroup")
    public List<BoardGroup> getBoardGroup(){
        //board table 추가
        return boardService.getBoardGroup();
    }

    @GetMapping("/getCategoryBoard/{groupId}")
    public List<Board> getCategoryBoard(@PathVariable int groupId){
        //board table 추가
        return boardService.getCategoryBoard(groupId);
    }

    @PatchMapping("/deleteBoard/{boardId}")
    public void deleteBoard(@PathVariable int boardId){
        boardService.deleteBoard(boardId);
    }
    @PutMapping("/updateBoard")
    public void updateBoard(@RequestBody Board board){
        boardService.updateBoard(board);
    }


    }
