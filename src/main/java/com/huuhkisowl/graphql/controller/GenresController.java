package com.huuhkisowl.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.huuhkisowl.graphql.model.Genre;
import com.huuhkisowl.graphql.repository.GenreRepository;

@Controller
public class GenresController {
	
	private final GenreRepository genreRepository;

	@Autowired
    public GenresController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
	
	@SchemaMapping(typeName = "Query", value = "genres")
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
	
	@MutationMapping
	public Genre createGenre(@Argument(name = "genre") Genre genre) {
		return genreRepository.save(genre);
	}

}
