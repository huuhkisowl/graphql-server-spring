package com.huuhkisowl.graphql.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.huuhkisowl.graphql.model.Movie;
import com.huuhkisowl.graphql.repository.MovieRepository;

@Controller
public class MovieController {

	private final MovieRepository movieRepository;
	
	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public MovieController(MovieRepository movieRepository, MongoTemplate mongoTemplate) {
        this.movieRepository = movieRepository;
        this.mongoTemplate = mongoTemplate;
    }
	
	@SchemaMapping(typeName = "Query", value = "movies")
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
	
	@QueryMapping
    public List<Movie> movie(@Argument String term) {
		
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
	
	@MutationMapping
	public Movie createMovie(@Argument String name, @Argument Integer year, @Argument Integer ageLimit, @Argument Integer rating, @Argument String synopsis) {
		Movie newMovie = new Movie();
		newMovie.setName(name);
		
		if(year!=null) {
			newMovie.setYear(year);
		}
		
		if(ageLimit!=null) {
			newMovie.setAgeLimit(ageLimit);
		}
		
		if(rating!=null) {
			newMovie.setRating(rating);
		}
		
		if(synopsis!=null) {
			newMovie.setSynopsis(synopsis);
		}
		
		movieRepository.save(newMovie);
		return newMovie;
	}
	
	
}
