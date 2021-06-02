package com.example.hes_fin_tech_test.dao;

import com.example.hes_fin_tech_test.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long>,
        CrudRepository<UserAccount, Long>,
        PagingAndSortingRepository<UserAccount, Long> {

    /*search and pagination by parameters role and username*/
    @Query(value = "select * from m_user_account u where u.username like %:username% and u.role like %:role%",
            countQuery = "select count (*) from m_user_account u",
            nativeQuery = true)
    Page<UserAccount> findAllUsersByUsernameAndRole(Pageable pageable,
                                                    @Param("username") String username,
                                                    @Param("role") String role);

    UserAccount findByUsername(String username);

    /*encrypting the password for the pattern ^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{3,16}$*/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update m_user_account set password = crypt(password, gen_salt('bf', 8)) where id = :id",
            nativeQuery = true)
    void encryptPassword(@Param("id") Long id);
}
