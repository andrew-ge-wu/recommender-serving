package com.innometrics.integration.app.recommendation.service.impl;

import com.innometrics.integration.app.recommendation.model.Recommendation;
import com.innometrics.integration.app.recommendation.model.RecommendationResult;
import com.innometrics.integration.app.recommendation.service.ItemRepository;
import com.innometrics.integration.app.recommendation.service.RecommendationRepository;
import com.innometrics.integration.app.recommendation.service.SingletonService;
import jersey.repackaged.com.google.common.cache.Cache;
import jersey.repackaged.com.google.common.cache.CacheBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author andrew, Innometrics
 */
public class MemoryRecommendationRepository implements RecommendationRepository {
    Map<String, Map<Comparable, Float>> storage = new HashMap<>();
    ItemRepository itemRepo = SingletonService.getInstanceFromConfig(ItemRepository.class);
    Cache<String, List<RecommendationResult.ItemWrapper>> resultCache;

    public MemoryRecommendationRepository() {
        resultCache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(1, TimeUnit.HOURS).build();
    }

    @Override
    public RecommendationResult getRecommendations(String profileId, int nr) {
        Map<Comparable, Float> data = storage.get(profileId);
        Collection<RecommendationResult.ItemWrapper> items = new ArrayList<>(nr);
        if (data != null) {
            List<RecommendationResult.ItemWrapper> cached = resultCache.getIfPresent(profileId);
            if (cached == null) {
                List<RecommendationResult.ItemWrapper> toCache = new ArrayList<>(nr);
                TreeMap<Comparable, Float> sorted = sortByValue(data);
                for (Comparable eachId : sorted.navigableKeySet()) {
                    toCache.add(new RecommendationResult.ItemWrapper(itemRepo.getItem(eachId), data.get(eachId)));
                }
                resultCache.put(profileId, toCache);
            }
            items = resultCache.getIfPresent(profileId).subList(0, nr - 1);
        }
        return new RecommendationResult(profileId, items);

    }

    @Override
    public void saveRecommendation(Recommendation recommendation) {
        Comparable iid = itemRepo.setItem(recommendation.getItem());
        if (!storage.containsKey(recommendation.getProfileId())) {
            storage.put(recommendation.getProfileId(), new HashMap<Comparable, Float>());
        }
        storage.get(recommendation.getProfileId()).put(iid, recommendation.getPreference());
        resultCache.invalidate(recommendation.getProfileId());
    }

    private TreeMap<Comparable, Float> sortByValue(Map<Comparable, Float> map) {
        ValueComparator vc = new ValueComparator(map);
        TreeMap<Comparable, Float> sortedMap = new TreeMap<>(vc);
        sortedMap.putAll(map);
        return sortedMap;
    }

    class ValueComparator implements Comparator<Comparable> {

        Map<Comparable, Float> map;

        public ValueComparator(Map<Comparable, Float> base) {
            this.map = base;
        }

        public int compare(Comparable a, Comparable b) {
            if (map.get(a) >= map.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
}
