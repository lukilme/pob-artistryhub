

<div align="end">
<a href="./index.md">EN</a>
</div>


# ArtistryHub 🎉


## Descrição 🗯:

Projeto desenvolvido para o curso de persistência de objetos utilizando a linguagem Java. O objetivo é criar um sistema de informações com o tema 'Apresentação de Artista'.

Trata-se de uma aplicação simples para armazenamento e manipulação de dados, focada em gerenciamento de apresentações de artistas em diferentes cidades. O projeto envolve operações de CRUD (criação, leitura, atualização e exclusão) para três tipos principais de objetos, claro, seguindo as regras do modelo de negócio

## Requisitos Funcionais 📝

**Básico:** 

**F1-** Operações CRUD com o objeto Artist.

**F2-** Operações CRUD com o objeto City.

**F3-** Operações CRUD com o objeto Presentation.

**F4-** GUI para cada objecto

<hr> 

**Funções de consulta:** 

**F5-** Quais artistas têm mais apresentações.

**F6-** Quais artistas se apresentam em uma data específica 

**F7-** Inserindo a cidade e a data, forneça uma lista de artistas que correspondem à combinação 

**F8-** Mostre quais artistas se apresentaram em uma na cidade.

## Estrutura de diretórios 📂

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

## Dependências📚:

Bibliotecas de testes unitários e criação de tabelas e gráficos foram introduzidas para melhor observação dos dados em questão.
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

 ## Regras do professor ❗:


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