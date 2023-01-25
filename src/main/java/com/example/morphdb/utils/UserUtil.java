package com.example.morphdb.utils;

import com.example.morphdb.domain.entity.User;
import com.example.morphdb.usercase.payload.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final PasswordEncoder passwordEncoder;

    public String getAuthenticatedUserEmail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    public User updateUserMapper(UpdateUserRequest updateUserRequest, User user) {
        if (updateUserRequest.getFirstname() != null) {
            user.setFirstName(updateUserRequest.getFirstname());
        }
        if (updateUserRequest.getLastname() != null) {
           user.setLastName(updateUserRequest.getLastname());
        }
        if (updateUserRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        }
        return user;
    }


}
