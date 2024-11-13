//package com.example.programming_project.repository.jdbc;
//
//import com.example.programming_project.domain.Albums;
//import com.example.programming_project.domain.Songs;
//import com.example.programming_project.domain.SongGenres;
//import com.example.programming_project.repository.SongRepository;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//@Profile("jdbc")
//public class SongJdbcTemplateRepository implements SongRepository {
//    private JdbcTemplate jdbcTemplate;
//    private SimpleJdbcInsert simpleJdbcInsert;
//
//    public SongJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
//                .withTableName("song")
//                .usingGeneratedKeyColumns("id");
//    }
//
//    private Songs mapRowSongs(ResultSet resultSet, int id) throws SQLException {
//        return new Songs(resultSet.getInt("id"),
//                resultSet.getString("songname"),
//                resultSet.getInt("duration"),
//                SongGenres.valueOf(resultSet.getString("genre")));
//    }
//
//
//    @Override
//    public List<Songs> readSongs() {
//        return jdbcTemplate.query("select * from song", this::mapRowSongs);
//    }
//
//    @Override
//    public Songs findSongById(int id) {
//        return jdbcTemplate.queryForObject("select from song where id = ?", this::mapRowSongs, id);
//    }
//
//    @Override
//    public Songs createSong(Songs song) {
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("songname", song.getSongName());
//        parameters.put("duration", song.getDuration());
//        parameters.put("genre", song.getGenre());
//        song.setId(simpleJdbcInsert.executeAndReturnKey(parameters).intValue());
//        return song;
//    }
//
//    @Override
//    public void deleteSong(int id) {
//        jdbcTemplate.update("delete from song where id = ?", id);
//    }
//}
