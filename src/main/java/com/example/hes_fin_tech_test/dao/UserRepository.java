package com.example.hes_fin_tech_test.dao;

import com.example.hes_fin_tech_test.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long>,
        CrudRepository<UserAccount, Long>,
        PagingAndSortingRepository<UserAccount, Long> {

    @Query(value = "select * from m_user_account u where u.username like %:username% and u.role like %:role%",
            countQuery = "select count (*) from m_user_account u",
            nativeQuery = true)
    Page<UserAccount> findAllUsersByUsernameAndRole(Pageable pageable,
                                                    @Param("username") String username,
                                                    @Param("role") String role);

    UserAccount findByUsername(String username);
}
