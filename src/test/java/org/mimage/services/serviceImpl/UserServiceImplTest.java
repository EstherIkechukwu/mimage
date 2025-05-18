package org.mimage.services.serviceImpl;

import org.junit.jupiter.api.Test;
import org.mimage.data.models.Post;
import org.mimage.dtos.request.LoginRequest;
import org.mimage.dtos.request.PostRequest;
import org.mimage.dtos.response.AuthResponse;
import org.mimage.services.serviceInterface.Auth;
import org.mimage.services.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private Auth auth;

    @Test
    void createPost_postServiceTest() {
        LoginRequest loginRequest = new LoginRequest("esther000@gmail.com","password");
        AuthResponse savedUser2 = auth.login(loginRequest);
        Post post = new Post();
        post.setTitle("title");
        PostRequest postRequest = new PostRequest(savedUser2.token(), savedUser2.id(), post);
        Post createdPost = userService.createPost(postRequest);
        assertNotNull(createdPost);
    }
}