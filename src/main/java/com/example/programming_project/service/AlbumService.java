package com.example.programming_project.service;

import com.example.programming_project.domain.Albums;

import java.util.List;

public interface AlbumService {
    List<Albums> getAllAlbums();
    void deleteAlbum(int id);
    Albums addAlbum(Albums album);
    Albums getAlbumWithSongs(int albumId);
}
