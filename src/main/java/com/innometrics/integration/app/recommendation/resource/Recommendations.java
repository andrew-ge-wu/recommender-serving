package com.innometrics.integration.app.recommendation.resource;

import com.innometrics.integration.app.recommendation.model.Recommendation;
import com.innometrics.integration.app.recommendation.model.RecommendationResult;
import com.innometrics.integration.app.recommendation.model.User;
import com.innometrics.integration.app.recommendation.service.ItemRepository;
import com.innometrics.integration.app.recommendation.service.RecommendationRepository;
import com.innometrics.integration.app.recommendation.service.SingletonService;
import com.innometrics.integration.app.recommendation.service.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author andrew, Innometrics
 */
@Path("/recommendation")
public class Recommendations {

    RecommendationRepository recommender = SingletonService.getInstanceFromConfig(RecommendationRepository.class);
    ItemRepository itemRepo = SingletonService.getInstanceFromConfig(ItemRepository.class);
    UserRepository userRepo = SingletonService.getInstanceFromConfig(UserRepository.class);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RecommendationResult get(@QueryParam("profileId") String profileId, @QueryParam("nr") int nr) {
        User user = userRepo.getUser(profileId);
        List<Recommendation> recommendations = recommender.getRecommendations(userRepo.getUser(profileId).getUserId(), nr);
        RecommendationResult.ItemWrapper[] items = new RecommendationResult.ItemWrapper[recommendations.size()];
        for (int i = 0; i < recommendations.size(); i++) {
            Recommendation recommendation = recommendations.get(i);
            items[i] = new RecommendationResult.ItemWrapper(itemRepo.getItem(recommendation.getItemId()), recommendation.getPreference());
        }
        return new RecommendationResult(user, items);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(Recommendation recommendation) {
        recommender.saveRecommendation(recommendation.getUserId(), recommendation.getItemId(), recommendation.getPreference());
    }
}
