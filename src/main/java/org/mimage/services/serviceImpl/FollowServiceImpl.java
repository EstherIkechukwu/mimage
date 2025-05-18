package org.mimage.services.serviceImpl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.mimage.data.models.User;
import org.mimage.data.repositories.UserRepository;
import org.mimage.dtos.request.FollowRequest;
import org.mimage.services.serviceInterface.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Override
    public void follow(@Valid FollowRequest followRequest) {
        validateRequest(followRequest);
        User follower = userRepository.findById(followRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("Follower not found"));
        User followed = userRepository.findById(followRequest.followerId())
                .orElseThrow(() -> new IllegalArgumentException("Followed user not found"));
        follower.getProfile().getFollowingId().add(followed.getId());
        followed.getProfile().getFollowersId().add(follower.getId());
        userRepository.save(follower);
        userRepository.save(followed);
    }

    @Override
    public void unfollow(FollowRequest followRequest) {
        validateRequest(followRequest);
        User follower = userRepository.findById(followRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("Follower not found"));
        User followed = userRepository.findById(followRequest.followerId())
                .orElseThrow(() -> new IllegalArgumentException("Followed user not found"));
        follower.getProfile().getFollowingId().remove(followed.getId());
        followed.getProfile().getFollowersId().remove(follower.getId());
        userRepository.save(follower);
        userRepository.save(followed);
    }

    private void validateRequest(FollowRequest request) {
        Set<ConstraintViolation<FollowRequest>> violations = validator.validate(request);
        if(!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }
}
