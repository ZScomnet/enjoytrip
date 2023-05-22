package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.AttractionDto;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.service.AttractionService;
import com.ssafy.enjoytrip.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mail")
@CrossOrigin("*")
public class MailController {
    private final MailService mailService;
    @PatchMapping("/send/{userEmail}")
    public  ResponseEntity<Void> sendEmail(@PathVariable String userEmail){
        int isUpdate= mailService.sendMail(userEmail);
       if(isUpdate==1){
           return new ResponseEntity<Void>(HttpStatus.OK);
       }
       else{
          return new ResponseEntity<Void> (HttpStatus.BAD_REQUEST);
       }
    }

}
