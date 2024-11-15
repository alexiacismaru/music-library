package com.example.programming_project.repository.jdbc;

import com.example.programming_project.domain.Album;
import com.example.programming_project.domain.Song;
import com.example.programming_project.domain.SongGenres;
import com.example.programming_project.exceptions.DatabaseException;
import com.example.programming_project.repository.AlbumRepository;
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
public class AlbumJDBCRepository implements AlbumRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Album> readAlbum() {
        List<Album> album = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from album")) {
                    while (resultSet.next()) {
                        int ID = resultSet.getInt("id");
                        String title = resultSet.getString("title");
                        LocalDate releaseDate = resultSet.getDate("releasedate").toLocalDate();
                        String cover = resultSet.getString("cover");
                        Album albums = new Album(ID, title, releaseDate, cover);
                        album.add(albums);
                    }
                }
            }
            return album;
        } catch (SQLException e) {
            logger.error("Oops, something went wrong! :(");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Album getAlbumWithSongs(int id) {
        Album album = null;
        List<Song> songs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT a.id AS albumid, a.title, a.releasedate,a.cover, s.songname, s.duration, s.genre, s.audio " +
                            "FROM album a INNER JOIN song s ON a.id = s.albumid WHERE a.id = ?")) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        if (album == null) {
                            int ID = resultSet.getInt("albumid");
                            String title = resultSet.getString("title");
                            LocalDate releaseDate = resultSet.getDate("releasedate").toLocalDate();
                            String cover = resultSet.getString("cover");
                            album = new Album(ID, title, releaseDate, cover);
                        }

                        String songName = resultSet.getString("songname");
                        int songDuration = resultSet.getInt("duration");
                        SongGenres songGenre = SongGenres.valueOf(resultSet.getString("genre"));
                        String audio = resultSet.getString("audio");
                        Song song = new Song(songName, songDuration, songGenre, audio);
                        songs.add(song);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Problem getting the album...", e);
            throw new DatabaseException("Problem getting the album...", e);
        }
        if (album != null) {
            album.setSongs(songs);
        }
        return album;
    }


    @Override
    public Album createAlbum(Album album) {
        Album createdAlbum = new Album(album.getTitle(), album.getReleaseDate(), album.getCover());
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement statement = connection.prepareStatement("insert into album(title, amountofsongs, releasedate) " +
                    "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, album.getTitle());
                statement.setDate(2, Date.valueOf(album.getReleaseDate()));
                statement.setString(3, album.getCover());

                int result = statement.executeUpdate();
                if (result != 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            createdAlbum.setId(generatedKeys.getInt(1));
                        } else {
                            throw new SQLException("Creating album failed, no ID obtained!");
                        }
                    }
                }
            }

        } catch (SQLException e) {
            logger.error("Problem creating album...", e);
            throw new DatabaseException("Problem creating album...", e);
        }
        return createdAlbum;
    }

//    @Override
//    public void updateAlbum(Album album) {
//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
//            try (PreparedStatement statement = connection.prepareStatement("update album set title = ?, releasedate = ?, cover = ? where id = ?")) {
//                statement.setString(1, album.getTitle());
//                statement.setDate(2, Date.valueOf(album.getReleaseDate()));
//                statement.setString(3, album.getCover());
//                statement.setInt(4, album.getId());
//
//                int result = statement.executeUpdate();
//                if (result == 0) {
//                    throw new SQLException("Updating album failed...");
//                }
//            }
//        } catch (SQLException e) {
//            logger.error("Problem updating the album...", e);
//            throw new DatabaseException("Problem updating the album...", e);
//        }
//    }

    @Override
    public void deleteAlbum(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement statement = connection.prepareStatement("delete from album where id = ?")) {
                statement.setInt(1, id);
                int result = statement.executeUpdate();
                if (result == 0) {
                    throw new SQLException("Deleting album failed");
                }
            }
        } catch (SQLException e) {
            logger.error("Problem deleting the album...", e);
            throw new DatabaseException("Problem deleting the album...", e);
        }
    }
}


