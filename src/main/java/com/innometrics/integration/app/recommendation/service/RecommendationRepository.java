package com.innometrics.integration.app.recommendation.service;

import com.innometrics.integration.app.recommendation.model.Recommendation;

import java.util.List;

/**
 * @author andrew, Innometrics
 */
public interface RecommendationRepository {
    public List<Recommendation> getRecommendations(long uid, int nr);

    void saveRecommendation(long uid, long iid, float preference);
}
