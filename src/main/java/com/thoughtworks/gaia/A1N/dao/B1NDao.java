package com.thoughtworks.gaia.a1n.dao;

import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import com.thoughtworks.gaia.a1n.model.B1NModel;
import org.springframework.stereotype.Component;


@Component
public class B1NDao extends BaseDaoWrapper<B1NModel> {

    public B1NDao() {
        super(B1NModel.class);
    }
}
