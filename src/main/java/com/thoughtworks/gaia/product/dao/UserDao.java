package com.thoughtworks.gaia.product.dao;

import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import com.thoughtworks.gaia.product.model.UserModel;
import org.springframework.stereotype.Component;


@Component
public class UserDao extends BaseDaoWrapper<UserModel>{
    public UserDao() {
        super(UserModel.class);
    }

}
