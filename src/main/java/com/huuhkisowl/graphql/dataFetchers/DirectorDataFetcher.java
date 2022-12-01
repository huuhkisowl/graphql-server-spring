package com.huuhkisowl.graphql.dataFetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huuhkisowl.graphql.models.Director;
import com.huuhkisowl.graphql.models.Movie;
import com.huuhkisowl.graphql.services.DirectorService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class DirectorDataFetcher implements DataFetcher<Director> {

	private final DirectorService directorService;
	
	@Autowired
	DirectorDataFetcher(DirectorService directorService){
        this.directorService = directorService;
    }
	
	@Override
	public Director get(DataFetchingEnvironment env) {
        Movie movie = env.getSource();
        if(movie != null){
        	String directorId = movie.getDirectorId();
        	return directorService.findDirectorById(directorId);
        }
        return null;
    }
}
