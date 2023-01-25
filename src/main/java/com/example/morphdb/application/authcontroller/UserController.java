package com.example.morphdb.application.authcontroller;

import com.example.morphdb.usercase.payload.request.RegisterRequest;
import com.example.morphdb.usercase.payload.request.UpdateUserRequest;
import com.example.morphdb.usercase.payload.response.ApiResponse;
import com.example.morphdb.usercase.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;
    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse<?>> registerUser(@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
        return new ResponseEntity<>(userService.registerUser(registerRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "update")
    public ResponseEntity<ApiResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest){

        return new ResponseEntity<>(userService.updateUser(updateUserRequest), HttpStatus.OK);
    }
}
