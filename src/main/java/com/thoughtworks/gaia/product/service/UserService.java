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


@Component
@Transactional
public class UserService implements Loggable {
    @Autowired
    UserDao userDao;
    @Autowired
    UserMap userMap;

    public User getUser(Long userId) {
        UserModel userModel = userDao.idEquals(userId).querySingle();
        if (userModel == null) {
            error("User not found with id: " + userId);
            throw new NotFoundException();
        }

        return userMap.map(userModel, User.class);
    }
}
