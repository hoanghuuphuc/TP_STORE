package com.pts.DAO;

import com.pts.entity.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizQuestionDAO extends JpaRepository<QuizQuestion,Long> {
    @Query("select p from QuizQuestion p where p.quizQues.content.tps_id =?1")
    List<QuizQuestion> findQuizQuestion(int contentId);

    @Query("select p from QuizQuestion  p where p.id =?1")
    QuizQuestion layid(long id);
    @Query("select p from QuizQuestion p where p.quizQues.id =?1")
    List<QuizQuestion>timidquiz(int id);
}
