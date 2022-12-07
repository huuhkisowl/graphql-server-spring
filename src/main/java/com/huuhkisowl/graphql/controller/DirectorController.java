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

import com.huuhkisowl.graphql.model.Director;
import com.huuhkisowl.graphql.repository.DirectorRepository;

@Controller
public class DirectorController {
	
	private final DirectorRepository directorRepository;
	
	private final MongoTemplate mongoTemplate;

	@Autowired
    public DirectorController(DirectorRepository directorRepository, MongoTemplate mongoTemplate) {
        this.directorRepository = directorRepository;
		this.mongoTemplate = mongoTemplate;
    }
	
	@SchemaMapping(typeName = "Query", value = "directors")
    public List<Director> findAll() {
        return directorRepository.findAll();
    }
	
	@QueryMapping
    public List<Director> director(@Argument String term) {
		
		final List<Criteria> criteria = new ArrayList<>();
		if (term != null && !term.isEmpty())
			criteria.add(Criteria.where("firstName").regex(term, "i"));
			criteria.add(Criteria.where("lastName").regex(term, "i"));
		
		if (!criteria.isEmpty()) {
			
			final Query query = new Query();
			query.addCriteria(new Criteria().orOperator(criteria.toArray(new Criteria[criteria.size()])));
			return mongoTemplate.find(query, Director.class);
		}
		
		return new ArrayList<>();
    }
	
	@MutationMapping
	public Director createDirector(@Argument(name = "director") Director director) {
		return directorRepository.save(director);
	}

}
