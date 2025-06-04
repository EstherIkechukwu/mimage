package org.mimage.services.serviceImpl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.mimage.data.models.Post;
import org.mimage.data.models.User;
import org.mimage.data.repositories.PostRepository;
import org.mimage.data.repositories.UserRepository;
import org.mimage.dtos.request.PostRequest;
import org.mimage.services.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createPost(@Valid PostRequest postRequest) {
        validateRequest(postRequest);
        User currentUser = userRepository.findById(postRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        Post post = postRequest.post();
        post.setAuthor(currentUser);
        return postRepository.save(post);
    }

    private void validateRequest(PostRequest request) {
        Set<ConstraintViolation<PostRequest>> violations = validator.validate(request);
        if(!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    @Override
    public void deletePost(String userId, String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        if (!post.getAuthor().getId().equals(userId)) {
            throw new SecurityException("You can only delete your own posts");
        }
        postRepository.delete(post);
    }
}
