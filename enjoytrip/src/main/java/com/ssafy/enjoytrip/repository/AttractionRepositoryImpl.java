package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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


}