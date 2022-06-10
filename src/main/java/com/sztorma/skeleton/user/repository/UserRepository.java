package com.sztorma.skeleton.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sztorma.skeleton.user.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

}
