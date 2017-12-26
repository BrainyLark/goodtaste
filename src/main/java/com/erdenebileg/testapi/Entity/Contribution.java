package com.erdenebileg.testapi.Entity;

public class Contribution {
    private long userId;
    private long movieId;
    private double rating;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String toString() {
        return this.getUserId() + "," + this.getMovieId() + "," + this.getRating() + "\n";
    }
}
