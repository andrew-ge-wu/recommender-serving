package com.innometrics.integration.app.recommendation.service;

import com.innometrics.integration.app.recommendation.model.Item;

/**
 * @author andrew, Innometrics
 */
public interface ItemRepository<T extends Comparable<T>> {
    T setItem(Item item);

    Item getItem(T itemId);
}
