package com.ssafy.enjoytrip.repository;

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

}
