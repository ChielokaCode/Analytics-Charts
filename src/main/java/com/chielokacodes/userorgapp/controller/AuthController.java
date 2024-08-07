package com.chielokacodes.userorgapp.controller;


import com.chielokacodes.userorgapp.serviceImpl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public ResponseEntity<String> homePage(){
        String welcome = authService.showWelcome();
        return new ResponseEntity<>(welcome, HttpStatus.OK);
    }

}
