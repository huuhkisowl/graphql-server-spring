package com.huuhkisowl.graphql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.huuhkisowl.graphql.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
	
	List<Movie> findByIdIn(List<String> ids);
	
	@Query("{ 'genresIds' : ?0 }")
	List<Movie> findByGenreId(String id);
	
	@Query("{ 'actorsIds' : ?0 }")
	List<Movie> findByActorId(String id);
	
	@Query("{ 'directorId' : ?0 }")
	List<Movie> findByDirectorId(String id);

}
