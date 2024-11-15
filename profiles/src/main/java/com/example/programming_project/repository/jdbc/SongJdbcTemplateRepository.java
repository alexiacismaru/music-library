package com.example.programming_project.repository.jdbc;

import com.example.programming_project.domain.Song;
import com.example.programming_project.domain.SongGenres;
import com.example.programming_project.repository.SongRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jdbc")
public class SongJdbcTemplateRepository implements SongRepository {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    public SongJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("song")
                .usingGeneratedKeyColumns("id");
    }

    private Song mapRowSongs(ResultSet resultSet, int id) throws SQLException {
        return new Song(resultSet.getInt("id"),
                resultSet.getString("songname"),
                resultSet.getInt("duration"),
                SongGenres.valueOf(resultSet.getString("genre")),
                resultSet.getString("audio"));
    }


    @Override
    public List<Song> readSong() {
        return jdbcTemplate.query("select * from song", this::mapRowSongs);
    }

    @Override
    public Song findSongById(int id) {
        return jdbcTemplate.queryForObject("select from song where id = ?", this::mapRowSongs, id);
    }

    @Override
    public Song createSong(Song song) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("songname", song.getSongName());
        parameters.put("duration", song.getDuration());
        parameters.put("genre", song.getGenre());
        parameters.put("audio", song.getAudio());
        song.setId(simpleJdbcInsert.executeAndReturnKey(parameters).intValue());
        return song;
    }

    @Override
    public void deleteSong(int id) {
        jdbcTemplate.update("delete from song where id = ?", id);
    }
}

