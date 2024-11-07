package com.example.programming_project.repository.jdbc;

import com.example.programming_project.domain.Albums;
import com.example.programming_project.domain.Artist;
import com.example.programming_project.exceptions.DatabaseException;
import com.example.programming_project.repository.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("jdbc")
@Primary
public class ArtistJdbcRepository implements ArtistRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Artist> readArtists() {
        List<Artist> artist = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from artist")) {
                    while (resultSet.next()) {
                        int ID = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String gender = resultSet.getString("gender");
                        int debutYear = resultSet.getInt("debutyear");
                        Artist artists = new Artist(ID, name, gender, debutYear);
                        artist.add(artists);
                    }
                }
            }
            return artist;
        } catch (SQLException e) {
            logger.error("Oops, something went wrong! :(");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Artist getArtistWithAlbums(int id) {
        Artist artist = null;
        List<Albums> albums = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT a.id AS artistid, a.name, a.gender, a.debutyear, s.title, s.amountofsongs, s.releasedate " +
                            "FROM artist a INNER JOIN album s ON a.id = s.artistid WHERE a.id = ?")) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        if (artist == null) {
                            int ID = resultSet.getInt("artistid");
                            String name = resultSet.getString("name");
                            String gender = resultSet.getString("gender");
                            int debutYear = resultSet.getInt("debutyear");
                            artist = new Artist(ID, name, gender, debutYear);
                        }

                        String title = resultSet.getString("title");
                        int amountOfSongs = resultSet.getInt("amountofsongs");
                        LocalDate releaseDate = resultSet.getDate("releasedate").toLocalDate();
                        Albums album = new Albums(title, amountOfSongs, releaseDate);
                        albums.add(album);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Problem getting the artist...", e);
            throw new DatabaseException("Problem getting the artist...", e);
        }
        if (artist != null) {
            artist.setAlbums(albums);
        }
        return artist;
    }

    @Override
    public Artist createArtist(Artist artist) {
        Artist createdArtist = new Artist(artist.getName(), artist.getGender(), artist.getDebutYear());
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement statement = connection.prepareStatement("insert into artist(name, gender, debutyear) " +
                    "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, artist.getName());
                statement.setString(2, artist.getGender());
                statement.setInt(3, artist.getDebutYear());

                int result = statement.executeUpdate();
                if (result != 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            createdArtist.setId(generatedKeys.getInt(1));
                        } else {
                            throw new SQLException("Creating artist failed, no ID obtained!");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Problem creating artist...", e);
            throw new DatabaseException("Problem creating artist...", e);
        }
        return createdArtist;
    }

//    @Override
//    public void updateArtist(Artist artist) {
//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
//            try (PreparedStatement statement = connection.prepareStatement("update artist set name = ?, gender = ?, debutyear = ?  where id = ?")) {
//                statement.setString(1, artist.getName());
//                statement.setString(2, artist.getGender());
//                statement.setInt(3, artist.getDebutYear());
//                statement.setInt(4, artist.getId());
//
//                int result = statement.executeUpdate();
//                if (result == 0) {
//                    throw new SQLException("Updating artist failed...");
//                }
//            }
//        } catch (SQLException e) {
//            logger.error("Problem updating the artist...", e);
//            throw new DatabaseException("Problem updating the artist...", e);
//        }
//    }

    @Override
    public void deleteArtist(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement statement = connection.prepareStatement("delete from artist where id = ?")) {
                statement.setInt(1, id);
                int result = statement.executeUpdate();
                if (result == 0) {
                    throw new SQLException("Deleting artist failed");
                }
            }
        } catch (SQLException e) {
            logger.error("Problem deleting the artist...", e);
            throw new DatabaseException("Problem deleting the artist...", e);
        }
    }
}
