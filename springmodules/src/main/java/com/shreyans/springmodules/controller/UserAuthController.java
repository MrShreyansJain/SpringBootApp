package com.shreyans.springmodules.controller;

import com.shreyans.springmodules.entity.UserAuthEntity;
import com.shreyans.springmodules.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(path = "/saveUserAuth")
    public ResponseEntity<String> saveUserAuth(@RequestBody UserAuthEntity userAuth){
        //hashing the password before saving
        userAuth.setPassword(passwordEncoder.encode(userAuth.getPassword()));
        userAuthService.save(userAuth);
        return ResponseEntity.ok("User saved SuccessFully");
    }
}
