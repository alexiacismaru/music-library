// package com.example.programming_project.service;

// import com.example.programming_project.domain.Albums;
// import com.example.programming_project.repository.AlbumRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Primary;
// import org.springframework.context.annotation.Profile;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// @Profile({"hardcoded", "jpa", "jdbc"})
// @Primary
// public class AlbumServiceImplementation implements AlbumService{
//     private AlbumRepository albumRepository;

//     @Autowired
//     public AlbumServiceImplementation(AlbumRepository albumRepository) {
//         this.albumRepository = albumRepository;
//     }

//     @Override
//     public Albums addAlbum(Albums album) {
//         return albumRepository.createAlbum(album);
//     }

//     @Override
//     public Albums getAlbumWithSongs(int albumId) {
//         return albumRepository.getAlbumsWithSongs(albumId);
//     }

//     @Override
//     public List<Albums> getAllAlbums() {
//         return albumRepository.readAlbums();
//     }

//     @Override
//     public void deleteAlbum(int id) {
//         albumRepository.deleteAlbum(id);
//     }
// }
