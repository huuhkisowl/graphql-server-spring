package com.huuhkisowl.graphql.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huuhkisowl.graphql.models.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
	
	List<Movie> findByIdIn(List<String> ids);

}
