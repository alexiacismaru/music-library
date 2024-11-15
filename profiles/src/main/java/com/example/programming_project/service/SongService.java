package com.example.programming_project.service;

import com.example.programming_project.domain.Song;

import java.util.List;

public interface SongService {
    Song addSong(Song song);
    List<Song> getAllSong();
    Song getSong(int id);
    void deleteSong(int id);
}
