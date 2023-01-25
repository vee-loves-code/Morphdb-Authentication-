package com.example.morphdb.utils;

import com.example.morphdb.domain.entity.User;
import com.example.systemspecstask.model.User;
import com.example.systemspecstask.usecase.payload.request.UpdateProfileRequest;
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

    public User updateUserMapper(UpdateProfileRequest updateUserProfileRequest, User user) {
        if (updateUserProfileRequest.getCompanyName() != null) {
            user.setCompanyName(updateUserProfileRequest.getCompanyName());
        }
        if (updateUserProfileRequest.getAddress() != null) {
            user.setAddress(updateUserProfileRequest.getAddress());
        }
        if (updateUserProfileRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updateUserProfileRequest.getPassword()));
        }
        if (updateUserProfileRequest.getBusinessType() != null) {
            user.setPassword(updateUserProfileRequest.getBusinessType());
        }
        return user;
    }


}
