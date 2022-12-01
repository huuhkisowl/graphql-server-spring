package com.huuhkisowl.graphql.services;

import java.util.List;
import java.util.Optional;

import com.huuhkisowl.graphql.models.Movie;

public interface MovieService {

	List<Movie> findAllMovies();
	Movie findMovieById(String id);
	List<Movie> findMovieByIdIn(List<String>ids);
	List<Movie> findMovieByTerm(String term);
	
	Movie createMovie(Movie newMovie);
	
}
