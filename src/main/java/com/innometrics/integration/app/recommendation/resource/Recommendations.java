package com.innometrics.integration.app.recommendation.resource;

import com.innometrics.integration.app.recommendation.model.Recommendation;
import com.innometrics.integration.app.recommendation.model.RecommendationResult;
import com.innometrics.integration.app.recommendation.service.RecommendationRepository;
import com.innometrics.integration.app.recommendation.service.SingletonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author andrew, Innometrics
 */
@Path("/recommendation")
public class Recommendations {

    RecommendationRepository recommender = SingletonService.getInstanceFromConfig(RecommendationRepository.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RecommendationResult get(@QueryParam("profileId") String profileId, @QueryParam("nr") int nr) {
        return recommender.getRecommendations(profileId, nr);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(Recommendation recommendation) {
        recommender.saveRecommendation(recommendation);
        return Response.accepted().build();
    }
}
