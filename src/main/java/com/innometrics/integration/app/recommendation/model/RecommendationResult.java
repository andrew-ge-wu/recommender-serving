package com.innometrics.integration.app.recommendation.model;

/**
 * @author andrew, Innometrics
 */
public class RecommendationResult {
    private final User user;
    private final ItemWrapper[] items;

    public RecommendationResult(User user, ItemWrapper[] items) {
        this.user = user;
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public ItemWrapper[] getItems() {
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

