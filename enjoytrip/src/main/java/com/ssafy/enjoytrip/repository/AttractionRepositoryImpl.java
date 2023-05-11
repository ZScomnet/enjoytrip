package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttractionRepositoryImpl implements AttractionRepository {

    private final EntityManager em;
    public List<AttractionInfo> getAllAttraction(){
        return em.createQuery("select ai from AttractionInfo ai", AttractionInfo.class).getResultList();
    }

    public List<AttractionInfo> getSidoGugunAttraction(int sido, int gugun){
        String jpql = "select ai from AttractionInfo ai where ai.sidoCode=:sidoCode and ai.gugunCode=:gugunCode"; // 앞에 :가 붙은 변수에 파라미터를 바인딩 받는다.
        List<AttractionInfo> sidoGugunAttraction = em.createQuery(jpql, AttractionInfo.class)
                .setParameter("sidoCode", String.valueOf(sido))
                .setParameter("gugunCode",String.valueOf(gugun)).getResultList();

        return sidoGugunAttraction;
    }

    public AttractionInfo getDetailAttraction(long content_id){
        return em.find(AttractionInfo.class, content_id);
    }

    public List<AttractionInfo> getTypeAttraction(long type){
        String jpql = "select ai from AttractionInfo ai where ai.typeId=:contentType"; // 앞에 :가 붙은 변수에 파라미터를 바인딩 받는다.
        List<AttractionInfo> typeAttraction = em.createQuery(jpql, AttractionInfo.class)
                .setParameter("contentType", type).getResultList();

        return typeAttraction;
    }

    @Transactional
    public void like(int plan_id){
        String jpql = "UPDATE Plan  pl SET pl.likes = pl.likes+1 WHERE pl.plan_id =:planId"; // 앞에 :가 붙은 변수에 파라미터를 바인딩 받는다
        em.createQuery(jpql)
                .setParameter("planId", plan_id)
                .executeUpdate();
//        jpql = //user_id랑 plan_id 묶은 테이블에 삽입 나중에 유저 아이디 가지고
        //플랜 아이디 참조해서 plan테이블 접근 후 데이터 가져오기
        em.clear();
    }
    @Transactional

    public void insertlikes(int plan_id, Long user_id){
        String jpql = "INSERT INTO likes (plan_id, user_id) VALUES(?,?)";
        System.out.println("sql 처리 됨?");
        em.createNativeQuery(jpql).setParameter(1,plan_id)
                .setParameter(2, user_id).executeUpdate();
        em.clear();
    }



}