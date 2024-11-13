// package com.example.programming_project.configuration;


// import com.example.programming_project.repository.AlbumRepository;
// import com.example.programming_project.repository.ArtistRepository;
// import com.example.programming_project.repository.SongRepository;
// import com.example.programming_project.repository.hardcoded.HardcodedAlbumRepository;
// import com.example.programming_project.repository.hardcoded.HardcodedArtistRepository;
// import com.example.programming_project.repository.hardcoded.HardcodedSongRepository;
// import com.example.programming_project.service.*;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class SystemConfiguration {
//     @Bean
//     public AlbumRepository albumRepository() {
//         return new HardcodedAlbumRepository();
//     }
//     @Bean
//     public AlbumService albumService(AlbumRepository albumRepository){
//         return new AlbumServiceImplementation(albumRepository);
//     }
//     @Bean
//     public ArtistRepository artistRepository() {
//         return new HardcodedArtistRepository();
//     }
//     @Bean
//     public ArtistService artistService(ArtistRepository artistRepository){
//         return new ArtistServiceImplementation(artistRepository);
//     }
//     @Bean
//     public SongRepository songRepository() {
//         return new HardcodedSongRepository();
//     }
//     @Bean
//     public SongService songService(SongRepository songRepository){
//         return new SongServiceImplementation(songRepository);
//     }
// }
