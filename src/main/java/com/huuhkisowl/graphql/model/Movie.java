package com.huuhkisowl.graphql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "movies")
public class Movie {
	
	@Id
	private String id;
	
	private String name;
	private int year;
	private List<String> genresIds;
	private int ageLimit;
	private int rating;
	private List<String> actorsIds;
	private String directorId;
	private String synopsis;
	
	public Movie() {
	}
	
	public Movie(String name, int year, List<String> genresIds, int ageLimit, 
			int rating, List<String> actorsIds, String directorId, String synopsis) {
		this.name = name;
		this.year = year;
		this.genresIds = genresIds;
		this.ageLimit = ageLimit;
		this.rating = rating;
		this.actorsIds = actorsIds;
		this.directorId = directorId;
		this.synopsis = synopsis;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<String> getGenresIds() {
		return genresIds;
	}
	public void setGenresIds(List<String> genresIds) {
		this.genresIds = genresIds;
	}
	public int getAgeLimit() {
		return ageLimit;
	}
	public void setAgeLimit(int ageLimit) {
		this.ageLimit = ageLimit;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<String> getActorsIds() {
		return actorsIds;
	}
	public void setActorsIds(List<String> actorsIds) {
		this.actorsIds = actorsIds;
	}
	public String getDirectorId() {
		return directorId;
	}
	public void setDirectorId(String directorId) {
		this.directorId = directorId;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", year=" + year + ", genresIds=" + genresIds + ", ageLimit="
				+ ageLimit + ", rating=" + rating + ", actorsIds=" + actorsIds + ", directorId=" + directorId
				+ ", synopsis=" + synopsis + "]";
	}
	
}
