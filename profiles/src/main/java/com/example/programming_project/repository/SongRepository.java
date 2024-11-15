package com.example.programming_project.repository;

import com.example.programming_project.domain.Song;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"hardcoded", "jpa", "jdbc"})
public interface SongRepository {
    Song createSong(Song song);
    List<Song> readSong();
    Song findSongById(int id);
    void deleteSong(int id);
}

