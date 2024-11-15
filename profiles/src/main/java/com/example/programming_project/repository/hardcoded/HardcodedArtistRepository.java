package com.example.programming_project.repository.hardcoded;

import com.example.programming_project.domain.Artist;
import com.example.programming_project.repository.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("hardcoded")
public class HardcodedArtistRepository implements ArtistRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static List<Artist> artists = new ArrayList<>();

    @Override
    public Artist createArtist(Artist artist) {
        logger.info("Creating artist {}", artist);
        artists.add(artist);
        return artist;
    }

    @Override
    public List<Artist> readArtists() {
        logger.info("Reading artists from the database.");
        return artists;
    }

    @Override
    public Artist getArtistWithAlbums(int id) {
        return null;
    }

    @Override
    public void deleteArtist(int id) {

    }
}


