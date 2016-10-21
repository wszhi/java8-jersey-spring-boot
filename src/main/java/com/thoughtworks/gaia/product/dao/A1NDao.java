package com.thoughtworks.gaia.product.dao;

import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import com.thoughtworks.gaia.product.model.A1NModel;
import org.springframework.stereotype.Component;


@Component
public class A1NDao extends BaseDaoWrapper<A1NModel> {

    public A1NDao() {
        super(A1NModel.class);
    }
}
