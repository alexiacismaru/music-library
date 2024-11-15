package com.example.programming_project.repository.jdbc;

import com.example.programming_project.domain.Album;
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
public class ArtistJDBCRepository implements ArtistRepository {
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
                        String profile = resultSet.getString("profile");
                        String description = resultSet.getString("description");
                        Artist artists = new Artist(ID, name, profile, description);
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
        List<Album> albums = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT a.id AS artistid, a.name, a.profile, a.description, s.title, s.releasedate, s.cover " +
                            "FROM artist a INNER JOIN album s ON a.id = s.artistid WHERE a.id = ?")) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        if (artist == null) {
                            int ID = resultSet.getInt("artistid");
                            String name = resultSet.getString("name");
                            String profile = resultSet.getString("profile");
                            String description = resultSet.getString("description");
                            artist = new Artist(ID, name, profile, description);
                        }

                        String title = resultSet.getString("title");
                        LocalDate releaseDate = resultSet.getDate("releasedate").toLocalDate();
                        String cover = resultSet.getString("cover");
                        Album album = new Album(title, releaseDate, cover);
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
        Artist createdArtist = new Artist(artist.getName(), artist.getProfile(), artist.getDescription());
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement statement = connection.prepareStatement("insert into artist(name, profile, description) " +
                    "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, artist.getName());
                statement.setString(2, artist.getProfile());
                statement.setString(3, artist.getDescription());

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
//            try (PreparedStatement statement = connection.prepareStatement("update artist set name = ?, profile = ?, description = ?  where id = ?")) {
//                statement.setString(1, artist.getName());
//                statement.setString(2, artist.getProfile());
//                statement.setString(3, artist.getDescription());
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

