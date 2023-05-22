package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{
    private final EntityManager em;
    public List<User> getAllUser(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public String getUserName(Long user_id) {
        String jpql ="select m.username from Member m where m.user_id=:user_id";
        String userName = (String)em.createNativeQuery(jpql)
                .setParameter("user_id", user_id)
                .getSingleResult();

        return userName;
    }
}
