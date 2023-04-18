package com.pts.DAO;

import com.pts.entity.Course;
import com.pts.entity.Order;
import com.pts.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Integer> {
    @Query("select p from Order p WHERE p.tps_username =?1 and p.courseor.tps_id =?2")
    Order kiemtracode(String username,Integer courseId);

    @Query("select p from Order p where p.tps_username=?1")
    List<Order> myCrouse(String username);

    @Query("select p from Order p where p.tps_username=?1")
    Order myCrouse1(String username);

    @Query("select p from Order p where p.tps_username=?1 and p.courseor.tps_id=?2")
    Order ktKhoaHoc(String username,int id);
    @Query("select p from Order p where p.courseor.tps_id =?1 and p.tps_username =?2")
    Order kiemtraCrouse(int id,String username);
    @Query("SELECT MONTH(o.tps_date), SUM(c.tps_Price) FROM Order o JOIN o.courseor c WHERE YEAR(o.tps_date) = YEAR(CURRENT_DATE()) GROUP BY MONTH(o.tps_date)")
    List<Object[]> getMonthlyRevenue();

    @Query( "SELECT p FROM Order p WHERE p.tps_date BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query("SELECT COUNT(o) FROM Order o WHERE YEAR(o.tps_date) = :year")
    Long countOrdersByYear(@Param("year") Integer year);

    @Query("SELECT c FROM Order c WHERE  c.tps_username like  '%'+:keyword+'%' ")
    List<Order> timtaikhoan(@Param("keyword") String keyword);

    @Query("SELECT o FROM Order o WHERE o.tps_username LIKE %:keyword% AND o.tps_date BETWEEN :startDate AND :endDate")
    List<Order> searchOrders(@Param("keyword") String keyword, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


}
