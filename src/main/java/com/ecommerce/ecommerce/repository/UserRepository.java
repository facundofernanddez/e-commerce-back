package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT a FROM User a WHERE a.email = :email")
    public User findByEmail(@Param("email") String email);
}
