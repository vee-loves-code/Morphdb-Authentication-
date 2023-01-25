package com.example.morphdb.application.authcontroller;

import com.example.morphdb.usercase.payload.request.AuthRequest;
import com.example.morphdb.usercase.payload.response.ApiResponse;
import com.example.morphdb.usercase.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class Authcontroller {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> generateToken(@RequestBody @Valid AuthRequest authRequest)  {
        return new ResponseEntity<>(userService.loginUser(authRequest), HttpStatus.CREATED);
    }
}
