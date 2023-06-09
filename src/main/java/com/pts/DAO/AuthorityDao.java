package com.pts.DAO;


import com.pts.entity.Account;
import com.pts.entity.Authority;
import com.pts.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorityDao extends JpaRepository<Authority,Integer> {
    @Query("SELECT p.role from Authority p where p.account.tps_Username=?1")
    List<Role>findRolesByUsername(String username);
    @Query("select DISTINCT p from Authority p where p.account In ?1")
    List<Authority>findAuthoritiesOfAdministrators(List<Account>accounts);
    @Query("select p from Authority  p where p.account.tps_Username =?1")
    Authority laytk(String username);

    @Query("SELECT c FROM Authority c WHERE  c.account.tps_Username like  '%'+:keyword+'%' ")
    List<Authority> timkiem(@Param("keyword") String keyword);

}
