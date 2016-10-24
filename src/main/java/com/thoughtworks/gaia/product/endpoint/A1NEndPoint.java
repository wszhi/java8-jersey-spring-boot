package com.thoughtworks.gaia.product.endpoint;

import com.thoughtworks.gaia.product.entity.A1N;
import com.thoughtworks.gaia.product.service.A1NService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("a1ns")
@Api(value = "a1ns", description = "Access to a1n resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class A1NEndPoint {
    @Autowired
    private A1NService a1NService;

    @Path("/")
    @ApiOperation(value = "Get a1ns", response = A1N.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get a1n successfully"),
            @ApiResponse(code = 404, message = "No a1n matches")
    })
    @GET
    public Response getA1N() {
        List<A1N> a1Ns = a1NService.getAllA1Ns();
        GenericEntity<List<A1N>> entity = new GenericEntity<List<A1N>>(a1Ns) {};
        return Response.ok().entity(entity).build();
    }

    @Path("/{id}")
    @GET
    public Response getA1NById(@PathParam("id")Long id) {
        A1N a1N = a1NService.getA1NById(id);
        return Response.ok().entity(a1N).build();
    }

    @Path("/")
    @POST
    public Response addA1N(A1N a1n) {
        String url=a1NService.addA1N(a1n);
        return Response.ok().entity(url).build();
    }
    @Path("/{id}")
    @PUT
    public Response update(A1N a1N,@PathParam("id")Long id) {
        a1N.setId(id);
        String url=a1NService.updateA1N(a1N);
        return Response.ok().entity(url).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteA1N(@PathParam("id")Long id) {
        String url=a1NService.deleteA1N(id);
        return Response.ok().entity(url).build();
    }

//    @Path("/{a1n_name}/{b1n_name}")
//    @POST
//    public Response addA1Ns(@PathParam("a1n_name")String a1N,@PathParam("b1n_name")String b1N) {
//        A1N  a1N1 = new A1N();
//        a1N1.setName(a1N);
//        B1N b1N1 = new B1N();
//        b1N1.setName(b1N);
//        List b1Ns=new ArrayList<>();
//        b1Ns.add(b1N1);
//        a1N1.setB1Ns(b1Ns);
//        String url=a1NService.addA1N(a1N1);
//        return Response.ok().entity(url).build();
//    }
}
