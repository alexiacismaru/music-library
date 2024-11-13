package com.example.programming_project.repository.springdata;

import com.example.programming_project.domain.Artist;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("springdata")
public interface SpringDataArtistRepository extends JpaRepository<Artist, Integer> {
    @Query("SELECT a FROM Artist a WHERE a.debutYear < 2000")
    List<Artist> findArtistThatDebutedBeforeYear();

    @Query("select a from Artist a join fetch a.albums where a.id = ?1")
    Artist getArtistWithAlbums(int artistId);
}
