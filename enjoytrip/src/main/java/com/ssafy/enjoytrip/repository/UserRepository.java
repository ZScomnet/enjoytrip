package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.SignUpDto;
import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.model.User;

import java.util.List;

public interface UserRepository {
    public List<User> getAllUser();
    public MemberDto getMemberInfoById(Long id);

    public Long login(User user);

//    public void signIn();
//    public void updateInfo(MemberDto memberDto);
//    public void updatePassword();

    public void signupUser(SignUpDto signUpDto);

}
