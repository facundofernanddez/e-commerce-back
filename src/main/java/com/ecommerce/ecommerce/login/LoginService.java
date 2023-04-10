package com.ecommerce.ecommerce.login;

import com.ecommerce.ecommerce.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserServiceImpl userService;

    public String login(LoginRequest request){
        return userService.loginUser(request.getEmail(), request.getPassword());
    }
}
