package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.AttractionDto;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Board;
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
@CrossOrigin("http://localhost:5000/")
public class BoardController {
    private final BoardService boardService;

    @PutMapping("/insertBoard")
    public void insertBoard(@RequestBody Board board){
        //board table 추가
        boardService.insertBoard(board);

        //boardGroup 추가(권한 설정)
        boardService.

//        System.out.println(board.getText());
//        int planID = plan.getPlan_id();

    }

}
