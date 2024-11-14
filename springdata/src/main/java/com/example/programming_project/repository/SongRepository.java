package com.example.programming_project.repository;

import com.example.programming_project.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Repository
@Profile("springdata")
public interface SongRepository extends JpaRepository<Song, Integer>{
    @Query("select song from Song song where upper(song.songName) like upper(concat('%', :searchValue, '%'))")
    List<Song> findSongByName(@Param("searchValue") String searchValue);

    @Query("select song from Song song where upper(song.genre) like upper(concat('%', :searchValue, '%'))")
    List<Song> findSongByGenre(@Param("searchValue") String searchValue);
}
