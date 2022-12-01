package com.huuhkisowl.graphql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.huuhkisowl.graphql.model.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
	
	List<Genre> findByIdIn(List<String> ids);

}
