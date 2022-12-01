package com.huuhkisowl.graphql.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.huuhkisowl.graphql.models.Movie;
import com.huuhkisowl.graphql.repositories.MovieRepository;
import com.huuhkisowl.graphql.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieRepository movieRepository;
	
	private final MongoTemplate mongoTemplate;
	
	@Autowired
	MovieServiceImpl(MovieRepository movieRepository, MongoTemplate mongoTemplate){
        this.movieRepository = movieRepository;
        this.mongoTemplate = mongoTemplate;
    }

	@Override
	public List<Movie> findAllMovies() {
		return (ArrayList) movieRepository.findAll();
	}

	@Override
	public Movie findMovieById(String id) {
		Optional<Movie> optinalEntity = movieRepository.findById(id);
		return optinalEntity.get();
	}

	@Override
	public List<Movie> findMovieByIdIn(List<String> ids) {
		 return movieRepository.findByIdIn(ids);
	}

	@Override
	public List<Movie> findMovieByTerm(String term) {
		final List<Criteria> criteria = new ArrayList<>();
		if (term != null && !term.isEmpty())
		     criteria.add(Criteria.where("name").regex(term, "i"));
		
		if (!criteria.isEmpty()) {
			final Query query = new Query();
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
			return mongoTemplate.find(query, Movie.class);
		}
		
		return new ArrayList<>();
	}

	@Override
	public Movie createMovie(Movie newMovie) {
		movieRepository.save(newMovie);
		return newMovie;
	}
	
}
