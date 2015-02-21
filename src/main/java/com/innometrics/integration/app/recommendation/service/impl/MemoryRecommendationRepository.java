package com.innometrics.integration.app.recommendation.service.impl;

import com.innometrics.integration.app.recommendation.model.Recommendation;
import com.innometrics.integration.app.recommendation.model.RecommendationResult;
import com.innometrics.integration.app.recommendation.service.ItemRepository;
import com.innometrics.integration.app.recommendation.service.RecommendationRepository;
import com.innometrics.integration.app.recommendation.service.SingletonService;

import java.util.*;

/**
 * @author andrew, Innometrics
 */
public class MemoryRecommendationRepository implements RecommendationRepository {
    Map<String, Map<Comparable, Float>> storage = new HashMap<>();
    ItemRepository itemRepo = SingletonService.getInstanceFromConfig(ItemRepository.class);

    @Override
    public RecommendationResult getRecommendations(String profileId, int nr) {
        Map<Comparable, Float> data = storage.get(profileId);
        Collection<RecommendationResult.ItemWrapper> items = new ArrayList<>(nr);
        if (data != null) {
            TreeMap<Comparable, Float> sorted = sortByValue(data);
            int count = 0;
            for (Comparable eachId : sorted.navigableKeySet()) {
                items.add(new RecommendationResult.ItemWrapper(itemRepo.getItem(eachId), data.get(eachId)));
                count++;
                if (count >= nr) {
                    break;
                }
            }
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
