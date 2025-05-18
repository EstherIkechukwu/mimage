package org.mimage.controllers;

import jakarta.validation.Valid;
import org.mimage.dtos.request.FollowRequest;
import org.mimage.services.serviceInterface.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticated/users")
public class FollowerController {

    @Autowired
    private FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<?> follow(@Valid @RequestBody FollowRequest followRequest) {
        followService.follow(followRequest);
        return ResponseEntity.ok("Followed");
    }

    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollow(@Valid @RequestBody FollowRequest followRequest) {
        followService.unfollow(followRequest);
        return ResponseEntity.ok("Unfollowed");
    }
}
