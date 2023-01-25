package com.example.morphdb.inftrastructure.persistence.daoimpl;

import com.example.morphdb.domain.dao.UserDao;
import com.example.morphdb.domain.entity.User;
import com.example.morphdb.inftrastructure.persistence.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class UserDaoImpl extends CrudDaoImpl<User, Long> implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
