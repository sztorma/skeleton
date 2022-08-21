package com.sztorma.skeleton.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sztorma.skeleton.user.dao.UserDao;
import com.sztorma.skeleton.user.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    @DisplayName("find user successfully")
    public void testFindUserByUsername() {
        User user = new User();
        user.setUsername("foo");
        when(userDao.findUserByUsername("foo")).thenReturn(user);
        userServiceImpl.findUserByUsername("foo");
        verify(userDao, times(1)).findUserByUsername("foo");
        assertEquals("foo", user.getUsername());
    }

    @Test
    @DisplayName("Return null when username param is null")
    public void testFindUserByUsernameParamNull() {
        User user = userServiceImpl.findUserByUsername(null);
        verify(userDao, times(0)).findUserByUsername("foo");
        assertEquals(null, user);
    }
}
