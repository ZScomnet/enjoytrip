//package com.ssafy.enjoytrip.controller;
//
//import com.ssafy.enjoytrip.dto.MemberDto;
//import com.ssafy.enjoytrip.dto.SignInDto;
//import com.ssafy.enjoytrip.service.UserService;
////import com.ssafy.enjoytrip.util.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/auth")
//public class AuthController {
//    private final UserService userService;
//
//    @PostMapping("/login")
//    public ResponseEntity<MemberDto> login(@RequestBody SignInDto signInDto){
//        // 사용자 인증 로직
//        MemberDto user = userService.login(signInDto);
//        if(user!=null){
////            String token = JwtUtil.generateToken(user.getUsername());
////            return ResponseEntity.ok(token);
//            return ResponseEntity.ok(user);
//        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//
//}
