package com.innometrics.integration.app.recommendation.model;

import java.util.Collection;

/**
 * @author andrew, Innometrics
 */
public class RecommendationResult {
    private  String profileId;
    private  Collection<ItemWrapper> items;

    public RecommendationResult() {
    }

    public RecommendationResult(String profileId, Collection<ItemWrapper> items) {
        this.profileId = profileId;
        this.items = items;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public void setItems(Collection<ItemWrapper> items) {
        this.items = items;
    }

    public String getProfileId() {
        return profileId;
    }

    public Collection<ItemWrapper> getItems() {
        return items;
    }

    public static class ItemWrapper {
        private final float preference;
        private final Item item;

        public ItemWrapper(Item item, float preference) {
            this.item = item;
            this.preference = preference;
        }

        public Item getItem() {
            return item;
        }

        public float getPreference() {
            return preference;
        }
    }
}

