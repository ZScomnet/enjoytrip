package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.User;
import com.ssafy.enjoytrip.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
}
