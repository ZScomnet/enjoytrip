package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.SignInDto;
import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final EntityManager em;
    public List<User> getAllUser(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public MemberDto getMemberInfoById(Long id){
        Member member = em.find(Member.class,id);
        MemberDto memberDto = new MemberDto(
            member.getUsername(),
            member.getAddress(),
            member.getAge(),
            member.getPhone_number()
        );
        return memberDto;
    }
    public void signIn(SignInDto signInDto){
        User user = em.createQuery("select u from User u where u.email = :email and u.password = :password",User.class)
                .setParameter("email",signInDto.getEmail())
                .setParameter("password",signInDto.getPassword())
                .getSingleResult();
        if(user != null){

        }
    }
    public void updateInfo(MemberDto memberDto){

    }
    public void updatePassword(){

    }
}
