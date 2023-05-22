package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.MemberDto;
import com.ssafy.enjoytrip.dto.SignUpDto;
import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public Long login(User user) {
        String jpql = "select u.user_id from User u where u.email =:email and u.password =:pw";
        Long userId = (Long)em.createNativeQuery(jpql)
                .setParameter("email", user.getEmail())
                .setParameter("pw", user.getPassword())
                .getSingleResult();
        System.out.println(userId);

////        em.find(User.class,memberInfo.get(0).getUser_id());
//        User result = (User)em.createQuery("select m from Member m where m.user_id = :user_id")
//                        .setParameter("user_id",memberInfo.get(0).getUser_id())
//                        .getSingleResult();
//        System.out.println(result);
        return userId;
    }
    @Transactional
    @Override
    public void signupUser(SignUpDto signUpDto) {
        String jpql = "INSERT INTO user (email,password, created) VALUES(?,?,?)";
        em.createNativeQuery(jpql)
                .setParameter(1, signUpDto.getEmail())
                .setParameter(2, signUpDto.getPassword())
                .setParameter(3, LocalDateTime.now())
                .executeUpdate();
        jpql = "insert into member select ?,?,?,?, u.user_id from user u where u.email = ? and u.password = ?";
        em.createNativeQuery(jpql).setParameter(1, signUpDto.getAddress())
                .setParameter(2,signUpDto.getAge())
                .setParameter(3,signUpDto.getPhone_number())
                .setParameter(4,signUpDto.getUsername())
                .setParameter(5,signUpDto.getEmail())
                .setParameter(6,signUpDto.getPassword())
                .executeUpdate();
    }

    public void signIn(SignUpDto signInDto){
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
