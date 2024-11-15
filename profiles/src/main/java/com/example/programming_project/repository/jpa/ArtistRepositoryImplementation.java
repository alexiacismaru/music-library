package com.example.programming_project.repository.jpa;

import com.example.programming_project.domain.Artist;
import com.example.programming_project.repository.ArtistRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
@Profile("jpa")
public class ArtistRepositoryImplementation implements ArtistRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Artist> readArtists() {
        return em.createQuery("SELECT artist from Artist artist", Artist.class).getResultList();
    }

    @Override
    @Transactional
    public Artist createArtist(Artist artist) {
        em.persist(artist);
        return artist;
    }

//    @Override
//    @Transactional
//    public void updateArtist(Artist artist) {
//        em.merge(artist);
//    }

    @Override
    @Transactional
    public void deleteArtist(int id) {
        em.remove(em.find(Artist.class, id));
    }

    @Override
    public Artist getArtistWithAlbums(int artistId) {
        return em.createQuery("SELECT a FROM Artist a JOIN FETCH a.albums where a.id = ?1", Artist.class).getSingleResult();
    }
}

