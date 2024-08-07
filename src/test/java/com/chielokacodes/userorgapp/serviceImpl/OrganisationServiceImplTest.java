//package com.chielokacodes.userorgapp.serviceImpl;
//
//import com.chielokacodes.userorgapp.dto.DataResponse;
//import com.chielokacodes.userorgapp.dto.OrgRequest;
//import com.chielokacodes.userorgapp.dto.OrgResponse;
//import com.chielokacodes.userorgapp.dto.userToOrg.UserReq;
//import com.chielokacodes.userorgapp.exeption.ErrorResponse;
//import com.chielokacodes.userorgapp.exeption.SuccessResponse;
//import com.chielokacodes.userorgapp.exeption.UserNotVerifiedException;
//import com.chielokacodes.userorgapp.model.Organisation;
//import com.chielokacodes.userorgapp.model.User;
//import com.chielokacodes.userorgapp.repository.OrganisationRepository;
//import com.chielokacodes.userorgapp.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class OrganisationServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private OrganisationRepository organisationRepository;
//
//    @InjectMocks
//    private OrganisationServiceImpl organisationService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetOrganisations() {
//        // Mock SecurityContextHolder and Authentication
//        SecurityContext securityContext = mock(SecurityContext.class);
//        SecurityContextHolder.setContext(securityContext);
//
//        Authentication authentication = mock(Authentication.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//
//        // Mock authenticated user details
//        String username = "testuser";
//        when(authentication.getName()).thenReturn(username);
//
//        // Mock UserRepository to return a user
//        User mockUser = new User();
//        mockUser.setEmail(username);
//        when(userRepository.findUserByEmail(username)).thenReturn(mockUser);
//
//        // Mock OrganisationRepository to return organisations
//        Organisation mockOrganisation = new Organisation();
//        mockOrganisation.setOrgId(1L);
//        mockOrganisation.setName("Test Organisation");
//        mockOrganisation.setDescription("Test description");
//
//        List<Organisation> organisations = new ArrayList<>();
//        organisations.add(mockOrganisation);
//        when(organisationRepository.findByUsers(mockUser)).thenReturn(organisations);
//
//        // Invoke the method under test
//        SuccessResponse response = organisationService.getOrganisations();
//
//        // Assertions
//        assertNotNull(response);
//        assertEquals("success", response.getStatus());
//        assertEquals("User organisations gotten successfully", response.getMessage());
//
//        DataResponse data = response.getData();
//        assertNotNull(data);
//        List<OrgResponse> organisationList = data.getOrganisations();
//        assertNotNull(organisationList);
//        assertEquals(1, organisationList.size());
//
//        OrgResponse orgResponse = organisationList.get(0);
//        assertEquals(1L, orgResponse.getOrgId());
//        assertEquals("Test Organisation", orgResponse.getName());
//        assertEquals("Test description", orgResponse.getDescription());
//
//        // Verify interactions
//        verify(userRepository, times(1)).findUserByEmail(username);
//        verify(organisationRepository, times(1)).findByUsers(mockUser);
//    }
//
//    @Test
//    void testGetUserOrganisation() {
//        // Mock SecurityContextHolder and Authentication
//        SecurityContext securityContext = mock(SecurityContext.class);
//        SecurityContextHolder.setContext(securityContext);
//
//        Authentication authentication = mock(Authentication.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//
//        // Mock authenticated user details
//        String username = "testuser";
//        when(authentication.getName()).thenReturn(username);
//
//        // Mock UserRepository to return a user
//        User mockUser = new User();
//        mockUser.setEmail(username);
//        when(userRepository.findUserByEmail(username)).thenReturn(mockUser);
//
//        // Mock OrganisationRepository to return an organisation
//        Organisation mockOrganisation = new Organisation();
//        mockOrganisation.setOrgId(1L);
//        mockOrganisation.setName("Test Organisation");
//        mockOrganisation.setDescription("Test description");
//
//        when(organisationRepository.findByUsersAndOrgId(any(User.class), anyLong())).thenReturn(mockOrganisation);
//
//        // Invoke the method under test
//        SuccessResponse response = organisationService.getUserOrganisation(1L);
//
//        // Assertions
//        assertNotNull(response);
//        assertEquals("success", response.getStatus());
//        assertEquals("User organisation gotten successfully", response.getMessage());
//
//        DataResponse data = response.getData();
//        assertNotNull(data);
//        OrgResponse orgResponse = data.getOrganisation();
//        assertNotNull(orgResponse);
//        assertEquals(1L, orgResponse.getOrgId());
//        assertEquals("Test Organisation", orgResponse.getName());
//        assertEquals("Test description", orgResponse.getDescription());
//
//        // Verify interactions
//        verify(userRepository, times(1)).findUserByEmail(username);
//        verify(organisationRepository, times(1)).findByUsersAndOrgId(mockUser, 1L);
//    }
//
//    @Test
//    void testCreateOrganisationByUser_Success() {
//        // Mock SecurityContextHolder and Authentication
//        SecurityContext securityContext = mock(SecurityContext.class);
//        SecurityContextHolder.setContext(securityContext);
//
//        Authentication authentication = mock(Authentication.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//
//        // Mock authenticated user details
//        String username = "testuser";
//        when(authentication.getName()).thenReturn(username);
//
//        // Mock UserRepository to return a user
//        User mockUser = new User();
//        mockUser.setEmail(username);
//        when(userRepository.findUserByEmail(username)).thenReturn(mockUser);
//
//        // Mock input OrgRequest
//        OrgRequest orgRequest = new OrgRequest();
//        orgRequest.setName("Test Organisation");
//        orgRequest.setDescription("Test description");
//
//        // Mock OrganisationRepository save operation
//        Organisation mockOrganisation = new Organisation();
//        mockOrganisation.setOrgId(1L);
//        mockOrganisation.setName(orgRequest.getName());
//        mockOrganisation.setDescription(orgRequest.getDescription());
//        when(organisationRepository.save(any(Organisation.class))).thenReturn(mockOrganisation);
//
//        // Invoke the method under test
//        Object response = organisationService.createOrganisationByUser(orgRequest);
//
//        // Assertions
//        assertNotNull(response);
//        assertNotNull(response instanceof SuccessResponse);
//
//        SuccessResponse successResponse = (SuccessResponse) response;
//        assertEquals("success", successResponse.getStatus());
//        assertEquals("Organisation created successfully", successResponse.getMessage());
//
//        DataResponse data = successResponse.getData();
//        assertNotNull(data);
//        OrgResponse orgResponse = data.getOrganisation();
//        assertNotNull(orgResponse);
//        assertEquals(1L, orgResponse.getOrgId());
//        assertEquals("Test Organisation", orgResponse.getName());
//        assertEquals("Test description", orgResponse.getDescription());
//
//        // Verify interactions
//        verify(userRepository, times(1)).findUserByEmail(username);
//        verify(organisationRepository, times(1)).save(any(Organisation.class));
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    @Test
//    void testCreateOrganisationByUser_InvalidRequest() {
//        // Mock SecurityContextHolder and Authentication
//        SecurityContext securityContext = mock(SecurityContext.class);
//        SecurityContextHolder.setContext(securityContext);
//
//        Authentication authentication = mock(Authentication.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//
//        // Mock authenticated user details
//        String username = "testuser";
//        when(authentication.getName()).thenReturn(username);
//
//        // Mock UserRepository to return a user
//        User mockUser = new User();
//        mockUser.setEmail(username);
//        when(userRepository.findUserByEmail(username)).thenReturn(mockUser);
//
//        // Mock input OrgRequest with null name
//        OrgRequest orgRequest = new OrgRequest();
//        orgRequest.setName(null);
//        orgRequest.setDescription("Test description");
//
//        // Invoke the method under test and expect an ErrorResponse
//        assertThrows(ErrorResponse.class, () -> organisationService.createOrganisationByUser(orgRequest));
//
//        // Verify interactions
//        verify(userRepository, times(1)).findUserByEmail(username);
//        verify(organisationRepository, never()).save(any(Organisation.class));
//        verify(userRepository, never()).save(any(User.class));
//    }
//
//
//    @Test
//    void testPutUserInOrg() {
//        // Mock SecurityContextHolder and Authentication
//        SecurityContext securityContext = mock(SecurityContext.class);
//        SecurityContextHolder.setContext(securityContext);
//
//        Authentication authentication = mock(Authentication.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        when(authentication.getName()).thenReturn("testuser");
//        when(authentication.isAuthenticated()).thenReturn(true);
//
//        // Mock UserRepository to return a user
//        User loggedInUser = new User();
//        loggedInUser.setEmail("testuser");
//        when(userRepository.findUserByEmail("testuser")).thenReturn(loggedInUser);
//
//        // Mock OrganisationRepository to return an organisation
//        Organisation organisation = new Organisation();
//        organisation.setOrgId(1L);
//        when(organisationRepository.findById(1L)).thenReturn(Optional.of(organisation));
//
//        // Mock UserRepository to return a user
//        User user = new User();
//        user.setUserId(2L);
//        when(userRepository.findUserByUserId(2L)).thenReturn(user);
//
//        // Invoke the method under test
//        UserReq userReq = new UserReq();
//        userReq.setUserId("2");
//        Long orgId = 1L;
//        Object response = null;
//        try {
//            response = organisationService.putUserInOrg(userReq, orgId);
//        } catch (ErrorResponse errorResponse) {
//            errorResponse.printStackTrace();
//        }
//
//        // Assertions
//        assertNotNull(response);
//        assertTrue(response instanceof SuccessResponse);
//
//        SuccessResponse successResponse = (SuccessResponse) response;
//        assertEquals("success", successResponse.getStatus());
//        assertEquals("User added to organisation successfully", successResponse.getMessage());
//
//        // Verify interactions
//        verify(userRepository, times(1)).findUserByEmail("testuser");
//        verify(organisationRepository, times(1)).findById(1L);
//        verify(userRepository, times(1)).findUserByUserId(2L);
//        verify(organisationRepository, times(1)).save(any(Organisation.class));
//    }
//
//    @Test
//    void testPutUserInOrgUnauthenticated() {
//        // Mock SecurityContextHolder and Authentication
//        SecurityContext securityContext = mock(SecurityContext.class);
//        SecurityContextHolder.setContext(securityContext);
//
//        Authentication authentication = mock(Authentication.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        when(authentication.isAuthenticated()).thenReturn(false);
//
//        // Invoke the method under test and expect UserNotVerifiedException
//        UserReq userReq = new UserReq();
//        userReq.setUserId("2");
//        Long orgId = 1L;
//        assertThrows(UserNotVerifiedException.class, () -> organisationService.putUserInOrg(userReq, orgId));
//
//        // Verify interactions
//        verify(userRepository, never()).findUserByEmail(any());
//        verify(organisationRepository, never()).findById(any());
//        verify(userRepository, never()).findUserByUserId(any());
//        verify(organisationRepository, never()).save(any());
//    }
//
//
//}
