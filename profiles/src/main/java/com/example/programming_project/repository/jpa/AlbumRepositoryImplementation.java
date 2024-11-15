package com.example.programming_project.repository.jpa;

import com.example.programming_project.domain.Album;
import com.example.programming_project.repository.AlbumRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
@Profile("jpa")
public class AlbumRepositoryImplementation implements AlbumRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Album> readAlbum() {
        return em.createQuery("SELECT album from Albums album", Album.class).getResultList();
    }

    @Override
    @Transactional
    public Album createAlbum(Album album) {
        em.persist(album);
        return album;
    }

//    @Override
//    @Transactional
//    public void updateAlbum(Albums album) {
//        em.merge(album);
//    }

    @Override
    @Transactional
    public void deleteAlbum(int id) {
        em.remove(em.find(Album.class, id));
    }

    @Override
    public Album getAlbumWithSongs(int albumId) {
        return em.createQuery("SELECT DISTINCT a FROM Albums a JOIN FETCH a.songs WHERE a.id = ?1", Album.class).getSingleResult();
    }
}
