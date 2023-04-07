package com.ecommerce.ecommerce.registration.token;

import com.ecommerce.ecommerce.commons.GenericServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationTokenService extends GenericServiceImpl<RegistrationToken, String> {

    private final RegistrationTokenRepository registrationTokenRepository;

    public void saveRegistrationToken(RegistrationToken token){
        registrationTokenRepository.save(token);
    }

    public RegistrationToken getToken(String token){
        Optional<RegistrationToken> tokenFound = registrationTokenRepository.findByToken(token);

        return tokenFound.get();
    }

    public int setConfirmedAt(String token){
        return registrationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public CrudRepository<RegistrationToken, String> getDao() {
        return null;
    }
}
