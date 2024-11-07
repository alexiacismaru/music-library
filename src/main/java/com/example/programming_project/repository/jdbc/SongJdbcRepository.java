package com.example.programming_project.repository.jdbc;

import com.example.programming_project.domain.Albums;
import com.example.programming_project.domain.Songs;
import com.example.programming_project.domain.SongGenres;
import com.example.programming_project.exceptions.DatabaseException;
import com.example.programming_project.repository.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("jdbc")
@Primary
public class SongJdbcRepository implements SongRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public List<Songs> readSongs() {
        List<Songs> song = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from song")) {
                    while (resultSet.next()) {
                        int ID = resultSet.getInt("id");
                        String songName = resultSet.getString("songname");
                        int duration = resultSet.getInt("duration");
                        String genre = resultSet.getString("genre");
                        Songs songs = new Songs(ID, songName, duration, SongGenres.valueOf(genre));
                        song.add(songs);
                    }
                }
            }
            return song;
        } catch (SQLException e) {
            logger.error("Oops, something went wrong! :(");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Songs findSongById(int id) {
        Songs song = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("select * from song where id = ?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int ID = resultSet.getInt("id");
                    String songName = resultSet.getString("songname");
                    int duration = resultSet.getInt("duration");
                    SongGenres genre = SongGenres.valueOf(resultSet.getString("genre"));
                    song = new Songs(ID, songName, duration , genre);
                }
            }
            return song;

        } catch (SQLException e) {
            logger.error("Problem getting the song...", e);
            throw new DatabaseException("Problem getting the song...", e);
        }
    }

    @Override
    public Songs createSong(Songs song) {
        Songs createdSong = new Songs(song.getSongName(), song.getDuration(), song.getGenre());
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement statement = connection.prepareStatement("insert into song(songname, duration, genre) " +
                    "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, song.getSongName());
                statement.setDouble(2, song.getDuration());
                statement.setString(3, String.valueOf(song.getGenre()));

                int result = statement.executeUpdate();
                if (result != 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            createdSong.setId(generatedKeys.getInt(1));
                        } else {
                            throw new SQLException("Creating song failed, no ID obtained!");
                        }
                    }
                }
            }

        } catch (SQLException e) {
            logger.error("Problem creating song...", e);
            throw new DatabaseException("Problem creating song...", e);
        }
        return createdSong;
    }

//    @Override
//    public void updateSong(Songs song) {
//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
//            try (PreparedStatement statement = connection.prepareStatement("update song set songname = ?, duration = ?, genre = ? where id = ?")) {
//                statement.setString(1, song.getSongName());
//                statement.setDouble(2, song.getDuration());
//                statement.setString(3, String.valueOf(song.getGenre()));
//                statement.setInt(4, song.getId());
//
//                int result = statement.executeUpdate();
//                if (result == 0) {
//                    throw new SQLException("Updating song failed...");
//                }
//            }
//        } catch (SQLException e) {
//            logger.error("Problem updating the song...", e);
//            throw new DatabaseException("Problem updating the song...", e);
//        }
//    }

    @Override
    public void deleteSong(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Programming_3", "postgres", "1234")) {
            try (PreparedStatement statement = connection.prepareStatement("delete from song where id = ?")) {
                statement.setInt(1, id);
                int result = statement.executeUpdate();
                if (result == 0) {
                    throw new SQLException("Deleting song failed");
                }
            }
        } catch (SQLException e) {
            logger.error("Problem deleting the song...", e);
            throw new DatabaseException("Problem deleting the song...", e);
        }
    }
}