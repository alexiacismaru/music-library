package com.example.programming_project.repository.springdata;

import com.example.programming_project.domain.Songs;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("springdata")
public interface SpringDataSongRepository extends JpaRepository<Songs, Integer>{
}
