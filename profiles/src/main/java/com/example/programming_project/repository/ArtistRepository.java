package com.example.programming_project.repository;

import com.example.programming_project.domain.Artist;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"hardcoded", "jpa", "jdbc"})
public interface ArtistRepository {
    Artist createArtist(Artist artist);
    List<Artist> readArtists();
    Artist getArtistWithAlbums(int id);
    void deleteArtist(int id);
}

