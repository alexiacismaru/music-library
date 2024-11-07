package com.example.programming_project.service;

import com.example.programming_project.domain.Songs;

import java.util.List;

public interface SongService {
    Songs addSong(Songs song);
    List<Songs> getAllSongs();
    Songs getSong(int id);
    void deleteSong(int id);
}
