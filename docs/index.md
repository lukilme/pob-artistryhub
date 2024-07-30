<div align="end">
<a href="./pt.md">PT</a>
</div>

## ArtistryHub 🎉

## Description  🗯::
Project developed for the object persistence course using the Java language. The goal is to create an information system with the theme 'Artist Presentation'.

It is a simple application for storing and manipulating data, focused on managing artist presentations in different cities. The project involves CRUD operations (creation, reading, updating and deletion) for three main types of objects, of course, following the rules of the business model.

## Functional Requirements 📝

**Basic:**

**F1-** CRUD operations with the Artist object.

**F2-** CRUD operations with the City object.

**F3-** CRUD operations with the Apresentation object.

**F4-** GUI for any object

<hr>

**Query funcionts:**

**F4-** Which artists have the most performances.

**F5-** Which artists perform on a specific date

**F6-** Entering the city and date, provide a list of artists that match the combination

**F7-** Show which artists performed at one in the city.

## Folder Structure 📂

    artistryhub
    ├── lib
    │   └──db4o library and dependencies
    ├── src/main/java/com/artistryhub     
    │   ├── dao
    │   │   ├──DAO
    │   │   ├──DAOInterface
    │   │   ├──DAOArtist
    │   │   ├──DAOCity
    │   │   ├──DAOPresentation
    │   │   └──Utility
    │   ├── exception  
    │   │   ├──CustomException
    │   │   └──ExceptionCode
    │   ├── model
    │   │   ├──Artist
    │   │   ├──City
    │   │   └──Presentation
    │   ├── repository
    │   ├── service
    │   │   ├──AbstractFacade
    │   │   ├──Facade
    │   │   ├──ArtistFacade
    │   │   ├──CityFacade
    │   │   └──PresentationFacade
    │   ├── controller
    │   ├── view
    │   ├── terminal
    │   ├── view
    ├── src/test/java/com/artistryhub 
    │   ├── view
    │   ├── main   
    └── pom.xml

> NOTE
>
> For see more about classes : <a href="./classes">click here</a>


## Dependencies📚:
Unit testing libraries and creation of tables and graphs were introduced for better observation of the data in question.
```xml
	<dependency>
		<groupId>org.junit.jupiter</groupId>
		<artifactId>junit-jupiter-engine</artifactId>
	    <version>5.8.1</version>
	    <scope>test</scope>
	</dependency>
	<dependency>
        <groupId>org.assertj</groupId>
		<artifactId>assertj-core</artifactId>
		<version>3.21.0</version>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>jfree</groupId>
		<artifactId>jfreechart</artifactId>
	    <version>1.0.13</version>
	</dependency>
```

## Teacher's rules ❗:

```
Objective: 
To practice the concepts of transparent persistence of objects, through the 
development of an information system, using layered architecture, 
db4o object database and DAO standard.

Tasks: 
- (1pt) Implement the business model classes for the given theme 
- (1pt) Implement the specific DAO classes for the business classes, including the read() methods for the 3 queries of the chosen theme. Configure the database with the Util class.
- (2pt) Implement in the Facade class the requirements for creating, altering, deleting and listing each business class, as well as the business rules 
- (1pt) Implement the console applications: - Cadastrar.java - register several objects of the business model - Listar.java – list all registered objects - Consultar.java – list the 3 queries of the theme 
- (1pt) Implement the graphical applications – one screen for each business class and one screen for the 3 queries. 
- (4pt) Present the project Note: The presentation is mandatory for each student to get a grade
```