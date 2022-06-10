package com.sztorma.skeleton.user.dao;

import org.springframework.stereotype.Service;

import com.sztorma.skeleton.common.dao.EntityDaoImpl;
import com.sztorma.skeleton.user.entity.User;
import com.sztorma.skeleton.user.repository.UserRepository;

@Service
public class UserDaoImpl extends EntityDaoImpl<User, UserRepository> implements UserDao {

    @Override
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

}
