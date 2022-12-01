package com.huuhkisowl.graphql.dataFetchers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huuhkisowl.graphql.models.Genre;
import com.huuhkisowl.graphql.models.Movie;
import com.huuhkisowl.graphql.services.GenreService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GenreDataFetcher implements DataFetcher<List<Genre>> {

	private final GenreService genreService;
	
	@Autowired
	GenreDataFetcher(GenreService genreService){
        this.genreService = genreService;
    }
	
	@Override
	public List<Genre> get(DataFetchingEnvironment env) {
        Movie movie = env.getSource();
        List<String> genresIds = new ArrayList<String>();
        if(movie != null){
        	genresIds = movie.getGenresIds();
        }
        List<Genre> genres = genreService.findGenresByIdIn(genresIds);
        return genres;
    }
}
