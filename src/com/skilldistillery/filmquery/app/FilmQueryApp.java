package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
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

		int userOption = startUserInterface(input);
		switch (userOption) {
		case (1):
			System.out.println("Option 1 Selected");
			break;
		case (2):
			System.out.println("Option 2 Selected");
			break;
		case (3):
			System.out.println("Goodbye!");
			break;
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
				}
				else {
					throw new InputMismatchException();
				}
			} 
			catch (InputMismatchException e) {
				System.out.print("Invalid Entry - Please try again: ");
				input.nextLine();
			}
		}
	}

}
