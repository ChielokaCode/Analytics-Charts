package com.chielokacodes.userorgapp.controller;

import com.chielokacodes.userorgapp.dto.LoginRequest;
import com.chielokacodes.userorgapp.dto.SignupRequest;
import com.chielokacodes.userorgapp.dto.SuccessResponse;
import com.chielokacodes.userorgapp.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> saveUser(@RequestBody SignupRequest signupRequest) {
        SuccessResponse successResponse = userService.saveUser(signupRequest);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> login(@RequestBody LoginRequest loginRequest) {
        SuccessResponse successResponse = userService.logInUser(loginRequest);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
