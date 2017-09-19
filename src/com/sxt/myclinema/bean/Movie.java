package com.sxt.myclinema.bean;

import java.io.Serializable;

/**
 * 电影类
*@author Ryan
*@version 创建时间:2017年6月25日
*/
public class Movie implements Serializable{
	private String actor;
	private String director;
	private String movieName;
	private MovieType movieType;
	private String poster;
	private float price;
	
	public Movie() {
		super();
	}
	public Movie(String actor, String director, String movieName, MovieType movieType, String poster, float price) {
		super();
		this.actor = actor;
		this.director = director;
		this.movieName = movieName;
		this.movieType = movieType;
		this.poster = poster;
		this.price = price;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public MovieType getMovieType() {
		return movieType;
	}
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actor == null) ? 0 : actor.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((movieName == null) ? 0 : movieName.hashCode());
		result = prime * result + ((movieType == null) ? 0 : movieType.hashCode());
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (actor == null) {
			if (other.actor != null)
				return false;
		} else if (!actor.equals(other.actor))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (movieName == null) {
			if (other.movieName != null)
				return false;
		} else if (!movieName.equals(other.movieName))
			return false;
		if (movieType != other.movieType)
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Movie [actor=" + actor + ", director=" + director + ", movieName=" + movieName + ", movieType="
				+ movieType + ", poster=" + poster + ", price=" + price + "]";
	}
	
}
