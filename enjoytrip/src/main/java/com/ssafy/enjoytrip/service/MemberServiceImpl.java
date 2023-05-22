package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.model.User;
import com.ssafy.enjoytrip.repository.MemberRepository;
import com.ssafy.enjoytrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public String getUserName(Long user_id){return memberRepository.getUserName(user_id);}


}
