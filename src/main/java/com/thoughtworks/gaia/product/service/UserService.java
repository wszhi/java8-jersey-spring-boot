package com.thoughtworks.gaia.product.service;

import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.product.UserMap;
import com.thoughtworks.gaia.product.dao.UserDao;
import com.thoughtworks.gaia.product.entity.User;
import com.thoughtworks.gaia.product.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional
public class UserService implements Loggable {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMap userMap;

//    为了测试还可以这么改
//    private UserDao userDao;
//    private UserMap userMap;
//    @Autowired
//    public UserService(UserDao userDao,UserMap userMap) {
//        this.userDao = userDao;
//        this.userMap = userMap;
//    }

    public User getUser(Long userId) {
        UserModel userModel = userDao.idEquals(userId).querySingle();
        if (userModel == null) {
            error("User not found with id: " + userId);
            throw new NotFoundException();
        }

        return userMap.map(userModel, User.class);
    }

    public List addUserAndShowAllUsers(User user) {
        return null;
    }
}
