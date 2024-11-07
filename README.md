Alexia Cismaru, ACS201

<h2>Domain model</h2>
![Domain model](/src/main/resources/static/domainModel.png)

<h2>Data model</h2>
![Data model](/src/main/resources/static/dataModel.png)

The Domain includes "Artist", "Albums" and "Songs" classes,
where "Albums" and "Artist" have a many-to-one relation and "Songs" and 
"Albums" have a many-to-many relation.
More, there is the "Song genres" enum which includes all the available genres that can be assigned to a song.

Profiles: "jdbc", "jpa", "query", "hardcoded", "web", and "console".
"web" implements the website, while "console" the console View.

The implemented database: PostgresSQL database.

<h3>Web features:</h3>
    <ol>
        <li>3 pages that indicate the 3 main entities (Artists, Songs, and Albums)</li>
        <li>separate pages that add or remove entities</li>
        <li>if you click on a particular object you get to a page containing its attributes and associated data</li>
        <li>session history that shows the pages the user viewed</li>
        <li>navigation bar and footer are made using fragments</li>
        <li>responsive pages</li>
        <li>the website is designed using bootstrap</li>
        <li>the website supports several other languages</li>
        <li>client side validation shows if a field was filled incorrectly or not filled at all</li>
        <li>custom error pages</li>
    </ol>

<h3>Code features:</h3>
    <ol>
        <li>layered architecture (presentation - service - repository - domain are in different packages)</li>
        <li>loose coupling from using interfaces</li>
        <li>loggers indicate important methods</li>
        <li>the relations between entities are shown in the domain and .sql code</li>
        <li>navigation bar and footer are made using fragments</li>
        <li>adding and deleting entities</li>
        <li>form validation is implemented using View Models</li>
        <li>separate service and repository for each entity</li>
        <li>custom error implemented at controller level</li>
    </ol>

<h3>Added dependencies when making the project:</h3>
    <ul>
        <li>Spring Web</li>
        <li>Thymeleaf</li>
        <li>JDBC API</li>
        <li>Spring Data JPA</li>
        <li>Spring Data JDBC</li>
        <li>PostgresSQL Driver</li>
    </ul>
