package com.sztorma.skeleton.user.service;

import com.sztorma.skeleton.user.entity.User;

public interface UserService {

    User findUserByUsername(String username);

}
