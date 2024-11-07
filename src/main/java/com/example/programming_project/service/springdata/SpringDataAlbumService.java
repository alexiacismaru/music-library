package com.example.programming_project.service.springdata;

import com.example.programming_project.domain.Albums;
import com.example.programming_project.repository.springdata.SpringDataAlbumRepository;
import com.example.programming_project.repository.springdata.SpringDataSongRepository;
import com.example.programming_project.service.AlbumService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdata")
@Primary
public class SpringDataAlbumService implements AlbumService {
    private SpringDataAlbumRepository albumRepository;
    private SpringDataSongRepository songRepository;
    @Autowired
    public SpringDataAlbumService(SpringDataAlbumRepository albumRepository, SpringDataSongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @Override
    public List<Albums> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAlbum(int id) {
        Albums album = albumRepository.findById(id).get();

        album.getSongs().forEach(song->{
            song.getAlbums().remove(album);
            songRepository.save(song);
        });
        albumRepository.deleteById(id);
    }

    @Override
    public Albums addAlbum(Albums album) {
        return albumRepository.save(album);
    }

    @Override
    public Albums getAlbumWithSongs(int albumId) {
        return albumRepository.getAlbumsWithSongs(albumId);
    }
}
