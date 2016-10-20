package com.thoughtworks.gaia.product.endpoint;

import com.thoughtworks.gaia.product.entity.Product;
import com.thoughtworks.gaia.product.entity.User;
import com.thoughtworks.gaia.product.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("user")
@Api(value = "user", description = "Access to user resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserEndPoint {
    @Autowired
    UserService userService;

    @Path("/{user_id}")
    @ApiOperation(value = "Get user by id", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user successfully"),
            @ApiResponse(code = 404, message = "No user matches given id")
    })
    @GET
    public Response getUser(@PathParam("user_id") Long userId) {
        User user = userService.getUser(userId);
        return Response.ok().entity(user).build();
    }

    @Path("/add/{user_name}/{age}")
    @ApiOperation(value = "Get user by id", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user successfully"),
            @ApiResponse(code = 404, message = "No user matches given id")
    })
    @GET
    public Response addUser(@PathParam("user_name") String userName,@PathParam("age") int age) {
        User user = new User(userName,age);
        List<User> userList = userService.addUserAndShowAllUsers(user);
        return Response.ok().entity(userList).build();
    }
}
