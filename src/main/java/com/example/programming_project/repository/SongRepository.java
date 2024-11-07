package com.example.programming_project.repository;

import com.example.programming_project.domain.Songs;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"hardcoded", "jpa", "jdbc"})
public interface SongRepository {
    Songs createSong(Songs song);
    List<Songs> readSongs();
    Songs findSongById(int id);
    void deleteSong(int id);
}
