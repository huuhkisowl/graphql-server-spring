package com.huuhkisowl.graphql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huuhkisowl.graphql.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
	
	List<Movie> findByIdIn(List<String> ids);

}
