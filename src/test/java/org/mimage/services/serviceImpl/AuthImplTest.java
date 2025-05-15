package org.mimage.services.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mimage.data.repositories.UserRepository;
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
        RegisterRequest registerRequest = new RegisterRequest("esther345612@gmail.com","password");
        AuthResponse savedUser = auth.register(registerRequest);
        assertNotNull(savedUser);
        assertEquals(4L, userRepository.count());

    }

}