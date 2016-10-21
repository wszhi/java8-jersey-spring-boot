package com.thoughtworks.gaia.product.endpoint;

import com.thoughtworks.gaia.product.entity.User;
import com.thoughtworks.gaia.product.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserEndPointTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserEndPoint userEndPoint;

    @Before
    public void setUp() throws Exception {

//        userEndPoint = new UserEndPoint();
        initMocks(this);
    }

    @Test
    public void shouldReturnUserJsonDataWhenGivenCorrectUserId() throws Exception {
        Long userId = 1L;
        User user = new User("test", 21);
        when(userService.getUser(userId)).thenReturn(user);

        Response response = userEndPoint.getUser(userId);

        Response expect = Response.ok().entity(user).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }

    @Test
    public void shouldReturnUserListDataWhenAddUserSuccess() throws Exception {
        User user1 =new User("test1",21);
        User user2 =new User("test2",21);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(userService.addUserAndShowAllUsers(any())).thenReturn(users);

        Response response = userEndPoint.addUser("test2",21);

        Response expect = Response.ok().entity(users).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }
}