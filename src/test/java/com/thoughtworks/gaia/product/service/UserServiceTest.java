package com.thoughtworks.gaia.product.service;

import com.exmertec.yaz.core.BasicCommandBuilder;
import com.thoughtworks.gaia.product.UserMap;
import com.thoughtworks.gaia.product.dao.UserDao;
import com.thoughtworks.gaia.product.entity.User;
import com.thoughtworks.gaia.product.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserMap userMap;
    @Mock
    UserDao userDao;
    @InjectMocks
    UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService();
        initMocks(this);
    }

    @Test
    public void shouldReturnUserWhenIdIsValid() throws Exception {
        Long userId=1L;
        User user = new User("test",21);
        UserModel userModel = new UserModel();
        BasicCommandBuilder builder =mock(BasicCommandBuilder.class);
        when(userDao.idEquals(userId)).thenReturn(builder);
        when(builder.querySingle()).thenReturn(userModel);
        when(userMap.map(userModel,User.class)).thenReturn(user);

        User result=userService.getUser(userId);

        verify(userMap).map(userModel,User.class);
        assertEquals(user,result);
    }

    @Test
    public void shouldReturn() throws Exception {

    }
}