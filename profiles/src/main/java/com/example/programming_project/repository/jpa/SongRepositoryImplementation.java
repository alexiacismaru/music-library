package com.example.programming_project.repository.jpa;

import com.example.programming_project.domain.Song;
import com.example.programming_project.repository.SongRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
@Profile("jpa")
public class SongRepositoryImplementation implements SongRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Song> readSong() {
        return em.createQuery("SELECT songs from Song songs", Song.class).getResultList();
    }

    @Override
    public Song findSongById(int id) {
        return em.find(Song.class, id);
    }

    @Override
    @Transactional
    public Song createSong(Song song) {
        em.persist(song);
        return song;
    }

    @Override
    @Transactional
    public void deleteSong(int id) {
        em.remove(em.find(Song.class, id));
    }
}


