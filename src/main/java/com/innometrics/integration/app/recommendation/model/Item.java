package com.innometrics.integration.app.recommendation.model;

import java.util.Map;

/**
 * @author andrew, Innometrics
 */
public class Item {
    private final long itemId;
    private final Map itemData;

    public Item(long itemId, Map itemData) {
        this.itemId = itemId;
        this.itemData = itemData;
    }

    public Map getItemData() {
        return itemData;
    }

    public long getItemId() {
        return itemId;
    }
}
