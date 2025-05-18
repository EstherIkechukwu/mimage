package org.mimage.services.serviceImpl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.mimage.data.models.Profile;
import org.mimage.data.models.User;
import org.mimage.data.repositories.ProfileRepository;
import org.mimage.data.repositories.UserRepository;
import org.mimage.dtos.request.LoginRequest;
import org.mimage.dtos.request.ProfileUpdateRequest;
import org.mimage.dtos.request.RegisterRequest;
import org.mimage.dtos.response.AuthResponse;
import org.mimage.services.serviceInterface.Auth;
import org.mimage.utils.JwtUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthImpl implements Auth {

    @Autowired
    private Validator validator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public AuthResponse login(@Valid LoginRequest loginRequest) {
        validateRequest(loginRequest);
        User currentUser = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if(!BCrypt.checkpw(loginRequest.password(), currentUser.getPassword()))
            throw new IllegalArgumentException("Invalid email or password");
        String token = jwtUtils.generateToken(currentUser.getId());
        return new AuthResponse(token, currentUser.getId(), currentUser.getEmail(), currentUser.getProfile());
    }

    @Override
    public AuthResponse register(@Valid RegisterRequest request) {
        validateRequest(request);
        if(userRepository.existsByEmail(request.email())) throw new RuntimeException("Email already exists");
        String hashedPassword = BCrypt.hashpw(request.password(), BCrypt.gensalt());
        User user = mapRegisterRequestToUser(request, hashedPassword);
        User savedUser = userRepository.save(user);
        String token = jwtUtils.generateToken(savedUser.getId());
        return new AuthResponse(token, savedUser.getId(), savedUser.getEmail(), savedUser.getProfile());
    }

    @Override
    public void updateProfile(@Valid ProfileUpdateRequest request) {
        validateRequest(request);
        User user = userRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        Profile savedProfile = profileRepository.save(request.profile());
        user.setProfile(savedProfile);
        userRepository.save(user);
    }

    private static User mapRegisterRequestToUser(RegisterRequest request, String hashedPassword) {
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(hashedPassword);
        return user;
    }

    private void validateRequest(RegisterRequest request) {
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        if(!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    private void validateRequest(LoginRequest request) {
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);
        if(!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    private void validateRequest(ProfileUpdateRequest request) {
        Set<ConstraintViolation<ProfileUpdateRequest>> violations = validator.validate(request);
        if(!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

}
