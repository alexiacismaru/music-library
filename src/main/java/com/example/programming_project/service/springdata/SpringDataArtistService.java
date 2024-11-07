package com.example.programming_project.service.springdata;

import com.example.programming_project.domain.Artist;
import com.example.programming_project.repository.springdata.SpringDataArtistRepository;
import com.example.programming_project.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdata")
@Primary
public class SpringDataArtistService implements ArtistService {
    private SpringDataArtistRepository artistRepository;

    @Autowired
    public SpringDataArtistService(SpringDataArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public void deleteArtist(int id) {
        artistRepository.deleteById(id);
    }

    @Override
    public Artist getArtistWithAlbums(int artistId) {
        return artistRepository.getArtistWithAlbums(artistId);
    }

    @Override
    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }
}
