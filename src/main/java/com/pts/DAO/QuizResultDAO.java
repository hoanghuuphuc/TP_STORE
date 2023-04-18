package com.pts.DAO;

import com.pts.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizResultDAO extends JpaRepository<QuizResult,Long> {

    @Query("select p from QuizResult p where p.account.tps_Username =?1 and p.quiz.id = ?2")
    List<QuizResult> ktbai(String username, int id);

    @Query("select p from QuizResult p where p.account.tps_Username =?1 and p.quiz.content.tps_id =?2 and p.quiz.course.tps_id =?3")
    List<QuizResult> ketqua(String Username,int content,int course);
}

