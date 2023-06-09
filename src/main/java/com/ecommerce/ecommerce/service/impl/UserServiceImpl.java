package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.exception.MyException;
import com.ecommerce.ecommerce.model.user.User;
import com.ecommerce.ecommerce.registration.token.RegistrationToken;
import com.ecommerce.ecommerce.registration.token.RegistrationTokenService;
import com.ecommerce.ecommerce.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserServiceImpl  implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public User signUpUser(User user){
        boolean isUserExist = userRepository.findByEmail(user.getEmail()) != null;

        if (isUserExist){
            //TODO: 
            //if user not confirm email, can register again
            throw new IllegalStateException("User already exist");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return user;
    }

    public User loginUser(String email, String password) throws MyException{
        User user = userRepository.findByEmail(email);
        boolean isUserExist = user.getEmail() != null;
        boolean isCorrectPassword = bCryptPasswordEncoder.matches(password, user.getPassword());

        if(!isUserExist){
            throw new MyException("user not found");
        }else if(!isCorrectPassword){
            throw new MyException("incorrect password");
        }

        return user;
    }

    public void enableUser(String email){
        userRepository.enableUser(email);
    }
}
