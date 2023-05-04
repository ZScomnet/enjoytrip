package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.SignInDto;
import com.ssafy.enjoytrip.dto.SignUpDto;
import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final EntityManager em;

//    public MemberDto getMemberInfoByEmail(String email){
//        try {
//            Member member = em.createQuery("select m from Member m where m.email = :email", Member.class)
//                    .setParameter("email", email)
//                    .getSingleResult();
//            MemberDto memberDto = new MemberDto(
//                    member.getUsername(),
//                    member.getAddress(),
//                    member.getAge(),
//                    member.getPhone_number()
//            );
//            return memberDto;
//        }catch(NoResultException e){
//            e.printStackTrace();
//            return null;
//        }catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
    public MemberDto login(SignInDto signInDto){
        Member member = em.createQuery("select m from Member m where m.email= :email and m.password= :password",Member.class)
                .setParameter("email",signInDto.getEmail())
                .setParameter("password",signInDto.getPassword())
                .getSingleResult();
        if(member != null) {
            return new MemberDto(
                    member.getUsername(),
                    member.getAddress(),
                    member.getAge(),
                    member.getPhone_number()
            );
        } else return null;
    }
    @Transactional
    public void signUp(SignUpDto signUpDto){
            Member member = new Member();
            // user
            member.setEmail(signUpDto.getEmail());
            member.setPassword(signUpDto.getPassword());
            member.setCreated(LocalDateTime.now().toString());
            member.setPermission('M');
            // member
            member.setUsername(signUpDto.getUsername());
            member.setAddress(signUpDto.getAddress());
            member.setAge(signUpDto.getAge());
            member.setPhone_number(signUpDto.getPhone_number());
            em.persist(member);

    }
//    public void updateInfo(MemberDto memberDto){
//
//    }
//    public void updatePassword(){
//
//    }
}
