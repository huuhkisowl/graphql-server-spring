package com.huuhkisowl.graphql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.huuhkisowl.graphql.model.Director;

public interface DirectorRepository extends MongoRepository<Director, String> {
	
	List<Director> findByIdIn(List<String> ids);

}
