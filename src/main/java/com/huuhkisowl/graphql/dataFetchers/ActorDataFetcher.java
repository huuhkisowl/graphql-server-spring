package com.huuhkisowl.graphql.dataFetchers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huuhkisowl.graphql.models.Actor;
import com.huuhkisowl.graphql.models.Movie;
import com.huuhkisowl.graphql.services.ActorService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ActorDataFetcher implements DataFetcher<List<Actor>> {

	private final ActorService actorService;
	
	@Autowired
	ActorDataFetcher(ActorService actorService){
        this.actorService = actorService;
    }
	
	@Override
	public List<Actor> get(DataFetchingEnvironment env) {
        Movie movie = env.getSource();
        List<String> actorsIds = new ArrayList<String>();
        if(movie != null){
        	actorsIds = movie.getActorsIds();
        }
        List<Actor> actors = actorService.findActorsByIdIn(actorsIds);
        return actors;
    }
}
