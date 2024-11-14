package com.example.programming_project.service;

import com.example.programming_project.domain.Song;
import com.example.programming_project.domain.SongGenres;
import com.example.programming_project.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
public class SongService {
    private final SongRepository songRepository;

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song addSong(String songName, int duration, SongGenres genre, String audio) {
        var song = new Song(songName, duration, genre, audio);
        return songRepository.save(song);
    }

    public boolean updateSong(long songId, String newName) {
        var song = songRepository.findById((int) songId).orElse(null);
        if (song == null) {
            return false;
        }
        song.setSongName(newName);
        songRepository.save(song);
        return true;
    }

    public Song getSong(long id) {
        return songRepository.findById((int) id).orElseThrow(EntityNotFoundException::new);}
    public void removeSong(long id) {
        songRepository.deleteById((int) id);
    }

    public List<Song> searchSongs(String searchValue) {
        return songRepository.findSongByName(searchValue);
    }

    public boolean songExists(long id) {
        return songRepository.existsById((int) id);
    }
}
