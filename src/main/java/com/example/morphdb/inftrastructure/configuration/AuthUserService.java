package com.example.morphdb.inftrastructure.configuration;

import com.example.morphdb.domain.entity.User;
import com.example.morphdb.inftrastructure.Exceptions.CustomNotFoundException;
import com.example.morphdb.inftrastructure.persistence.daoimpl.UserDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {
    @Autowired
    private final UserDaoImpl userDaoImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDaoImpl.findUserByEmail(email);
        if(user == null){
            throw new CustomNotFoundException("Wrong email");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
