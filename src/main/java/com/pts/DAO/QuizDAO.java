package com.pts.DAO;

import com.pts.entity.Chapter;
import com.pts.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {
    @Query("select p from Quiz p where p.content.tps_id =?1")
    Quiz layquiz(int id);

    @Query("select p from Quiz p where p.id =?1")
    Quiz layquizid(int id);

    @Query("SELECT c FROM Quiz c WHERE  c.course.tps_Name like  '%'+:keyword+'%' ")
    List<Quiz> timkiemKhoaHoc(@Param("keyword") String keyword);


}
