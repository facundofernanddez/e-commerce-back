package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT a FROM User a WHERE a.email = :email")
    User findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User a SET a.enabled = true WHERE a.email = :email")
    void enableUser(@Param("email") String email);
}
