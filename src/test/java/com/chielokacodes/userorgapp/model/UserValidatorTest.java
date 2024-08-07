//package com.chielokacodes.userorgapp.model;
//
//import com.chielokacodes.userorgapp.dto.SignupRequest;
//import com.chielokacodes.userorgapp.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//
//import static org.mockito.Mockito.when;
//
//class UserValidatorTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserValidator userValidator;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testValidateUserWithValidData() {
//        SignupRequest signupRequest = new SignupRequest();
//        signupRequest.setFirstName("John");
//        signupRequest.setLastName("Doe");
//        signupRequest.setEmail("john.doe@example.com");
//        signupRequest.setPassword("password123");
//        signupRequest.setPhone("12345678901");
//
//        when(userRepository.existsByEmail("john.doe@example.com")).thenReturn(false);
//
//        Errors errors = userValidator.validateUser(signupRequest);
//        assertNull(errors);
//    }
//
//    @Test
//    void testValidateUserWithInvalidData() {
//        SignupRequest signupRequest = new SignupRequest();
//        signupRequest.setFirstName("");
//        signupRequest.setLastName("");
//        signupRequest.setEmail("invalid-email");
//        signupRequest.setPassword("123");
//        signupRequest.setPhone("1234");
//
//        when(userRepository.existsByEmail("invalid-email")).thenReturn(true);
//
//        Errors errors = userValidator.validateUser(signupRequest);
//        assertNotNull(errors);
//        List<Error> errorList = errors.getErrors();
//        assertEquals(5, errorList.size());
//    }
//}
