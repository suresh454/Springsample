package com.springlearn.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springlearn.moviecatalogservice.model.CatalogItem;
import com.springlearn.moviecatalogservice.model.Movie;
import com.springlearn.moviecatalogservice.model.Rating;
import com.springlearn.moviecatalogservice.model.UserRating;
import com.springlearn.moviecatalogservice.services.MovieInfo;
import com.springlearn.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        UserRating ratings = userRatingInfo.getUserRating(userId);

        return ratings.getRatings().stream().map(rating -> {
            Movie movie = movieInfo.getMovieInfo(rating);
            return  new CatalogItem(movie.getName(), movie.getMovieOverview(), rating.getRating());
        }
        ).collect(Collectors.toList());


    }





}
