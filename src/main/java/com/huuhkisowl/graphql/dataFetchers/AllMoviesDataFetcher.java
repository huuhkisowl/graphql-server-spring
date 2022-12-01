package com.huuhkisowl.graphql.dataFetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huuhkisowl.graphql.models.Movie;
import com.huuhkisowl.graphql.services.MovieService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllMoviesDataFetcher implements DataFetcher<List<Movie>> {

	private final MovieService movieService;
	
	@Autowired
	AllMoviesDataFetcher(MovieService movieService){
        this.movieService = movieService;
    }
	
	@Override
    public List<Movie> get(DataFetchingEnvironment env) {
        return movieService.findAllMovies();
    }
}
