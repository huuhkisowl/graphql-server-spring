package com.huuhkisowl.graphql.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huuhkisowl.graphql.models.Actor;
import com.huuhkisowl.graphql.repositories.ActorRepository;
import com.huuhkisowl.graphql.services.ActorService;

@Service
public class ActorServiceImpl implements ActorService {
	
	private final ActorRepository actorRepository;

    @Autowired
    ActorServiceImpl(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

	@Override
	public List<Actor> findActorsByIdIn(List<String> ids) {
		return actorRepository.findByIdIn(ids);
	}

}
