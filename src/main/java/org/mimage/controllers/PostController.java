package org.mimage.controllers;

import jakarta.validation.Valid;
import org.mimage.data.models.Post;
import org.mimage.dtos.request.PostRequest;
import org.mimage.services.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticated")
public class PostController {

    @Autowired
    private UserService  userService;

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(userService.createPost(postRequest));
    }
}
