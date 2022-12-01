package com.huuhkisowl.graphql.dataFetchers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huuhkisowl.graphql.models.Movie;
import com.huuhkisowl.graphql.services.MovieService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CreateMovieDataFetcher implements DataFetcher<Movie> {

	private final MovieService movieService;
	
	@Autowired
	CreateMovieDataFetcher(MovieService movieService){
        this.movieService = movieService;
    }
	
	@Override
    public Movie get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        return createMovie(args);
    }
	
	public Movie createMovie(Map args) {
		Movie newMovie = new Movie();
		newMovie.setName(String.valueOf(args.get("name")));
		
		if(args.containsKey("year"))
			newMovie.setYear(Integer.parseInt(String.valueOf(args.get("year"))));
		
		if(args.containsKey("ageLimit"))
			newMovie.setAgeLimit(Integer.parseInt(String.valueOf(args.get("ageLimit"))));
		
		if(args.containsKey("rating"))
			newMovie.setRating(Integer.parseInt(String.valueOf(args.get("rating"))));
		
		if(args.containsKey("synopsis"))
			newMovie.setSynopsis(String.valueOf(args.get("synopsis")));
		
		return movieService.createMovie(newMovie);
	}
	
}
