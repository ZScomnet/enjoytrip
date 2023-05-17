package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.PlanDetailDto;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Plan;
import com.ssafy.enjoytrip.model.PlanInfo;
import com.ssafy.enjoytrip.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

//    @Transactional
//    public void like(int plan_id){
//        String jpql = "UPDATE Plan  pl SET pl.likes = pl.likes+1 WHERE pl.plan_id =:planId"; // 앞에 :가 붙은 변수에 파라미터를 바인딩 받는다
//        em.createQuery(jpql)
//                .setParameter("planId", plan_id)
//                .executeUpdate();
////        jpql = //user_id랑 plan_id 묶은 테이블에 삽입 나중에 유저 아이디 가지고
//        //플랜 아이디 참조해서 plan테이블 접근 후 데이터 가져오기
//        em.clear();
//    }
    @Transactional
    @Override
    public void insertlikes(int plan_id, Long user_id){
        String jpql = "INSERT INTO likes (plan_id, user_id) VALUES(?,?)";
        System.out.println("sql 처리 됨?");
        em.createNativeQuery(jpql).setParameter(1,plan_id)
                .setParameter(2, user_id).executeUpdate();
        em.clear();
    }
    @Transactional
    @Override
    public void deletelikes(int plan_id, Long user_id){
        String jpql = "delete from likes dell where dell.plan_id=:planId and dell.id = :userId";
        System.out.println("sql 처리 됨?");
        em.createQuery(jpql).setParameter("planId", plan_id)
                .setParameter("userId",user_id).executeUpdate();
        em.clear();
    }

    @Transactional
    public Plan insertPlan(String plan_name, Long user_id){
        String jpql = "INSERT INTO Plan (plan_name, user_id) VALUES(?,?)";
        System.out.println("sql 처리 됨?");
        em.createNativeQuery(jpql).setParameter(1,plan_name )
                .setParameter(2, user_id).executeUpdate();

        Plan plan = em.createQuery("select pl from Plan pl where pl.id =:userId and pl.planName=:planName ", Plan.class).setParameter("userId", user_id)
                .setParameter("planName",plan_name).getSingleResult();
        em.clear();
        return plan;
    }
    @Transactional
    @Override
    public void insertDetailPlan(PlanDetailDto planDetailDto){
        planDetailDto.getContendIdList().get(0).
        for (int i=1; i<= planDetailDto.getContendIdList().size();i++) {
            String jpql = "INSERT INTO Plan_Info (plan_id, content_id, plan_day, plan_day_idx) VALUES(?,?,?,?)";
            System.out.println("DetailPlan 삽입 완료");
            em.createNativeQuery(jpql).setParameter(1, plan_id)
                    .setParameter(2, integer)
                    .setParameter(3,plan_date)
                    .setParameter(4,plan_date_idx)
                    .executeUpdate();
        }
        em.clear();

    }
    @Transactional
    @Override
    public void deletePlan(String plan_name, Long user_id){
        String jpql = "update Plan pl set blocked=true where pl.id =:userId and pl.planName=:planName  ";
        System.out.println("delete 체크 완료");
        em.createQuery(jpql).setParameter("userId", user_id)
                .setParameter("planName",plan_name).executeUpdate();
        em.clear();

    }

}