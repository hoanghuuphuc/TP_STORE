package com.pts.DAO;

import com.pts.entity.Category;
import com.pts.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChapterDAO extends JpaRepository<Chapter,String> {
    @Query("select p from Chapter p where p.course.tps_id = ?1")
    List<Chapter>findByCourse(int id);

    @Query("select p from Chapter p where p.tps_id = ?1")
    Chapter findcourse(int id);
    @Query("select p from Chapter p where p.tps_id = ?1")
    Chapter find(int id);

    @Query("SELECT c FROM Chapter c WHERE  c.tps_title like  '%'+:keyword+'%' ")
    List<Chapter> timkiem(@Param("keyword") String keyword);

    @Query("SELECT c FROM Chapter c WHERE  c.course.tps_Name like  '%'+:keyword+'%' ")
    List<Chapter> timkiemKhoaHoc(@Param("keyword") String keyword);





}
