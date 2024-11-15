package com.example.programming_project.service;

import com.example.programming_project.domain.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbum();
    void deleteAlbum(int id);
    Album addAlbum(Album album);
    Album getAlbumWithSongs(int albumId);
}
