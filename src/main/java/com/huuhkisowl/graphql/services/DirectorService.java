package com.huuhkisowl.graphql.services;

import com.huuhkisowl.graphql.models.Director;

public interface DirectorService {

	Director findDirectorById(String id);
	
}
