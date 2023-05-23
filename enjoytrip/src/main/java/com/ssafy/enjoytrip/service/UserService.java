package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.SignUpDto;
import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public MemberDto getMemberInfoById(Long id);
    public Long login(User user);

    public void signupUser(SignUpDto signUpDto);

//    public void signIn();
//    public void updateInfo(MemberDto memberDto);
//    public void updatePassword();
}
