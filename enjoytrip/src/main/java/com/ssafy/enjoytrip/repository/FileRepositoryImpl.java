package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepository{

    private final EntityManager em;
    @Transactional
    public void uploadFile(String savePath, Long user_id){
        String jpql = "update Member m set profileimg=? where m.user_id =?";

        em.createNativeQuery(jpql).setParameter(1, savePath)
                .setParameter(2,user_id)
                .executeUpdate();
        em.clear();

    }

    public String getProfileImg(String username){
        String jpql = "select m from Member m where m.username =?";

        Member member= em.createQuery(jpql, Member.class).setParameter(1,username)
                .getSingleResult();
        em.clear();
        String profileImg = member.getProfileimg();
        return profileImg;
    }



}