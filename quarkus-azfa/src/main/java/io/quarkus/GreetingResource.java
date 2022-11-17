package io.quarkus;

import io.quarkus.services.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cars")
public class GreetingResource {

    private final Logger logger = LoggerFactory.getLogger(GreetingResource.class);
    private final ResourceService resourceService;

    @Inject
    public GreetingResource(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GET
    public Response getAllCars() {
        logger.debug("Env variables: [{}]", System.getenv().toString());
        return Response.ok(resourceService.getCars()).build();
    }
}
