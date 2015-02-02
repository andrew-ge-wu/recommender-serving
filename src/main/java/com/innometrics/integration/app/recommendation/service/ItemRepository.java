package com.innometrics.integration.app.recommendation.service;

import com.innometrics.integration.app.recommendation.model.Item;

/**
 * @author andrew, Innometrics
 */
public interface ItemRepository {
    void setItem(Item item);

    Item getItem(long itemId);
}
