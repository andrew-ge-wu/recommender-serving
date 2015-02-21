package com.innometrics.integration.app.recommendation.service;

import com.innometrics.integration.app.recommendation.model.Recommendation;
import com.innometrics.integration.app.recommendation.model.RecommendationResult;

/**
 * @author andrew, Innometrics
 */
public interface RecommendationRepository {
    public RecommendationResult getRecommendations(String profileId, int nr);

    void saveRecommendation(Recommendation recommendation);
}
