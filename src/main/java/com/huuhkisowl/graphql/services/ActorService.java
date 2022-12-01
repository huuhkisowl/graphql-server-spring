package com.huuhkisowl.graphql.services;

import java.util.List;

import com.huuhkisowl.graphql.models.Actor;

public interface ActorService {

	List<Actor> findActorsByIdIn(List<String>ids);
	
}
