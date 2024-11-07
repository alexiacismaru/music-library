package com.example.programming_project.service.springdata;

import com.example.programming_project.domain.Songs;
import com.example.programming_project.repository.springdata.SpringDataSongRepository;
import com.example.programming_project.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdata")
@Primary
public class SpringDataSongService implements SongService {
    private SpringDataSongRepository songRepository;

    @Autowired
    public SpringDataSongService(SpringDataSongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Songs> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Songs getSong(int id) {
        return songRepository.findById(id).get();
    }

    @Override
    public void deleteSong(int id) {
        songRepository.deleteById(id);
    }
    @Override
    public Songs addSong(Songs song) {
        return songRepository.save(song);
    }
}
