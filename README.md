<div align="end">
<a href="./pt.md">PT</a>
</div>


<div align="center">

## ArtistryHub 🎉

</div>

 ## Description:
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

> [!NOTE]
> For see more about classes : <a href="./classes.md">click here</a>



 ## Dependencies
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

 ## Teacher's rules:

```
Objetivo

Praticar os conceitos de persistência transparente de objetos, através do
desenvolvimento de um sistema de informação, usando arquitetura em camadas, banco
de objetos db4o e padrão DAO.

Tarefas:
- (1pt) Implementar as classes do modelo de negócio para o tema fornecido
- (1pt) Implementar as classes DAO específicas para as classes de negócio,
incluindo os métodos read() as 3 consultas do tema escolhido. Configurar o
banco com a classe Util.
- (2pt) Implementar na classe Fachada requisitos de criar, alterar, excluir e listar
de cada classe de negócio bem como as regras de negócio
- (1pt) Implementar as aplicações console:
- Cadastrar.java - cadastrar vários objetos do modelo de negócio
- Listar.java – listar todos os objetos cadastrados
- Consultar.java – listar as 3 consultas do tema
- (1pt) Implementar as aplicações gráficas – uma tela para cada classe de negócio
e uma tela para as 3 consultas.
- (4pt) Apresentar o projeto
Obs: A apresentação é obrigatória para cada aluno ter nota
```