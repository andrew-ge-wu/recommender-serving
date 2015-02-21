package com.innometrics.integration.app.recommendation.service.impl;

import com.innometrics.integration.app.recommendation.model.Item;
import com.innometrics.integration.app.recommendation.service.ItemRepository;
import org.apache.commons.lang.SerializationUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author andrew, Innometrics
 */
public class MemoryItemRepository implements ItemRepository<UUID> {
    Map<UUID, Item> storage = new ConcurrentHashMap<>();

    @Override
    public UUID setItem(Item item) {
        UUID id = UUID.nameUUIDFromBytes(SerializationUtils.serialize(item));
        storage.put(id, item);
        return id;
    }

    @Override
    public Item getItem(UUID itemId) {
        return storage.get(itemId);
    }
}
