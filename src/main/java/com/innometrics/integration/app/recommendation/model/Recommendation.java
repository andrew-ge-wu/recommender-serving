package com.innometrics.integration.app.recommendation.model;

/**
 * @author andrew, Innometrics
 */
public class Recommendation {
    private float preference;
    private Item item;
    private String profileId;

    public Recommendation() {
    }


    public void setPreference(float preference) {
        this.preference = preference;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public float getPreference() {
        return preference;
    }

    public Item getItem() {
        return item;
    }

    public String getProfileId() {
        return profileId;
    }
}
