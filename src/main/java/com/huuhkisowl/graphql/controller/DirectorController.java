package com.huuhkisowl.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.huuhkisowl.graphql.model.Director;
import com.huuhkisowl.graphql.repository.DirectorRepository;

@Controller
public class DirectorController {
	
	private final DirectorRepository directorRepository;

	@Autowired
    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }
	
	@SchemaMapping(typeName = "Query", value = "director")
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

}
