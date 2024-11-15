package com.example.programming_project.repository;

import com.example.programming_project.domain.Album;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"hardcoded", "jpa", "jdbc"})
public interface AlbumRepository {
    Album createAlbum(Album album);
    List<Album> readAlbum();
    Album getAlbumWithSongs(int id);
    void deleteAlbum(int id);
}
