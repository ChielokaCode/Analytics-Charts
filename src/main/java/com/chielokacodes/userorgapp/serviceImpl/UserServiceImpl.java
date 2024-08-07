package com.chielokacodes.userorgapp.serviceImpl;

import com.chielokacodes.userorgapp.dto.LoginRequest;
import com.chielokacodes.userorgapp.dto.SignupRequest;
import com.chielokacodes.userorgapp.dto.SuccessResponse;
import com.chielokacodes.userorgapp.enums.Role;
import com.chielokacodes.userorgapp.exeption.UserNotAuthorized;
import com.chielokacodes.userorgapp.model.User;
import com.chielokacodes.userorgapp.repository.UserRepository;
import com.chielokacodes.userorgapp.services.UserService;
import com.chielokacodes.userorgapp.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not Found"));
    }

    public SuccessResponse saveUser(SignupRequest signupRequest) {
        User existingUserByEmail = userRepository.findUserByEmail(signupRequest.getEmail());
        if (existingUserByEmail != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Duplicate email or user ID");
        }

        User user = new User();
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setPhone(signupRequest.getPhone());
        user.setEmail(signupRequest.getEmail());
        user.setUserRole(Role.USER);

        User createdUser = userRepository.save(user);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus("success");
        successResponse.setMessage("Register Successful");

        return successResponse;
    }


    public SuccessResponse logInUser(LoginRequest loginRequest) throws UserNotAuthorized {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = loadUserByUsername(loginRequest.getEmail());
        User user = userRepository.findUserByEmail(loginRequest.getEmail());


        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new UserNotAuthorized("Authentication unsuccessful");
        }


        String accessToken = jwtUtils.createJwt.apply((User) authentication.getPrincipal());

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus("success");
        successResponse.setMessage("Login Successful: " + accessToken);

        return successResponse;
    }
}
