package com.huuhkisowl.graphql.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.huuhkisowl.graphql.models.Actor;

public interface ActorRepository extends MongoRepository<Actor, String> {

	List<Actor> findByIdIn(List<String> ids);
}
