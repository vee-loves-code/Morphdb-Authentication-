package com.example.morphdb.usercase.services.serviceImpl;

import com.example.morphdb.domain.entity.User;
import com.example.morphdb.inftrastructure.Exceptions.CustomNotFoundException;
import com.example.morphdb.inftrastructure.Exceptions.ResourceNotFoundException;
import com.example.morphdb.inftrastructure.configuration.AuthUserService;
import com.example.morphdb.inftrastructure.configuration.JwtService;
import com.example.morphdb.inftrastructure.persistence.daoimpl.UserDaoImpl;
import com.example.morphdb.usercase.payload.request.AuthRequest;
import com.example.morphdb.usercase.payload.request.RegisterRequest;
import com.example.morphdb.usercase.payload.response.ApiResponse;
import com.example.morphdb.usercase.payload.response.LoginResponse;
import com.example.morphdb.usercase.payload.response.RegistrationResponse;
import com.example.morphdb.usercase.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao;

    private JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    private final AuthUserService authUserService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<LoginResponse> loginUser(AuthRequest authRequest) {
        Authentication auth;
        try {
            auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest
                            .getEmail(), authRequest.getPassword()));
        }catch (org.springframework.security.core.AuthenticationException ex) {
            log.error(ex.getMessage());
            throw new ResourceNotFoundException("Invalid username or password");
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        User appUserEntity = userDao.findUserByEmail(authRequest.getEmail());
        if(appUserEntity == null){
            throw new CustomNotFoundException("User does not exist");
        }
        String token = "Bearer " + jwtService
                .generateToken(new org.springframework.security.core.userdetails
                        .User(authRequest.getEmail(), authRequest.getPassword(), new ArrayList<>()));
        return new ApiResponse<>("Success", LocalDateTime.now(), new LoginResponse(appUserEntity.getId(),
                appUserEntity.getEmail(), token));

    }

    @Override
    public ApiResponse<RegistrationResponse> registerUser(RegisterRequest registerRequest) {
        User newUser = modelMapper.map(registerRequest, User.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userDao.saveRecord(newUser);
        RegistrationResponse data = new RegistrationResponse(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword());
        return new ApiResponse<>("success", LocalDateTime.now(), data);
    }
}
