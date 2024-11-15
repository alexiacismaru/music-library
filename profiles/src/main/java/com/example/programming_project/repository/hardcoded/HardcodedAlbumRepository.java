package com.example.programming_project.repository.hardcoded;

import com.example.programming_project.domain.Album;
import com.example.programming_project.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("hardcoded")
public class HardcodedAlbumRepository implements AlbumRepository {
    public static final List<Album> albums = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Album createAlbum(Album album) {
        logger.info("Creating album {}", album);
        albums.add(album);
        return album;
    }

    @Override
    public List<Album> readAlbum() {
        logger.info("Reading albums from the database.");
        return albums;
    }

    @Override
    public Album getAlbumWithSongs(int id) {
        return null;
    }

    @Override
    public void deleteAlbum(int id) {

    }
}
