package com.thoughtworks.gaia.product.dao;

import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.product.model.A1NModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class A1NDaoTest {
    @Autowired
    private A1NDao a1NDao;

    @Test
    public void shouldReturnUserModelWhenUserIdIsValid() throws Exception {
        A1NModel a1NModel =new A1NModel();
        a1NModel.setName("aa");
        a1NDao.save(a1NModel);

        List<A1NModel> result = a1NDao.where().queryList();
        assertNotNull(result);
    }
}