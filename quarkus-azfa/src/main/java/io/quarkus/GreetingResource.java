package io.quarkus;

import io.quarkus.dto.CarDto;
import io.quarkus.services.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

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
        logger.info("Getting all cars");
        return Response.ok(resourceService.getCars()).build();
    }

    @POST
    public Response persistCar(CarDto carDto) {
        logger.info("Persisting a car");
        resourceService.persistCar(carDto);
        return Response.status(CREATED).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        logger.info("Returning Hello");
        return "hello jaxrs";
    }
}
