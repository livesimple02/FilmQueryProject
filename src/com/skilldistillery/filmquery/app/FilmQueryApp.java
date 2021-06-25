package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		boolean keepGoing = true;
		while (keepGoing) {
			int userOption = startUserInterface(input);
			switch (userOption) {
			case (1):
				lookupByFilmId(input);
				break;
			case (2):
				lookupByKeyword(input);
				break;
			case (3):
				System.out.println();
				System.out.println("Goodbye!");
				keepGoing = false;
				break;
			}
		}
		input.close();
	}

	private int startUserInterface(Scanner input) {
		System.out.println("1) Look up a film by ID #");
		System.out.println("2) Look up a film by keyword");
		System.out.println("3) Exit");
		System.out.print("Select an option: ");

		int userSelection = 0;
		while (true) {
			try {
				userSelection = input.nextInt();
				if (userSelection > 0 && userSelection < 4) {
					input.nextLine();
					return userSelection;
				} else {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.print("Invalid Entry - Please try again: ");
				input.nextLine();
			}
		}
	}

	private void lookupByFilmId(Scanner input) {
		System.out.println();
		System.out.print("Enter the ID # of the film you would like to lookup: ");
		int userInput = 0;
		boolean validEntry = false;
		while (!validEntry) {
			try {
				userInput = input.nextInt();
				input.nextLine();
				Film film = db.findFilmById(userInput);
				if (film == null) {
					System.out.println("No film was found with ID # of: " + userInput);
					validEntry = true;
				} else {
					System.out.println(film.getPartialDetails() + " -- Language: " + db.findLanguageNameByLanguageId(film.getLanguageId()));
					film.printActorsInFilm();
					validEntry = true;
				}
				System.out.println();
			} catch (InputMismatchException e) {
				System.out.print("Invalid Entry - Please enter a whole number: ");
				input.nextLine();
			}
		}
	}

	private void lookupByKeyword(Scanner input) {
		System.out.println();
		System.out.print("Enter the keyword you would like to search for: ");

				String userInput = input.nextLine();
				List<Film> films = db.findFilmsByKeywordInTitleOrDesc(userInput);
				
				if (films.size() == 0) {
					System.out.println("No film was found with the keyword: " + userInput);
				} else {
					for (Film film : films) {
						System.out.println(film.getPartialDetails() + " -- Language: " + db.findLanguageNameByLanguageId(film.getLanguageId()));
						film.printActorsInFilm();
					}
				}
				System.out.println();
	}
}
