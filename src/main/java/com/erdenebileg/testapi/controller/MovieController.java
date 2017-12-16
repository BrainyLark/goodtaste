package com.erdenebileg.testapi.controller;

import com.erdenebileg.testapi.Entity.Movie;
import com.erdenebileg.testapi.repository.MovieRepository;
import com.erdenebileg.testapi.utils.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    private MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    public List<Movie> findRecommendations(@PathVariable String id) {
        List<Movie> movies = new ArrayList<>();
        System.out.println("NibABBABABABBABABBABA: " + id);
        Recommender recommender = new Recommender(Long.valueOf(id));
        List<RecommendedItem> items = recommender.getRecommendedItems();
        for (RecommendedItem item : items) {
            Movie temp = new Movie();
            temp.setId(item.getItemID());
            temp.setPredicted((double) item.getValue());
            movies.add(temp);
        }
        return movies;
    }
}
