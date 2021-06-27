# Film Query Project

### Overview
This application allows the user to lookup films by their ID# or by searching a keyword. The results are queried from a mySQL database and reported back to the user.
The basic information returned includes the film title, release year, rating, description, language, and the actors that are in the film.
If a user searches by keyword, all of the films with a matching keyword will be returned. If the user looks up a film by ID, in addition to the basic information returned, they are provided the option of viewing more details about the film to include the language ID#, rental duration, rental rate, length, replacement cost, special features, categories, and a count of how many copies are in inventory along with their condition.

### Technologies Used
* Java for the application code
* mySQL for the database
* Maven for dependency management
* JDBC API for interaction with the database
* JUnit 5 (Jupiter)
* Object Oriented Programming
 * Encapsulation
 * toString, hashCode, equals
 * Interface
 * Collections and Lists

### How To Run
At program startup - the user is prompted with a menu to search films by ID #, Keyword, or to Quit. Selecting an option other than 1-3 will notify the user to try again.

* If search by ID is selected, the user is asked to enter an ID#. The databaseAccessorObject (DBAO) queries the database using the provided film ID#. If a film with that ID does not exist, the user is informed of this. Otherwise, the user is displayed the film title, release year, rating, description, language, and all of the actors that are in the film. The user is then provided with an option to view all of the films details. If 'Y' or 'N' is not entered, the user is asked to enter a valid response. If the user chooses 'Y', they are then displayed the films ID#, title, description, release year, language id, rental duration, rental rate, length, replacement cost, rating, special features, categories, and the number of copies in inventory along with their condition. If the user chooses 'N', they are returned to the main menu.

* If search by keyword is selected, the user is asked to enter a keyword. The DBAO queries the database and returns all of the films that contain the keyword in the title or description. The results show the same information as what is displayed when searching by ID but each films ID # is also included in these results. At this point, the main menu appears again. Because the user is provided a potentially long list of results, the user is not provided an option to view full film details. To view the full film details of a particular film, they can use the film ID# that was returned in the results to search by Film Id from the main menu.

* If quit is chosen, the program terminates with a "Goodbye" message.

### Lessons Learned
* When beginning on JUnit, I discovered that creating more class constructors would have been beneficial vs. calling each setter to create a new object.
* It is always valuable to test SQL commands directly in the database to save time when errors in the code logic exist and to ensure the results that come back are as intended.
* Creating more classes that relate to the connecting tables in the dataBase might have been beneficial to reduce some code in the databaseAccessorObject methods.
* I like working with databases.
