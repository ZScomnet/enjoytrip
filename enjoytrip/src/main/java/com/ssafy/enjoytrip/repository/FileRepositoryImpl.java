//package com.ssafy.enjoytrip.repository;
//
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Repository
//@RequiredArgsConstructor
//public class FileRepositoryImpl implements FileRepository{
//
//    private final EntityManager em;
//    @Transactional
//    public void uploadFile(String savePath, Long user_id){
//        String jpql = "update Member m set profileimg=? where m.user_id =:userId";
//
//        em.createQuery(jpql).setParameter("userId", user_id)
//                .setParameter("planName",plan_name).executeUpdate();
//        em.clear();
//
//    }
//
//
//}