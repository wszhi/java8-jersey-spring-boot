package com.thoughtworks.gaia.product.dao;

import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.product.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void shouldReturnUserModelWhenUserIdIsValid() throws Exception {
        Long userId = 1L;
        UserModel userModel =new UserModel();
        userModel.setAge(22);
        userModel.setUsername("forTest");
        userDao.save(userModel);

        UserModel result = userDao.idEquals(userId).querySingle();
        assertNotNull(result);
    }
}