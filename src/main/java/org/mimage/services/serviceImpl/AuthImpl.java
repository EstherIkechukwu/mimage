package org.mimage.services.serviceImpl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.mimage.data.models.User;
import org.mimage.data.repositories.UserRepository;
import org.mimage.dtos.request.LoginRequest;
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
    private JwtUtils jwtUtils;

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
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
}
