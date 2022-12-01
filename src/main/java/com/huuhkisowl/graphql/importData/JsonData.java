package com.huuhkisowl.graphql.importData;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huuhkisowl.graphql.model.*;
import com.huuhkisowl.graphql.repository.*;

import jakarta.annotation.PostConstruct;

@Component
public class JsonData {
	private final MovieRepository movieRepository;
	private final GenreRepository genreRepository;
	private final ActorRepository actorRepository;
	private final DirectorRepository directorRepository;
	
	@Autowired
	public JsonData(MovieRepository movieRepository, GenreRepository genreRepository,
			ActorRepository actorRepository, DirectorRepository directorRepository) {
		this.movieRepository = movieRepository;
		this.genreRepository = genreRepository;
		this.actorRepository = actorRepository;
		this.directorRepository = directorRepository;
	}
	
	//@PostConstruct
	public void generateDataFromJson() {
		
		try {
			
			// Getting data from json file
			Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/data/movies-compact.json"));
		    
		    // convert JSON array to list of movies
		    List<getMovie> movies = new Gson().fromJson(reader, new TypeToken<List<getMovie>>() {}.getType());
		    
		    reader.close();
		    
		    if(!movies.isEmpty()) {
		    	
		    	List<getMovie> allMovies = new ArrayList<getMovie>();
		    	Map<String, Genre> collectionGenres = new HashMap<String, Genre>();
		    	Map<String, Actor> collectionActors = new HashMap<String, Actor>();
		    	Map<String, Director> collectionDirectors = new HashMap<String, Director>();
		    	
		    	// First collection genres, actors and directors, then save into mongodb and getting those IDs
		    	for(getMovie movie  : movies) {
		    		
		    		allMovies.add(movie);
		    		
		    		String[] getGenres = movie.genres;
		    		getActor[] getActors = movie.actors;
		    		getDirector director = movie.director;
		    		
		    		for(String genre : getGenres) {
		    			if(!collectionGenres.containsKey(genre)) {
		    				Genre newGenre = genreRepository.save(new Genre(genre));
		    				collectionGenres.put(genre, newGenre);
		    			}
		    		}
		    		
		    		for(getActor actor : getActors) {
		    			String name = actor.firstName + " " + actor.lastName;
		    			if(!collectionActors.containsKey(name)) {
		    				Actor newActor = actorRepository.save(new Actor(actor.firstName, actor.lastName));
		    				collectionActors.put(name, newActor);
		    			}
		    		}
		    		
		    		String directorName = director.firstName + " " + director.lastName;
	    			if(!collectionDirectors.containsKey(directorName)) {
	    				Director newDirector = directorRepository.save(new Director(director.firstName, director.lastName));
	    				collectionDirectors.put(directorName, newDirector);
	    			}
		    		
		    	}
		    	
		    	// Generate data of movie into mongoDB
		    	for(getMovie saveMovie  : allMovies) {
		    		
		    		List<String> genresIds = new ArrayList<String>();
		    		List<String> actorsIds = new ArrayList<String>();
		    		String directorId = null;
		    		
		    		String[] getGenres = saveMovie.genres;
		    		for(String genre : getGenres) {
		    			if(collectionGenres.containsKey(genre)) {
		    				Genre getGenre = collectionGenres.get(genre);
		    				genresIds.add(getGenre.getId());
		    			}
		    		}
		    		
		    		getActor[] getActors = saveMovie.actors;
		    		for(getActor actor : getActors) {
		    			String name = actor.firstName + " " + actor.lastName;
		    			if(collectionActors.containsKey(name)) {
		    				Actor getActor = collectionActors.get(name);
		    				actorsIds.add(getActor.getId());
		    			}
		    		}
		    		
		    		getDirector director = saveMovie.director;
		    		String directorName = director.firstName + " " + director.lastName;
		    		if(collectionDirectors.containsKey(directorName)) {
		    			Director getDirector = collectionDirectors.get(directorName);
		    			directorId = getDirector.getId();
	    			}
		    		
		    		movieRepository.save(new Movie(saveMovie.name, saveMovie.year, genresIds, saveMovie.ageLimit,
		    				saveMovie.rating, actorsIds, directorId, saveMovie.synopsis));
		    		
		    	}
		    	
		    }
		    
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		
	}
	
	public class getMovie {
		
		public String name;
		public int year;
		public String[] genres;
		public int ageLimit;
		public int rating;
		public getActor[] actors;
		public getDirector director;
		public String synopsis;
		
	}
	
	public class getActor {
		
		public String firstName;
		public String lastName;
		
	}
	
	public class getDirector {
		
		public String firstName;
		public String lastName;
		
	}
}
