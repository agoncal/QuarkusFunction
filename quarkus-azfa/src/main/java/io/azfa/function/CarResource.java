package io.azfa.function;

import io.azfa.function.dto.CarDto;
import io.azfa.function.services.CarService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cars")
public class CarResource {

    @Inject
    Logger logger;
    private final CarService carService;

    @Inject
    public CarResource(CarService carService) {
        this.carService = carService;
    }

    /**
     * curl 'localhost:8080/api/cars'
     */
    @GET
    public Response getAllCars() {
        List<CarDto> cars = carService.getCars();
        logger.info("Getting all cars: " + cars.size());
        return Response.ok(cars).build();
    }

    /**
     * curl 'localhost:8080/api/cars/hello'
     */
    @POST
    public Response persistCar(CarDto carDto) {
        logger.info("Persisting a car");
        carService.persistCar(carDto);
        return Response.status(CREATED).build();
    }

    /**
     * curl 'localhost:8080/api/cars/hello'
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        logger.info("Returning Hello");
        return "hello jaxrs";
    }
}
