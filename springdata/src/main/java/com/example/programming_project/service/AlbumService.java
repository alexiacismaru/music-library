package com.example.programming_project.service;

import com.example.programming_project.domain.Album;
import com.example.programming_project.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Album addAlbum(String title, LocalDate releaseDate, String cover) {
        var album = new Album(title, releaseDate, cover);
        return albumRepository.save(album);
    }

    public boolean updateAlbum(long albumId, String newName, LocalDate newReleaseDate, String newCover) {
        var album = albumRepository.findById((int) albumId).orElse(null);
        if (album == null) {
            return false;
        }
        album.setTitle(newName);
        album.setReleaseDate(newReleaseDate);
        album.setCover(newCover);
        albumRepository.save(album);
        return true;
    }
    public Album getAlbum(long id) {
        return albumRepository.findById((int) id).orElseThrow(EntityNotFoundException::new);
    }
    public List<Album> searchAlbums(String searchValue) {
        return albumRepository.findAlbumByTitle(searchValue);
    }

    public void removeAlbum(long id) {
        albumRepository.deleteById((int) id);
    }

    public boolean albumExists(long id) {
        return albumRepository.existsById((int) id);
    }
}
