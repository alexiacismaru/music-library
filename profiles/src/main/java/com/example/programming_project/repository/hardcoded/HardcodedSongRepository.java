package com.example.programming_project.repository.hardcoded;

import com.example.programming_project.domain.Song;
import com.example.programming_project.repository.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("hardcoded")
public class HardcodedSongRepository implements SongRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static List<Song> songs = new ArrayList<>();

    @Override
    public Song createSong(Song song) {
        logger.info("Creating song {}", song);
        songs.add(song);
        return song;
    }

    @Override
    public List<Song> readSong() {
        logger.info("Reading songs from the database.");
        return songs;
    }

    @Override
    public Song findSongById(int id) {
        return null;
    }

    @Override
    public void deleteSong(int id) {}
}

