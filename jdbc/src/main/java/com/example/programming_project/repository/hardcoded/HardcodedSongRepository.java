//package com.example.programming_project.repository.hardcoded;
//
//import com.example.programming_project.domain.Songs;
//import com.example.programming_project.repository.SongRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HardcodedSongRepository implements SongRepository {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    public static List<Songs> songs = new ArrayList<>();
//
//    @Override
//    public Songs createSong(Songs song) {
//        logger.info("Creating song {}", song);
//        songs.add(song);
//        return song;
//    }
//
//    @Override
//    public List<Songs> readSongs() {
//        logger.info("Reading songs from the database.");
//        return songs;
//    }
//
//    @Override
//    public Songs findSongById(int id) {
//        return null;
//    }
//
//    @Override
//    public void deleteSong(int id) {}
//}
