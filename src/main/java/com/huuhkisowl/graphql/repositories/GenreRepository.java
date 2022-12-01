package com.huuhkisowl.graphql.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.huuhkisowl.graphql.models.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
	
	List<Genre> findByIdIn(List<String> ids);

}
