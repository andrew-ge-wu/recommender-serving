package com.innometrics.integration.app.recommendation.service.impl;

import com.innometrics.integration.app.recommendation.model.User;
import com.innometrics.integration.app.recommendation.service.UserRepository;

/**
 * @author andrew, Innometrics
 */
public class MemoryUserRepository implements UserRepository {
    @Override
    public void setUser(User user) {
        //TODO:To be fixed
    }

    @Override
    public User getUser(long userId) {
        return null;  //TODO:To be fixed
    }

    @Override
    public User getUser(String profileId) {
        return null;  //TODO:To be fixed
    }
}
