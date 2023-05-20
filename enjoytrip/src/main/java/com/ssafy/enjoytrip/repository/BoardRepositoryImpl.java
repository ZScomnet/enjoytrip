package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.BoardGroup;
import com.ssafy.enjoytrip.model.Plan;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository{

    private final EntityManager em;
    @Transactional
    public void insertBoard(Board board){
        String jpql = "INSERT INTO Board (title, author, text, group_id, created) VALUES(?,?,?,?)";
        em.createNativeQuery(jpql).setParameter(1,board.getTitle() )
                .setParameter(2, board.getAuthor())
                .setParameter(3, board.getText())
                .setParameter(4, board.getGroup_id())
                .setParameter(5, LocalDateTime.now())
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
    public List<BoardGroup> getBoardGroup(){
        List<BoardGroup> boardGroup =  em.createQuery("select bd from BoardGroup bd", BoardGroup.class).getResultList();
        return boardGroup;
    }

    public List<Board> getCategoryBoard(int groupId){
        List<Board> groupIdBoard =  em.createQuery("select b from Board b where b.group_id =:groupId", Board.class)
                .setParameter("groupId",groupId)
                .getResultList();
        return groupIdBoard;
    }

    @Transactional
    public void deleteBoard(int BoardId){
        String jpql = "update Board b set blocked=true where b.id =:boardId";
        System.out.println("delete 체크 완료");
        em.createQuery(jpql).setParameter("boardId", BoardId)
                .executeUpdate();
        em.clear();
    }

    @Transactional
    public void updateBoard(Board board){
        String jpql = "update Board b set b.id=:id, b.author=:author, b.group_id=:groupId, b.text=:text, b.title=:title where b.id =:boardId";
        em.createQuery(jpql).setParameter("id", board.getId())
                .setParameter("author", board.getAuthor())
                .setParameter("groupId", board.getGroup_id())
                .setParameter("text", board.getText())
                .setParameter("title", board.getTitle())
                .executeUpdate();
        em.clear();
    }

    @Override
    public List<Board> getALlBoard() {

        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }


}