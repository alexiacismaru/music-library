// package com.example.programming_project.service;

// import com.example.programming_project.domain.Songs;
// import com.example.programming_project.repository.SongRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Primary;
// import org.springframework.context.annotation.Profile;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// @Profile({"hardcoded", "jpa", "jdbc"})
// @Primary
// public class SongServiceImplementation implements SongService {
//     private final SongRepository songRepository;

//     @Autowired
//     public SongServiceImplementation(SongRepository songRepository) {
//         this.songRepository = songRepository;
//     }

//     @Override
//     public Songs addSong(Songs song) {
//         return songRepository.createSong(song);
//     }

//     @Override
//     public List<Songs> getAllSongs() {
//         return songRepository.readSongs();
//     }

//     @Override
//     public Songs getSong(int id) {
//         return (Songs) songRepository.findSongById(id);
//     }

//     @Override
//     public void deleteSong(int id) {
//         songRepository.deleteSong(id);
//     }
// }
