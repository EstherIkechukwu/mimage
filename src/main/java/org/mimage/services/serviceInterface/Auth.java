package org.mimage.services.serviceInterface;

import org.mimage.dtos.request.LoginRequest;
import org.mimage.dtos.request.RegisterRequest;
import org.mimage.dtos.response.AuthResponse;

public interface Auth {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
