package com.huuhkisowl.graphql.dataFetchers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huuhkisowl.graphql.models.Movie;
import com.huuhkisowl.graphql.services.MovieService;

import graphql.language.Field;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class MovieDataFetcher implements DataFetcher<List<Movie>> {

	private final MovieService movieService;
	
	@Autowired
	MovieDataFetcher(MovieService movieService){
        this.movieService = movieService;
    }
	
	@Override
    public List<Movie> get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        List<Movie> movies = movieService.findMovieByTerm(String.valueOf(args.get("term")));
        return movies;
    }
	
}
