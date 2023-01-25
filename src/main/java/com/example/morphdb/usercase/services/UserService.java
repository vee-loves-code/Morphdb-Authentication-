package com.example.morphdb.usercase.services;

import com.example.morphdb.usercase.payload.request.AuthRequest;
import com.example.morphdb.usercase.payload.request.RegisterRequest;
import com.example.morphdb.usercase.payload.request.UpdateUserRequest;
import com.example.morphdb.usercase.payload.response.ApiResponse;
import com.example.morphdb.usercase.payload.response.LoginResponse;
import com.example.morphdb.usercase.payload.response.RegistrationResponse;

public interface UserService {
    ApiResponse<LoginResponse> loginUser(AuthRequest authRequest);

    ApiResponse<RegistrationResponse> registerUser(RegisterRequest registerRequest);

    ApiResponse<?> updateUser(UpdateUserRequest updateUserRequest);
}
