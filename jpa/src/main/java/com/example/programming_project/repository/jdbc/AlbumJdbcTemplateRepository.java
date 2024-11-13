//package com.example.programming_project.repository.jdbc;
//
//import com.example.programming_project.domain.Albums;
//import com.example.programming_project.repository.AlbumRepository;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Date;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//@Profile("jdbc")
//public class AlbumJdbcTemplateRepository implements AlbumRepository {
//    private JdbcTemplate jdbcTemplate;
//    private SimpleJdbcInsert simpleJdbcInsert;
//
//    public AlbumJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
//                .withTableName("album")
//                .usingGeneratedKeyColumns("id");
//    }
//
//    private Albums mapRowAlbum(ResultSet resultSet, int id) throws SQLException {
//        return new Albums(resultSet.getInt("id"),
//                resultSet.getString("title"),
//                resultSet.getInt("amountofsongs"),
//                resultSet.getDate("releasedate").toLocalDate());
//    }
//
//    @Override
//    public List<Albums> readAlbums() {
//        return jdbcTemplate.query("select * from album", this::mapRowAlbum);
//    }
//
//    @Override
//    public Albums getAlbumsWithSongs(int id) {
//        return (Albums) jdbcTemplate.query("SELECT * FROM album INNER JOIN song s ON album.id = s.albumid WHERE album.id = ?", this::mapRowAlbum, id);
//    }
//
//    @Override
//    public Albums createAlbum(Albums album) {
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("title", album.getTitle());
//        parameters.put("amountofsongs", album.getAmountOfSongs());
//        parameters.put("releasedate", Date.valueOf(album.getReleaseDate()));
//        album.setId(simpleJdbcInsert.executeAndReturnKey(parameters).intValue());
//        return album;
//    }
//
////    @Override
////    public void updateAlbum(Albums album) {
////        jdbcTemplate.update("update album set title = ?, amountofsongs = ?, amountofsongs = ? where id = ?",
////                album.getTitle(), album.getAmountOfSongs(), album.getReleaseDate());
////    }
//
//    @Override
//    public void deleteAlbum(int id) {
//        jdbcTemplate.update("delete from album where id = ?", id);
//    }
//}
