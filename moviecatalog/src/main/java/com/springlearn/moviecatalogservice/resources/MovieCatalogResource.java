package com.springlearn.moviecatalogservice.resources;

import com.springlearn.moviecatalogservice.model.CatalogItem;
import com.springlearn.moviecatalogservice.model.Movie;
import com.springlearn.moviecatalogservice.model.Rating;
import com.springlearn.moviecatalogservice.model.UserRating;
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
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        //get all movie rating associated with user
        UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class);

        //For each movie ID, call movie info service and get all movie info

        //put them all together

        return ratings.getRatings().stream().map(rating -> {
            // Using rest template
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
                   //Using webclient
              /*Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
                            .retrieve().bodyToMono(Movie.class).block();*/
              return  new CatalogItem(movie.getName(), movie.getMovieOverview(), rating.getRating());
        }
        ).collect(Collectors.toList());


    }
}
