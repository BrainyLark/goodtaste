package com.erdenebileg.testapi.utils;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Recommender {
    private long userId;
    private DataModel model;
    private UserSimilarity similarity;
    private UserNeighborhood neighborhood;
    private static final String ratingPath = "ratings_small.csv";
    private final double threshold = 0.5;

    public Recommender(long userId) {
        try {
            this.userId = userId;
            model = new FileDataModel(new File(ratingPath));
            similarity = new PearsonCorrelationSimilarity(model);
            neighborhood = new ThresholdUserNeighborhood(threshold, similarity, model);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (TasteException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<RecommendedItem> getRecommendedItems() {
        try {
            UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
            return recommender.recommend(userId, 20);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return null;
    }

}
