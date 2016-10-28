package com.thoughtworks.gaia.ann.dao;

import com.thoughtworks.gaia.ann.model.BNNModel;
import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import org.springframework.stereotype.Component;


@Component
public class BNNDao extends BaseDaoWrapper<BNNModel> {

    public BNNDao() {
        super(BNNModel.class);
    }
}
