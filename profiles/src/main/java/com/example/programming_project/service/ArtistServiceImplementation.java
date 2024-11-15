package com.example.programming_project.service;

import com.example.programming_project.domain.Artist;
import com.example.programming_project.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"hardcoded", "jpa", "jdbc"})
@Primary
public class ArtistServiceImplementation implements ArtistService{
    private ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImplementation(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist addArtist(Artist artist){
        return artistRepository.createArtist(artist);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.readArtists();
    }

    @Override
    public void deleteArtist(int id) {
        artistRepository.deleteArtist(id);
    }

    @Override
    public Artist getArtistWithAlbums(int artistId) {
        return artistRepository.getArtistWithAlbums(artistId);
    }
}
