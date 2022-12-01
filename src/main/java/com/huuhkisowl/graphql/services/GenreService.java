package com.huuhkisowl.graphql.services;

import java.util.List;

import com.huuhkisowl.graphql.models.Genre;

public interface GenreService {

	List<Genre> findGenresByIdIn(List<String>ids);
	
}
