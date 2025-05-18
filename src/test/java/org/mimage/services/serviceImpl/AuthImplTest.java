package org.mimage.services.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mimage.data.models.Profile;
import org.mimage.data.repositories.UserRepository;
import org.mimage.dtos.request.LoginRequest;
import org.mimage.dtos.request.ProfileUpdateRequest;
import org.mimage.dtos.request.RegisterRequest;
import org.mimage.dtos.response.AuthResponse;
import org.mimage.services.serviceInterface.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthImplTest {

    @Autowired
    private Auth auth;

//    @BeforeEach
//    void setUp() {
//    }

    @Autowired
    private UserRepository userRepository;

    @Test
    void savedUser_countUser_authServicesTest(){
        RegisterRequest registerRequest = new RegisterRequest("esther18@gmail.com","password");
        AuthResponse savedUser = auth.register(registerRequest);
        assertNotNull(savedUser);
        assertEquals(4L, userRepository.count());
    }

    @Test
    void savedUser_loginServicesTest(){
        RegisterRequest registerRequest = new RegisterRequest("esther000@gmail.com","password");
        AuthResponse savedUser = auth.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest("esther000@gmail.com","password");
        AuthResponse savedUser2 = auth.login(loginRequest);
        assertNotNull(savedUser);
    }

    @Test
    void savedUser_updateProfile_profileIsUpdatedTest(){
        RegisterRequest registerRequest = new RegisterRequest("esther180@gmail.com","password");
        AuthResponse savedUser = auth.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest("esther180@gmail.com","password");
        assertNull(savedUser.profile());
        Profile profile = new Profile();
        profile.setFirstName("esther");
        profile.setLastName("beauty");
        ProfileUpdateRequest profileUpdateRequest = new ProfileUpdateRequest(savedUser.token(), savedUser.id(), profile);
        auth.updateProfile(profileUpdateRequest);
        savedUser = auth.login(loginRequest);
        assertNotNull(savedUser.profile());
    }

}