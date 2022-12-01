package com.huuhkisowl.graphql.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huuhkisowl.graphql.models.Director;
import com.huuhkisowl.graphql.repositories.DirectorRepository;
import com.huuhkisowl.graphql.services.DirectorService;

@Service
public class DirectorServiceImpl implements DirectorService {
	
	private final DirectorRepository directorRepository;
	
	@Autowired
	DirectorServiceImpl(DirectorRepository directorRepository){
        this.directorRepository = directorRepository;
    }

	@Override
	public Director findDirectorById(String id) {
		Optional<Director> optinalEntity = directorRepository.findById(id);
		return optinalEntity.get();
	}

}
