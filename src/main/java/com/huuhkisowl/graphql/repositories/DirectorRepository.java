package com.huuhkisowl.graphql.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.huuhkisowl.graphql.models.Director;

public interface DirectorRepository extends MongoRepository<Director, String> {
	
	List<Director> findByIdIn(List<String> ids);

}
