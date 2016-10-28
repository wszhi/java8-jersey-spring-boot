package com.thoughtworks.gaia.ann.dao;

import com.thoughtworks.gaia.ann.model.ANNModel;
import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import org.springframework.stereotype.Component;


@Component
public class ANNDao extends BaseDaoWrapper<ANNModel> {

    public ANNDao() {
        super(ANNModel.class);
    }
}
