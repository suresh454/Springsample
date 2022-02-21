package com.springlearn.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springlearn.moviecatalogservice.model.Movie;
import com.springlearn.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMovieInfo")
    public Movie getMovieInfo(Rating rating) {
        return restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
    }

    public Movie getFallbackMovieInfo(Rating rating) {
        return new Movie("0","0", null);
    }
}
