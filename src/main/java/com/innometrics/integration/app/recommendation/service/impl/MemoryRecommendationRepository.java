package com.innometrics.integration.app.recommendation.service.impl;

import com.innometrics.integration.app.recommendation.model.Recommendation;
import com.innometrics.integration.app.recommendation.service.RecommendationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew, Innometrics
 */
public class MemoryRecommendationRepository implements RecommendationRepository {
    @Override
    public List<Recommendation> getRecommendations(long uid, int nr) {
        return new ArrayList<>();
    }

    @Override
    public void saveRecommendation(long uid, long iid, float preference) {

    }
}
