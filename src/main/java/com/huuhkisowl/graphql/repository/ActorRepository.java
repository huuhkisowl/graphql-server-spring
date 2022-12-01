package com.huuhkisowl.graphql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.huuhkisowl.graphql.model.Actor;

public interface ActorRepository extends MongoRepository<Actor, String> {

	List<Actor> findByIdIn(List<String> ids);
}
