package com.innometrics.integration.app.recommendation.model;

/**
 * @author andrew, Innometrics
 */
public class User {
    private final long userId;
    private final String profileId;

    public User(long userId, String profileId) {
        this.userId = userId;
        this.profileId = profileId;
    }

    public long getUserId() {
        return userId;
    }

    public String getProfileId() {
        return profileId;
    }
}
