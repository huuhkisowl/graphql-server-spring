package com.huuhkisowl.graphql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.huuhkisowl.graphql.model.Actor;
import com.huuhkisowl.graphql.model.Director;
import com.huuhkisowl.graphql.model.Genre;
import com.huuhkisowl.graphql.model.Movie;
import com.huuhkisowl.graphql.repository.ActorRepository;
import com.huuhkisowl.graphql.repository.DirectorRepository;
import com.huuhkisowl.graphql.repository.GenreRepository;
import com.huuhkisowl.graphql.repository.MovieRepository;

@Controller
public class MovieController {

	private final MovieRepository movieRepository;
	private final GenreRepository genreRepository;
	private final ActorRepository actorRepository;
	private final DirectorRepository directorRepository;
	
	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public MovieController(MovieRepository movieRepository, ActorRepository actorRepository,
			GenreRepository genreRepository, DirectorRepository directorRepository, MongoTemplate mongoTemplate) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
        this.directorRepository = directorRepository;
        this.mongoTemplate = mongoTemplate;
    }
	
	@SchemaMapping(typeName = "Query", value = "movies")
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
	
	// Data fetcher
	@SchemaMapping
    public List<Genre> genres(Movie movie) {
        return genreRepository.findByIdIn(movie.getGenresIds());
    }
	
	@SchemaMapping
    public List<Actor> actors(Movie movie) {
        return actorRepository.findByIdIn(movie.getActorsIds());
    }
	
	@SchemaMapping
    public Director director(Movie movie) {
		if(movie.getDirectorId() == null || movie.getDirectorId().isEmpty())
			return null;
		
		Optional<Director> optionalEnitry = directorRepository.findById(movie.getDirectorId());
		return optionalEnitry.get();
    }
	//-- Data fetcher
	
	@QueryMapping
    public List<Movie> movie(@Argument String term) {
		
		final List<Criteria> criteria = new ArrayList<>();
		if (term != null && !term.isEmpty())
		     criteria.add(Criteria.where("name").regex(term, "i"));
		
		if (!criteria.isEmpty()) {
			final Query query = new Query();
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
			return mongoTemplate.find(query, Movie.class);
		}
		
		return new ArrayList<>();
    }
	
	@QueryMapping
    public List<Movie> moviesByGenreId(@Argument String id) {
		
		if (id != null && !id.isEmpty())
			return movieRepository.findByGenreId(id);
		
		return new ArrayList<>();
    }
	
	@QueryMapping
    public List<Movie> moviesByActorId(@Argument String id) {
		
		if (id != null && !id.isEmpty())
			return movieRepository.findByActorId(id);
		
		return new ArrayList<>();
    }
	
	@QueryMapping
    public List<Movie> moviesByDirectorId(@Argument String id) {
		
		if (id != null && !id.isEmpty())
			return movieRepository.findByDirectorId(id);
		
		return new ArrayList<>();
    }
	
	
	@MutationMapping
	public Movie createMovie(@Argument(name = "movie") Movie movie) {
		return movieRepository.save(movie);
	}
	
}
