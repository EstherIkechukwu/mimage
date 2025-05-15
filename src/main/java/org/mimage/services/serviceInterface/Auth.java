package org.mimage.services.serviceInterface;

import jakarta.validation.Valid;
import org.mimage.dtos.request.LoginRequest;
import org.mimage.dtos.request.RegisterRequest;
import org.mimage.dtos.response.AuthResponse;

public interface Auth {
    AuthResponse login(@Valid LoginRequest request);
    AuthResponse register(@Valid RegisterRequest request);
}
