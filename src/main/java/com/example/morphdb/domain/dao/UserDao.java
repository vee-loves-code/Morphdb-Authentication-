package com.example.morphdb.domain.dao;

import com.example.morphdb.domain.entity.User;

public interface UserDao extends CrudDao<User, Long>{
    User findUserByEmail(String email);
}
