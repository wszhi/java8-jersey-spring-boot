package com.thoughtworks.gaia.ann.endpoint;

import com.thoughtworks.gaia.ann.entity.ANN;
import com.thoughtworks.gaia.ann.service.ANNService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("anns")
@Api(value = "anns", description = "Access to ann resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ANNEndPoint {
    @Autowired
    private ANNService aNNService;

    @Path("/")
    @GET
    public Response getANN() {
        List<ANN> anns = aNNService.getAllANNs();
        GenericEntity<List<ANN>> entity = new GenericEntity<List<ANN>>(anns) {};
        return Response.ok().entity(entity).build();
    }

    @Path("/{id}")
    @GET
    public Response getANNByID(@PathParam("id")Long id) {
        ANN ann = aNNService.getANNById(id);
        return Response.ok().entity(ann).build();
    }

    @Path("/")
    @POST
    public Response addANN(ANN ann) {
        String url = aNNService.addANN(ann);
        return Response.ok().entity(url).build();
    }
    @Path("/{id}")
    @PUT
    public Response updateANN(@PathParam("id")Long id,ANN ann) {
        ann.setId(id);
        String url = aNNService.updateANN(ann);
        return Response.ok().entity(url).build();
    }
    @Path("/{id}")
    @DELETE
    public Response deleteANN(@PathParam("id")Long id) {
        String url = aNNService.deleteANN(id);
        return Response.ok().entity(url).build();
    }
}
