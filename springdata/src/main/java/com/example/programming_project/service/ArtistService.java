package com.example.programming_project.service;

import com.example.programming_project.domain.Artist;
import com.example.programming_project.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
public class ArtistService {
    private ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist addArtist(String name, String profile, String description) {
        var artist = new Artist(name, profile, description);
        return artistRepository.save(artist);
    }

    public boolean updateArtist(long artistId, String newName, String newProfile) {
        var artist = artistRepository.findById((int) artistId).orElse(null);
        if (artist == null) {
            return false;
        }
        artist.setName(newName);
        artist.setProfile(newProfile);
        artistRepository.save(artist);
        return true;
    }

    public Artist getArtist(long id) {
        return artistRepository.findById((int) id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Artist> searchArtists(String searchValue) {
        return artistRepository.findArtistByName(searchValue);
    }

    public void removeArtist(long id) {
        artistRepository.deleteById((int) id);
    }
    public boolean artistExists(long id) {
        return artistRepository.existsById((int) id);
    }
}
