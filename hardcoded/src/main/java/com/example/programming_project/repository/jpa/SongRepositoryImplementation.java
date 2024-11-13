package com.example.programming_project.repository.jpa;

import com.example.programming_project.domain.Songs;
import com.example.programming_project.repository.SongRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jpa")
public class SongRepositoryImplementation implements SongRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Songs> readSongs() {
        return em.createQuery("SELECT songs from Songs songs", Songs.class).getResultList();
    }

    @Override
    public Songs findSongById(int id) {
        return em.find(Songs.class, id);
    }

    @Override
    @Transactional
    public Songs createSong(Songs song) {
        em.persist(song);
        return song;
    }

    @Override
    @Transactional
    public void deleteSong(int id) {
        em.remove(em.find(Songs.class, id));
    }
}
