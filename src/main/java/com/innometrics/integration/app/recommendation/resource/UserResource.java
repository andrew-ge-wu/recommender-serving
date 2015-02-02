package com.innometrics.integration.app.recommendation.resource;

import com.innometrics.integration.app.recommendation.model.User;
import com.innometrics.integration.app.recommendation.service.SingletonService;
import com.innometrics.integration.app.recommendation.service.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author andrew, Innometrics
 */
@Path("/user")
public class UserResource {
    UserRepository userRepo = SingletonService.getInstanceFromConfig(UserRepository.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@QueryParam("profileId") String profileId) {
        return userRepo.getUser(profileId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(User user) {
        userRepo.setUser(user);
    }
}
