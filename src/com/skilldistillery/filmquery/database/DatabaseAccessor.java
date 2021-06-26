package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId);

	public Actor findActorById(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);

	public String findLanguageNameByLanguageId(int langId);

	public List<Film> findFilmsByKeywordInTitleOrDesc(String keyword);
	
	public List<String> findCategoriesByFilmId(int filmId);
	
	public Inventory findInventoryByFilmId(int filmId);
}
