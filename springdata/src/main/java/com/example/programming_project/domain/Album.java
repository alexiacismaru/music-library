package com.example.programming_project.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Column(name = "releasedate", length = 10)
    private LocalDate releaseDate;
    @OneToMany(orphanRemoval = true, mappedBy = "album")
    private transient List<Song> songs = new ArrayList<>();
    private String cover;
    @ManyToOne
    @JoinColumn(name = "artistid")
    private Artist artist;

    public Album(String title, LocalDate releaseDate, String cover) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.cover = cover;
    }

    public Album(long id, String title, LocalDate releaseDate, String cover) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.cover = cover;
    }
}
