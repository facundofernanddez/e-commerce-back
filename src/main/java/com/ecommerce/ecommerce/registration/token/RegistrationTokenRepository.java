package com.ecommerce.ecommerce.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {

    @Query("SELECT a FROM RegistrationToken a WHERE a.token = :token")
    Optional<RegistrationToken> findByToken(@Param("token") String token);

    @Query("UPDATE RegistrationToken a SET a.confirmedAt = :confirmedAt WHERE a.token = :token")
    @Modifying
    int updateConfirmedAt(@Param("token") String token, @Param("confirmedAt") LocalDateTime confirmedAt);
}
