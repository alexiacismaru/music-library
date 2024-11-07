package com.example.programming_project.service;

import com.example.programming_project.domain.Artist;

import java.util.List;

public interface ArtistService {
    Artist addArtist(Artist artist);
    List<Artist> getAllArtists();
    void deleteArtist(int id);
    Artist getArtistWithAlbums(int artistId);
}
