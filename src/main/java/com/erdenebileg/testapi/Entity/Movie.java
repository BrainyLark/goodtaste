package com.erdenebileg.testapi.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private long id;
    private double predicted;
    private String title;
    private String url;
    private String genres;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPredicted() {
        return predicted;
    }

    public void setPredicted(double predicted) {
        this.predicted = predicted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
