package com.innometrics.integration.app.recommendation.model;

/**
 * @author andrew, Innometrics
 */
public class Recommendation {
    private final long userId;
    private final long itemId;
    private final float preference;

    public Recommendation(long userId, long itemId, float preference) {
        this.userId = userId;
        this.itemId = itemId;
        this.preference = preference;
    }

    public long getUserId() {
        return userId;
    }

    public long getItemId() {
        return itemId;
    }

    public float getPreference() {
        return preference;
    }
}
