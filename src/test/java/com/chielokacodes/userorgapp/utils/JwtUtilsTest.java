//package com.chielokacodes.userorgapp.utils;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Date;
//import java.util.function.Function;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//public class JwtUtilsTest {
//
//    @Mock
//    private Function<String, Date> mockExtractExpirationTime;
//
//    @InjectMocks
//    private JwtUtils jwtUtils;
//
//    public JwtUtilsTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testTokenExpiration() {
//        // Mock token and expiration time
//        String sampleToken = "sampleToken";
//        Date now = new Date();
//        Date futureExpiration = new Date(now.getTime() + 3600000); // 1 hour in the future
//
//        // Mock behavior of extractExpirationTime function
//        when(mockExtractExpirationTime.apply(anyString())).thenReturn(futureExpiration);
//
//        // Check if token is expired (should be false for future expiration)
//        boolean isExpired = jwtUtils.isTokenExpired.apply(sampleToken);
//        assert(isExpired);
//
//        // Update mock behavior for past expiration
//        Date pastExpiration = new Date(now.getTime() - 3600000);   // 1 hour ago
//        when(mockExtractExpirationTime.apply(anyString())).thenReturn(pastExpiration);
//
//        // Check if token is expired (should be true for past expiration)
//        isExpired = jwtUtils.isTokenExpired.apply(sampleToken);
//        assert(isExpired);
//    }
//}
