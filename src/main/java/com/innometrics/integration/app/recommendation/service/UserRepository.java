package com.innometrics.integration.app.recommendation.service;

import com.innometrics.integration.app.recommendation.model.User;

/**
 * @author andrew, Innometrics
 */
public interface UserRepository {
    void setUser(User user);

    User getUser(long userId);

    User getUser(String profileId);
}
