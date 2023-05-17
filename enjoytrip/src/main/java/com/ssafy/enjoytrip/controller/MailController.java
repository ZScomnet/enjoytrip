package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.AttractionDto;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.service.AttractionService;
import com.ssafy.enjoytrip.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mail")
@CrossOrigin("http://localhost:5000/")
public class MailController {
    private final MailService mailService;
    @PatchMapping("/send/{userEmail}")
    public void sendEmail(@PathVariable String userEmail){
       mailService.sendMail(userEmail);
       System.out.println(userEmail);
    }

}
