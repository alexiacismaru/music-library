package com.example.programming_project.repository;

import com.example.programming_project.domain.Albums;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"hardcoded", "jpa", "jdbc"})
public interface AlbumRepository {
    Albums createAlbum(Albums album);
    List<Albums> readAlbums();
    Albums getAlbumsWithSongs(int id);
    void deleteAlbum(int id);
}
