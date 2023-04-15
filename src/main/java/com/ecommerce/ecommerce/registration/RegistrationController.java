package com.ecommerce.ecommerce.registration;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.exception.MyException;
import com.ecommerce.ecommerce.model.user.User;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest request) throws MyException{
        User user = registrationService.register(request);

        try {
            Map<String, User> responsePayload = new HashMap<String, User>();
            responsePayload.put("user", user);

            return new ResponseEntity<Object>(responsePayload, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>("error with registration", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
