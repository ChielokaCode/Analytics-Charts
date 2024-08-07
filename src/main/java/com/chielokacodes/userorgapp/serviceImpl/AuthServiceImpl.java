package com.chielokacodes.userorgapp.serviceImpl;

import com.chielokacodes.userorgapp.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    public String showWelcome() {
        return "Welcome to Hng-Task 2";
    }
}
