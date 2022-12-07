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

import com.huuhkisowl.graphql.model.Actor;
import com.huuhkisowl.graphql.repository.ActorRepository;

@Controller
public class ActorController {
	
	private final ActorRepository actorRepository;
	
	private final MongoTemplate mongoTemplate;

	@Autowired
    public ActorController(ActorRepository actorRepository, MongoTemplate mongoTemplate) {
        this.actorRepository = actorRepository;
		this.mongoTemplate = mongoTemplate;
    }
	
	@SchemaMapping(typeName = "Query", value = "actors")
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }
	
	@QueryMapping
    public List<Actor> actor(@Argument String term) {
		
		final List<Criteria> criteria = new ArrayList<>();
		if (term != null && !term.isEmpty())
			criteria.add(Criteria.where("firstName").regex(term, "i"));
			criteria.add(Criteria.where("lastName").regex(term, "i"));
		
		if (!criteria.isEmpty()) {
			
			final Query query = new Query();
			query.addCriteria(new Criteria().orOperator(criteria.toArray(new Criteria[criteria.size()])));
			return mongoTemplate.find(query, Actor.class);
		}
		
		return new ArrayList<>();
    }
	
	@MutationMapping
	public Actor createActor(@Argument(name = "actor") Actor actor) {
		return actorRepository.save(actor);
	}

}
