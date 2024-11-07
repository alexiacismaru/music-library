package com.example.programming_project.repository.jdbc;

import com.example.programming_project.domain.Artist;
import com.example.programming_project.repository.ArtistRepository;
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
public class ArtistJdbcTemplateRepository implements ArtistRepository {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    public ArtistJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("artist")
                .usingGeneratedKeyColumns("id");
    }

    private Artist mapRowArtist(ResultSet resultSet, int id) throws SQLException {
        return new Artist(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("gender"),
                resultSet.getInt("debutyear"));
    }

    @Override
    public List<Artist> readArtists() {
        return jdbcTemplate.query("select * from artist", this::mapRowArtist);
    }

    @Override
    public Artist createArtist(Artist artist) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", artist.getName());
        parameters.put("gender", artist.getGender());
        parameters.put("debutyear", artist.getDebutYear());
        artist.setId(simpleJdbcInsert.executeAndReturnKey(parameters).intValue());
        return artist;
    }

//    @Override
//    public void updateArtist(Artist artist) {
//        jdbcTemplate.update("update artist SET name = ?, gender = ?, debutyear = ? where id = ?",
//                artist.getName(), artist.getGender(), artist.getDebutYear());
//    }

    @Override
    public void deleteArtist(int id) {
        jdbcTemplate.update("delete from artist where id = ?", id);
    }

    @Override
    public Artist getArtistWithAlbums(int artistId) {
        return null;
    }
}
