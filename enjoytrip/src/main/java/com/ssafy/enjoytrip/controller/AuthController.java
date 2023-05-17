package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.SignInDto;
import com.ssafy.enjoytrip.service.UserService;
//import com.ssafy.enjoytrip.util.JwtUtil;
import com.ssafy.enjoytrip.util.JwtUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInDto signInDto){
        // 사용자 인증 로직
        MemberDto user = userService.login(signInDto);
        if(user!=null){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.createToken(signInDto.getEmail());
//            String token = JwtUtil.generateToken(user.getUsername());
//            return ResponseEntity.ok(token);
//            session.setAttribute("username",user.getUsername());
            return ResponseEntity.ok(jwt);
        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @GetMapping("/logout")
    public void logout(){

    }
}
