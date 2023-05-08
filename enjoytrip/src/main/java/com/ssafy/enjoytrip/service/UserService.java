package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.SignInDto;
import com.ssafy.enjoytrip.dto.SignUpDto;
import com.ssafy.enjoytrip.model.User;

import java.util.List;

public interface UserService {
//    public List<User> getAllUser();
//    public MemberDto getMemberInfoById(Long id);
    public MemberDto getMemberInfoByEmail(String email);
    public MemberDto login(SignInDto signInDto);

    public void signUp(SignUpDto signUpDto);
//    public void updateInfo(MemberDto memberDto);
//    public void updatePassword();
}
