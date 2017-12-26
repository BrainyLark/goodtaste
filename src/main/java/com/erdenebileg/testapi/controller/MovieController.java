package com.erdenebileg.testapi.controller;

import com.erdenebileg.testapi.Entity.Contribution;
import com.erdenebileg.testapi.Entity.Movie;
import com.erdenebileg.testapi.Entity.request.AddMovieRequest;
import com.erdenebileg.testapi.Entity.response.ContributionResponse;
import com.erdenebileg.testapi.repository.MovieRepository;
import com.erdenebileg.testapi.utils.DatabaseHelper;
import com.erdenebileg.testapi.utils.Recommender;
import com.erdenebileg.testapi.utils.Utilizable;
import org.apache.mahout.cf.taste.common.NoSuchUserException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    private MovieRepository movieRepository;
    private DatabaseHelper dbhelper;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        dbhelper = new DatabaseHelper();
    }

    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    public List<Movie> findRecommendations(@PathVariable String id) {
        List<Movie> movies = new ArrayList<>();
        Recommender recommender = new Recommender(Long.valueOf(id));
        try {
            List<RecommendedItem> items = recommender.getRecommendedItems();
            for (RecommendedItem item : items) {
                Movie temp = new Movie();
                temp.setId(item.getItemID());
                temp.setPredicted((double) item.getValue());
                ResultSet rs = (dbhelper.getMovieData(item.getItemID()));
                try {
                    if (rs.next()) {
                        temp.setTitle(rs.getString("title"));
                        temp.setUrl(rs.getString("url"));
                        temp.setGenres(rs.getString("genres"));
                    }
                } catch (SQLException e) {
                    temp.setTitle("no data");
                    temp.setUrl("no data");
                    temp.setGenres("no data");
                }
                movies.add(temp);
            }
        } catch (NullPointerException e) {

        }
        return movies;
    }

    @RequestMapping(value = "/contribute", method = RequestMethod.POST)
    public ContributionResponse insertRating(@RequestBody AddMovieRequest addMovieRequest) {
        Contribution contribution = new Contribution();
        contribution.setUserId(addMovieRequest.getUserId());
        contribution.setMovieId(addMovieRequest.getItemId());
        contribution.setRating(addMovieRequest.getRating());
        ContributionResponse response = new ContributionResponse();
        if(Utilizable.insertRowData(contribution)) {
            response.setSuccess(true);
            response.setDescription("Таны хувь нэмрийг амжилттай бүртгэлээ");
        } else {
            response.setSuccess(false);
            response.setDescription("Unlucky hahaha");
        }
        return response;
    }

    @RequestMapping(value = "/search/{movieName}", method = RequestMethod.GET)
    public List<Movie> searchByMovieName(@PathVariable String movieName) {
        ResultSet rs = dbhelper.getMoviesByName(movieName);
        try {
            List<Movie> movieList = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getLong("movieid"));
                movie.setPredicted(3.5);
                movie.setTitle(rs.getString("title"));
                movie.setGenres(rs.getString("genres"));
                movie.setUrl(rs.getString("url"));
                movieList.add(movie);
            }
            return movieList;
        } catch(SQLException e) {

        }
        return null;
    }

}