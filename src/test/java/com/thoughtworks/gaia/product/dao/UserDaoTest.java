package com.thoughtworks.gaia.product.dao;

import com.thoughtworks.gaia.product.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    @InjectMocks
    UserDao userDao;
    @Mock
    EntityManager em;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDao();
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
//        EntityManager em=emf.createEntityManager();
        userDao.injectEntityManager(em);
    }

    @Test
    public void shouldReturnUserModelWhenUserIdIsValid() throws Exception {
        Long userId = 1L;
        UserModel userModel =new UserModel();
        userModel.setAge(22);
        userModel.setUsername("forTest");
//        userDao.save(userModel);
//
//        UserModel result = userDao.idEquals(userId).querySingle();
//        assertNotNull(result);
    }
}