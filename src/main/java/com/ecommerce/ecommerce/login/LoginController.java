package com.ecommerce.ecommerce.login;

import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.model.user.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.google.gson.Gson;

import javax.ws.rs.core.Response;


@RestController
@RequestMapping("/login")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080/", maxAge = 3600)
public class LoginController {

    private final UserRepository repository;

    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response login(@RequestBody LoginRequest request){
        User user = repository.findByEmail(request.getEmail());

        User userToShow = new User(user.getFirstName(), user.getLastName(), user.getEmail());
        Gson gson = new Gson();
        

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(userToShow))
                // .type(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
