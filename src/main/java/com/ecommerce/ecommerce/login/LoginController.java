package com.ecommerce.ecommerce.login;

import lombok.AllArgsConstructor;
import net.bytebuddy.pool.TypePool.Default.LazyTypeDescription.GenericTypeToken.Resolution.Raw;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.model.user.User;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @PostMapping
    // @ResponseBody
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
       return new ResponseEntity<>(loginService.login(request).toString(), HttpStatus.OK);
    }
}
