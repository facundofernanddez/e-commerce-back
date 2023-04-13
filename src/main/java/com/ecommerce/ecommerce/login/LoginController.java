package com.ecommerce.ecommerce.login;

import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.model.user.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.google.gson.Gson;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@RestController
@RequestMapping("/login")
@AllArgsConstructor
@CrossOrigin(
    maxAge = 3600, 
    methods = RequestMethod.POST, 
    allowedHeaders = "*")

public class LoginController {

    private final UserRepository repository;
    private final LoginService loginService;

    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Response login(@RequestBody LoginRequest request){
        User user = repository.findByEmail(request.getEmail());

        return Response
            .status(Status.OK)
            .entity(user)
            .type("application/json")
            .build();
    }
}
