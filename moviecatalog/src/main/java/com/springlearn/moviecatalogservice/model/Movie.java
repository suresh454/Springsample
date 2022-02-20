package com.springlearn.moviecatalogservice.model;

public class Movie {

    private String movieId;
    private String name;
    private String movieOverview;

    public Movie() {
    }

    public Movie(String movieId, String name, String movieOverview) {
        this.movieId = movieId;
        this.name = name;
        this.movieOverview = movieOverview;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }
}
