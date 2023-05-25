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
                member.getUser_id(),
            member.getUsername(),
            member.getPhone_number(),
                member.getProfileimg()
        );
        return memberDto;
    }

    public MemberDto getMemberInfoByUsername(String username){
        System.out.println("SELECT B.* FROM member as B LEFT JOIN user as A on B.user_id = A.user_id\n" +
                "where B.username = " +"\""+username+"\"");
        List<Object[]> result = em.createNativeQuery("SELECT B.* FROM member as B LEFT JOIN user as A on B.user_id = A.user_id where B.username = ?")
                .setParameter(1,username)
                        .getResultList();

        return new MemberDto((Long)result.get(0)[3],result.get(0)[2].toString(),result.get(0)[0].toString(),result.get(0)[1].toString());
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
        String jpql = "INSERT INTO user (email,password,permission ,created) VALUES(?,?,?,?)";
        em.createNativeQuery(jpql)
                .setParameter(1, signUpDto.getEmail())
                .setParameter(2, signUpDto.getPassword())
                .setParameter(3, 'M')
                .setParameter(4, LocalDateTime.now())
                .executeUpdate();
        Long user_id = (Long)em.createQuery("SELECT MAX(u.user_id) FROM User u")
                .getSingleResult();
        jpql = "insert into member( username, phone_number, user_id) values (?,?,?)";
        em.createNativeQuery(jpql)
                .setParameter(1,signUpDto.getUsername())
                .setParameter(2,signUpDto.getPhone_number())
                .setParameter(3,user_id)
                .executeUpdate();
    }

    public int checkEmail(String email){
        String jpql = "select u.user_id from User u where u.email =:email";

        try {
            em.createQuery(jpql).setParameter("email",email)
                    .getSingleResult();
            return 1;

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

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
