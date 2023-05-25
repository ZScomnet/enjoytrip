package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.dto.*;
import com.ssafy.enjoytrip.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public Long LikeCnt(int plan_id){
        String jpql = "select count(*) from likes where plan_id=:planId group by plan_id";
        try {
            Long num = (Long)em.createNativeQuery(jpql).setParameter("planId", plan_id)
                    .getSingleResult();
            em.clear();

            return num;

        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    public Long checkLike(Long user_id, int plan_id){
        String jpql = "select count(*) from likes where plan_id=:planId and user_id=:userId";
        Long num = (Long)em.createNativeQuery(jpql).setParameter("planId", plan_id)
                .setParameter("userId", user_id)
                .getSingleResult();
        em.clear();
        System.out.println(num);

        return num;
    }

    public String planRank(int plan_id){
        String jpql = "select m.username from member m join plan p on m.user_id=p.user_id where p.plan_id =:planId ";
        String userName = (String)em.createNativeQuery(jpql).setParameter("planId", plan_id)
                .getSingleResult();
        em.clear();
        return userName;
    }





    //    @Transactional
//    public Plan insertPlan(String plan_name, Long user_id){
//        String jpql = "INSERT INTO Plan (plan_name, user_id) VALUES(?,?)";
//        System.out.println("sql 처리 됨?");
//        em.createNativeQuery(jpql).setParameter(1,plan_name )
//                .setParameter(2, user_id).executeUpdate();
//
//        Plan plan = em.createQuery("select pl from Plan pl where pl.id =:userId and pl.planName=:planName ", Plan.class).setParameter("userId", user_id)
//                .setParameter("planName",plan_name).getSingleResult();
//        em.clear();
//        return plan;
//    }
    @Transactional
    @Override
    public void insertDetailPlan(PlanDetailDto planDetailDto){
        String plan_name = planDetailDto.getPlanTitle();
        String jpql = "INSERT INTO Plan (plan_name, user_id) VALUES(?,?)";
        System.out.println("sql 처리 됨?");
        em.createNativeQuery(jpql).setParameter(1,plan_name )
                .setParameter(2, planDetailDto.getUser_id()).executeUpdate();
        System.out.println("dfdfasdfasdf"+planDetailDto.getPlan().size());

        Long plan_id =(Long)em.createNativeQuery(" SELECT LAST_INSERT_ID() from Plan").getSingleResult();

//        Plan plan = em.createQuery("SELECT pl from Plan pl order by plan_id desc limit 1").getSingleResult();

//        Plan plan = em.createQuery("select pl from Plan pl where pl.id =:userId and pl.planName=:planName ", Plan.class).setParameter("userId", 1)
//                .setParameter("planName",plan_name).getSingleResult();
//        int planId =  plan.getPlan_id();
        for (int i=0;i<planDetailDto.getPlan().size();i++) {
            for(int j=0;j<planDetailDto.getPlan().get(i).size();j++) {
                jpql = "INSERT INTO Plan_Info (plan_id, content_id, plan_day, plan_day_idx) VALUES(?,?,?,?)";
                AttractionInfo tour = planDetailDto.getPlan().get(i).get(j);
                em.createNativeQuery(jpql).setParameter(1, plan_id)
                        .setParameter(2, tour.getId())
                        .setParameter(3, i+1)
                        .setParameter(4, j+1)
                        .executeUpdate();
                System.out.println("Theumbnail 처리 됨?"+i+" "+j);
                if(i==0&j==0){
                    jpql = "update Plan pl set thumbnail=(select first_image from attraction_info where content_id=?) where plan_id=?";
                    System.out.println("contentId : "+tour.getId());
                    System.out.println(jpql.toString());
                    em.createNativeQuery(jpql).setParameter(1, tour.getId())
                            .setParameter(2, plan_id)
                            .executeUpdate();

                }
            }
        }
        em.clear();

    }
    @Transactional
    @Override
    public void deletePlan(String plan_name, Long user_id){
        String jpql = "update Plan pl set blocked=true where pl.id =:userId and pl.plan_name=:planName";
        System.out.println("delete 체크 완료");
        em.createQuery(jpql).setParameter("userId", user_id)
                .setParameter("planName",plan_name).executeUpdate();
        em.clear();

    }

    public PlanDetailDto selectPlanList(int plan_id){
        String jpql = "select p from Plan p where p.plan_id=:planId";
        Plan plan = (Plan)em.createQuery(jpql)
                .setParameter("planId", plan_id).getSingleResult();

        String planName = plan.getPlan_name();

//        GetMyListDto getMyListDto;
//        jpql = "SELECT DISTINCT a,b.plan_id, b.plan_day,b.plan_day_idx FROM attraction_info a INNER JOIN a. (SELECT pi.plan_id, content_id,pi.plan_day, pi.plan_day_idx FROM plan_info as pi INNER JOIN plan as p ON pi.plan_id = p.plan_id) b ON a.content_id = b.content_id AND b.plan_id = 5";

        jpql = "select pi from PlanInfo pi where pi.planId=:planId order by pi.planDay asc, pi.planDayIdx asc";
//
        List<PlanInfo> planInfoList = em.createQuery(jpql)
                .setParameter("planId", plan_id).getResultList();
        System.out.println(planInfoList.get(1));
        //contentIdList 추출
        List<Integer> contentIdList = new ArrayList<>();
        for (int i = 0; i < planInfoList.size(); i++) {
            contentIdList.add(planInfoList.get(i).getContentId());
        }
        System.out.println("contentIdList : "+contentIdList);

        //contentIdList 통해 attractionInfo 접근해서 세부정보 가져오기
        List<AttractionInfo> attractionInfoList = new ArrayList<>();
        for (int i = 0; i < contentIdList.size(); i++) {
            jpql = "select ai from AttractionInfo ai where ai.id=:contentId";
            attractionInfoList.add((AttractionInfo) em.createQuery(jpql).setParameter("contentId",contentIdList.get(i)).getSingleResult());
        }

        System.out.println("attractionInfoList : "+attractionInfoList.get(1));
//        //몇일짜리 여행인지
        jpql = "SELECT max(pi.planDay) FROM PlanInfo pi where pi.planId =:planId";

        int n = (int)em.createQuery(jpql)
                .setParameter("planId", plan_id).getSingleResult();

        System.out.println("maxDay : "+n);


        List<List<AttractionInfo>> result = new ArrayList<>();
        int idx = 0;
        for (int day = 1; day <= n; day++) {
            List<AttractionInfo> dayList = new ArrayList<>();

            for (int j = idx; j < planInfoList.size(); j++) {
                int plan_day =planInfoList.get(j).getPlanDay();
                if(day == plan_day){
                    int contentId = planInfoList.get(j).getContentId();
                    for (int i = 0; i < attractionInfoList.size(); i++) {
                        if(contentId==attractionInfoList.get(i).getId()){
                            dayList.add(attractionInfoList.get(i));
                            break;
                        }
                    }
                }
                //달라
                else if(day !=  plan_day) {
                    idx=j;
                    break;
                }
            }


            result.add(dayList);
        }
//
//
//
//        // planInfo -> attractionInfo
////        for(int i=0;i<planInfoList.size();i++){
////            if(result.size() == planInfoList.get(i).getPlanDay()){
////                result.get(result.size()-1).add(planInfoList.get(i));
////            }else{
////                result.add(new ArrayList<>());
////                result.get(result.size()-1).add(planInfoList.get(i));
////            }
////        }
//        List<Integer> dayList= new ArrayList<>();
//        for (PlanInfo info: planInfoList) {
//            dayList.add(info.getPlanDay());
//        }

        PlanDetailDto planDetailDto = new PlanDetailDto(planName,(long)1,result);

        return planDetailDto;

    }

    @Transactional
    @Override
    public void updatePlanList(int plan_id, PlanDetailDto planDetailDto){

        String jpql = "DELETE FROM PlanInfo pi WHERE pi.planId=:planId";
//        System.out.println("sql 처리 됨?");
        em.createQuery(jpql).setParameter("planId",plan_id ).executeUpdate();


        for (int i=0;i<planDetailDto.getPlan().size();i++) {
            for(int j=0;j<planDetailDto.getPlan().get(i).size();j++) {
                String jpql2 = "INSERT INTO Plan_Info (plan_id, content_id, plan_day, plan_day_idx)  VALUES(?,?,?,?)";
                AttractionInfo tour = planDetailDto.getPlan().get(i).get(j);
                em.createNativeQuery(jpql2).setParameter(1, plan_id)
                        .setParameter(2, tour.getId())
                        .setParameter(3, i+1)
                        .setParameter(4, j+1)
                        .executeUpdate();

                if(i==0&j==0){
                    jpql = "update Plan pl set thumbnail=(select ai.first_image from attraction_info ai where ai.content_id=?) where plan_id=?";
                    System.out.println("contentId : "+tour.getId());
                    em.createNativeQuery(jpql).setParameter(1, tour.getId())
                            .setParameter(2, plan_id)
                            .executeUpdate();

                }

            }
        }
        em.clear();


    }

    public List<MyPlanListsDto> myplanLists(String username){
        String jpql = "select p.plan_id ,p.plan_name , p.thumbnail from Plan p join  Member m on p.user_id = m.user_id where m.username =:username";
        List<MyPlanListsDto> myPlanListsDto =  (List<MyPlanListsDto>)em.createNativeQuery(jpql)
                .setParameter("username", username)
                .getResultList();


        return myPlanListsDto;
    }


    public List<MyPlanListsDto> getAllPlanLists(){
//        String jpql = "select p.plan_id ,p.plan_name from Plan p join  Member m on p.user_id = m.user_id";

        String jpql ="select p.plan_id, plan_name, thumbnail from Plan p left join  Member m on p.user_id = m.user_id left join  likes l on p.plan_id = l.plan_id group by plan_id order by count(*) desc";

        List<MyPlanListsDto> allPlanListsDto =  (List<MyPlanListsDto>)em.createNativeQuery(jpql)
                .getResultList();
        System.out.println(allPlanListsDto);

        return allPlanListsDto;
    }

    public List<MyPlanListsDto> getNewAllPlanLists(){

        String jpql = "select p.plan_id ,p.plan_name from Plan p join  Member m on p.user_id = m.user_id";
        List<MyPlanListsDto> allNewPlanListsDto =  (List<MyPlanListsDto>)em.createNativeQuery(jpql)
                .getResultList();
        return allNewPlanListsDto;
    }

    public List<AttractionInfo> LikeAttractionList(int page){
        String jpql =  "select  ai.* from attraction_info as ai left join plan_info as pi on pi.content_id = ai.content_id\n" +
                "group by ai.content_id order by count(*) desc limit ?,?";
        List<AttractionInfo> likeAttractionList = em.createNativeQuery(jpql)
                .setParameter(1,(page-1)*100)
                .setParameter(2,100)
                .getResultList();

        return likeAttractionList;
    }


    public String getDescription(Long content_id){
        AttractionDescription attractionDescription = em.find(AttractionDescription.class, content_id);
        String overView = attractionDescription.getOverview();

        return overView;
    }
    public List<AttractionRankingDto> getAttractionRanking(){
        String jpql = "SELECT B.content_id, B.count,A.first_image, A.title, A.addr1 FROM attraction_info A " +
                "INNER JOIN (SELECT P.content_id, COUNT(P.content_id) AS count FROM plan_info P GROUP BY P.content_id) B " +
                "ON A.content_id = B.content_id " +
                "ORDER BY B.count DESC";
        List<AttractionRankingDto> result = em.createNativeQuery(jpql).getResultList();
        return result;
    }


}