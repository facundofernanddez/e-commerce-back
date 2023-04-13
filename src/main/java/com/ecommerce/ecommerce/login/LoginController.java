package com.ecommerce.ecommerce.login;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.model.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin
public class LoginController {

    private final LoginService loginService;


    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) throws Exception{
        User user = loginService.login(request);
        
        try {
            Map<String, User> respondePayload = new HashMap<>();
            respondePayload.put("user", user);

            return new ResponseEntity<>(respondePayload, HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>("Error with login", HttpStatus.UNAUTHORIZED);
        }
    }
}
