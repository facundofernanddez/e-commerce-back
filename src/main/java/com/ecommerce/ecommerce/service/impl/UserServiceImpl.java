package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.commons.GenericServiceImpl;
import com.ecommerce.ecommerce.model.user.User;
import com.ecommerce.ecommerce.model.user.dao.api.UserDaoAPI;
import com.ecommerce.ecommerce.registration.token.RegistrationToken;
import com.ecommerce.ecommerce.registration.token.RegistrationTokenService;
import com.ecommerce.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserDetailsService {

    private final UserDaoAPI userDaoAPI;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RegistrationTokenService registrationTokenService;

    @Override
    public CrudRepository<User, Long> getDao() {
        return userDaoAPI;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public String signUpUser(User user){
        boolean isUserExist = userRepository.findByEmail(user.getEmail()) != null;

        if (isUserExist){
            //TODO: if user not confirm email, can register again
            throw new IllegalStateException("User already exist");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        RegistrationToken registrationToken = new RegistrationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);

        registrationTokenService.save(registrationToken);

        return token;
    }

    public String loginUser(String email, String password) throws IllegalStateException{
        boolean isUserExist = userRepository.findByEmail(email) != null;
        boolean isCorrectPassword = userRepository.findByEmail(email).getPassword().equals(password);

        if(!isUserExist){
            return String.valueOf(new IllegalStateException("Unregistered user"));
        }else if(!isCorrectPassword){
            return String.valueOf(new IllegalStateException("Incorrect password"));
        }

        return "logged";
    }

    public void enableUser(String email){
        userRepository.enableUser(email);
    }
}
