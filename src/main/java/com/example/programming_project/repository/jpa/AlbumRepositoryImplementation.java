package com.example.programming_project.repository.jpa;

import com.example.programming_project.domain.Albums;
import com.example.programming_project.repository.AlbumRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jpa")
public class AlbumRepositoryImplementation implements AlbumRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Albums> readAlbums() {
        return em.createQuery("SELECT album from Albums album", Albums.class).getResultList();
    }

    @Override
    @Transactional
    public Albums createAlbum(Albums album) {
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
        em.remove(em.find(Albums.class, id));
    }

    @Override
    public Albums getAlbumsWithSongs(int albumId) {
        return em.createQuery("SELECT DISTINCT a FROM Albums a JOIN FETCH a.songs WHERE a.id = ?1", Albums.class).getSingleResult();
    }
}
