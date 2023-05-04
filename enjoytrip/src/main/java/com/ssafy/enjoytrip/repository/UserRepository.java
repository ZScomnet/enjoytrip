package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.SignInDto;
import com.ssafy.enjoytrip.dto.SignUpDto;
import com.ssafy.enjoytrip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> getMemberInfoByEmail(String email);
    public MemberDto login(SignInDto signInDto);

    public void signUp(SignUpDto signUpDto);
//    public void updateInfo(MemberDto memberDto);
//    public void updatePassword();

}
