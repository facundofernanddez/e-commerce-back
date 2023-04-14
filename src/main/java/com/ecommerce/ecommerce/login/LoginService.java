package com.ecommerce.ecommerce.login;

import com.ecommerce.ecommerce.exception.MyException;
import com.ecommerce.ecommerce.model.user.User;
import com.ecommerce.ecommerce.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserServiceImpl userService;

    public User login(LoginRequest request) throws MyException{
       return userService.loginUser(request.getEmail(), request.getPassword());
    }
}
