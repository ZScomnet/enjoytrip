package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.MyPlanListsDto;
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

    @Override
    public Member login(Member member) {
        String jpql = "select u.user_id ,m.user_name from user u join  Member m on p.user_id = m.user_id where p.user_id =:userId and p.password =:passWord";
        Member memberInfo = (Member) em.createNativeQuery(jpql)
                .setParameter("userId", member.getUser_id())
                .setParameter("passWord", member.getPassword())
                .getSingleResult();

        return memberInfo;
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
