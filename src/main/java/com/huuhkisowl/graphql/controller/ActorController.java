package com.huuhkisowl.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.huuhkisowl.graphql.model.Actor;
import com.huuhkisowl.graphql.repository.ActorRepository;

@Controller
public class ActorController {
	
	private final ActorRepository actorRepository;

	@Autowired
    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }
	
	@SchemaMapping(typeName = "Query", value = "actors")
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

}
