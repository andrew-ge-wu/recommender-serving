package com.innometrics.integration.app.recommendation.service.impl;

import com.innometrics.integration.app.recommendation.model.Item;
import com.innometrics.integration.app.recommendation.service.ItemRepository;

/**
 * @author andrew, Innometrics
 */
public class MemoryItemRepository implements ItemRepository {
    @Override
    public void setItem(Item item) {
        //TODO:To be fixed
    }

    @Override
    public Item getItem(long itemId) {
        return null;  //TODO:To be fixed
    }
}
