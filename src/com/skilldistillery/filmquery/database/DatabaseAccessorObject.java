package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
    private String pass = "student";
	
    static { 
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    
    
  @Override
  public Film findFilmById(int filmId) {
	  String query = "SELECT * FROM film WHERE id = ?";
	  try (Connection conn = DriverManager.getConnection(URL, user, pass);
		   PreparedStatement stmt = conn.prepareStatement(query)) {
		  
		  stmt.setInt(1, filmId);
		  ResultSet rs = stmt.executeQuery();
		  
		  if (rs.next()) {
			  Film film = new Film();
			  film.setId(rs.getInt("id"));
			  film.setTitle(rs.getString("title"));
			  film.setDescription(rs.getString("description"));
			  String releaseDate = rs.getString("release_year");
			  film.setReleaseYear(Integer.valueOf(releaseDate.substring(0,4)));
			  film.setLanguageId(rs.getInt("language_id"));
			  film.setRentalDuration(rs.getInt("rental_duration"));
			  film.setRentalRate(rs.getDouble("rental_rate"));
			  film.setLength(rs.getInt("length"));
			  film.setReplacementCost(rs.getDouble("replacement_cost"));
			  film.setRating(rs.getString("rating"));
			  film.setSpecialFeatures(rs.getString("special_features"));
			  rs.close();
			  film.setAllActorsInFilm(findActorsByFilmId(filmId));
			  return film;
		  }
	  }
	  catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return null;
  }

  
  @Override
  public Actor findActorById(int actorId) {
	  String query = "SELECT * FROM actor WHERE id = ?";
	  try (Connection conn = DriverManager.getConnection(URL, user, pass);
		   PreparedStatement stmt = conn.prepareStatement(query)) {
		  
		  stmt.setInt(1, actorId);
		  ResultSet rs = stmt.executeQuery();
		  
		  if (rs.next()) {
			  Actor actor = new Actor();
			  actor.setId(rs.getInt("id"));
			  actor.setFirstName(rs.getString("first_name"));
			  actor.setLastName(rs.getString("last_name"));
			  rs.close();
			  return actor;
		  }
	  }
	  catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return null;
  }
  
  
  @Override
  public List<Actor> findActorsByFilmId(int filmId) { 
	  String query = "SELECT actor.id, actor.first_name, actor.last_name FROM actor"
	  		+ " JOIN film_actor ON  actor.id = film_actor.actor_id"
	  		+ " JOIN film ON film_actor.film_id = film.id WHERE film_id = ?"
	  		+ " ORDER BY actor.id";
	  
	  List<Actor> actors = new ArrayList<Actor>();
	  try (Connection conn = DriverManager.getConnection(URL, user, pass);
			   PreparedStatement stmt = conn.prepareStatement(query)) {
		  	  
			  stmt.setInt(1, filmId);
			  ResultSet rs = stmt.executeQuery();
			  
			  while (rs.next()) {
				  Actor actor = new Actor();
				  actor.setId(rs.getInt("id"));
				  actor.setFirstName(rs.getString("first_name"));
				  actor.setLastName(rs.getString("last_name"));
			  }
			  rs.close();
			  return actors;
		  }
		  catch (SQLException e) {
			  e.printStackTrace();
		  }
		  return null;
	  }
  
  
  
  
}
