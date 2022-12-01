package com.huuhkisowl.graphql.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huuhkisowl.graphql.models.Genre;
import com.huuhkisowl.graphql.repositories.GenreRepository;
import com.huuhkisowl.graphql.services.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
	
	private final GenreRepository genreRepository;

    @Autowired
    GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

	@Override
	public List<Genre> findGenresByIdIn(List<String> ids) {
		return genreRepository.findByIdIn(ids);
	}

}
