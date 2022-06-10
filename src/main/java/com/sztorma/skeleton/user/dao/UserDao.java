package com.sztorma.skeleton.user.dao;

import com.sztorma.skeleton.common.dao.EntityDao;
import com.sztorma.skeleton.user.entity.User;

public interface UserDao extends EntityDao<User> {

    User findUserByUsername(String username);

}
