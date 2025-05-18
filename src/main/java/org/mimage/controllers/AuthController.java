package org.mimage.controllers;

import jakarta.validation.Valid;
import org.mimage.data.models.Profile;
import org.mimage.dtos.request.LoginRequest;
import org.mimage.dtos.request.ProfileUpdateRequest;
import org.mimage.dtos.request.RegisterRequest;
import org.mimage.dtos.response.AuthResponse;
import org.mimage.services.serviceInterface.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private Auth auth;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(auth.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(auth.login(loginRequest));
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@Valid @RequestBody ProfileUpdateRequest profileUpdateRequest){
        auth.updateProfile(profileUpdateRequest);
        return ResponseEntity.ok("Profile updated Successfully");
    }

}
