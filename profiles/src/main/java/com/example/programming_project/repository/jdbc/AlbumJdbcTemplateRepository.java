package com.example.programming_project.repository.jdbc;

import com.example.programming_project.domain.Album;
import com.example.programming_project.repository.AlbumRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jdbc")
public class AlbumJdbcTemplateRepository implements AlbumRepository {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    public AlbumJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("album")
                .usingGeneratedKeyColumns("id");
    }

    private Album mapRowAlbum(ResultSet resultSet, int id) throws SQLException {
        return new Album(resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getDate("releasedate").toLocalDate(),
                resultSet.getString("cover"));
    }

    @Override
    public List<Album> readAlbum() {
        return jdbcTemplate.query("select * from album", this::mapRowAlbum);
    }

    @Override
    public Album getAlbumWithSongs(int id) {
        return (Album) jdbcTemplate.query("SELECT * FROM album INNER JOIN song s ON album.id = s.albumid WHERE album.id = ?", this::mapRowAlbum, id);
    }

    @Override
    public Album createAlbum(Album album) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", album.getTitle());
        parameters.put("releasedate", Date.valueOf(album.getReleaseDate()));
        parameters.put("cover", album.getCover());
        album.setId(simpleJdbcInsert.executeAndReturnKey(parameters).intValue());
        return album;
    }

//    @Override
//    public void updateAlbum(Albums album) {
//        jdbcTemplate.update("update album set title = ?, releasedate = ?, cover =? where id = ?",
//                album.getTitle(), album.getReleaseDate(), album.getCover());
//    }

    @Override
    public void deleteAlbum(int id) {
        jdbcTemplate.update("delete from album where id = ?", id);
    }
}


