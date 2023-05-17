package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.BoardGroup;
import com.ssafy.enjoytrip.model.Plan;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository{

    private final EntityManager em;
    @Transactional
    public void insertBoard(Board board){
        String jpql = "INSERT INTO Board (title, author, text, group_id) VALUES(?,?,?,?)";
        em.createNativeQuery(jpql).setParameter(1,board.getTitle() )
                .setParameter(2, board.getAuthor())
                .setParameter(3, board.getText())
                .setParameter(4, board.getGroup_id())
                .executeUpdate();
        em.clear();
    }

    /*
     * group id =1  공지사항
     * group id =2  자유게시판
     * group id =3  여행 꿀팁
     * group id =4  이벤트
     * group id =5  여행 후기
     * */
    @Transactional
    public void insertBoardPermission(BoardGroup boardGroup){
        String jpql = "INSERT INTO BoardGroup (group_id, group_name, write_permission, read_permission) VALUES(?,?,?,?)";
        if(boardGroup.getGroup_id() == 1){

        }
        else if(boardGroup.getGroup_id() == 2){

        }
        else if(boardGroup.getGroup_id() == 3){

        }
        else if(boardGroup.getGroup_id() == 4){

        }
        else if(boardGroup.getGroup_id() == 5){

        }
        em.createNativeQuery(jpql).setParameter(1,boardGroup.getGroup_id() )
                .setParameter(2, boardGroup.getGroup_name())
                .setParameter(3, boardGroup.isWrite_permission())
                .setParameter(4, boardGroup.getGroup_id())
                .executeUpdate();
        em.clear();
    }


}