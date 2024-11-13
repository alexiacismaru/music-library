package com.example.programming_project.repository.springdata;

import com.example.programming_project.domain.Albums;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Profile("springdata")
public interface SpringDataAlbumRepository extends JpaRepository<Albums, Integer> {
    @Query(value = "SELECT a FROM Albums a WHERE a.releaseDate < :date")
    List<Albums> findAlbumsOlderThan(LocalDate date);

    @Query("select distinct a from Albums a join fetch a.songs where a.id = ?1")
    Albums getAlbumsWithSongs(int albumId);
}
