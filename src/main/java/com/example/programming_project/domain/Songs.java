package com.example.programming_project.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="song")
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "songname", nullable = false, length = 50)
    private String songName;
    @Column
    private double duration;
    @Enumerated(EnumType. STRING)
    private SongGenres genre;
    @ManyToMany(mappedBy = "songs")
    private List<Albums> albums = new ArrayList<>();

    public Songs(int id, String songName, double duration, SongGenres genre) {
        this.id = id;
        this.songName = songName;
        this.duration = duration;
        this.genre = genre;
    }

    public Songs(String songName, double duration, SongGenres genre) {
        this.songName = songName;
        this.duration = duration;
        this.genre = genre;
    }

    public Songs(){}

    public double getDuration() {
        return duration;
    }
    public SongGenres getGenre() {
        return genre;
    }

    public String getSongName() {
        return songName;
    }

    public void setAlbum(Albums album) {
        this.albums.add(album);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Albums> getAlbums() {
        return albums;
    }

    @Override
    public String toString() {
        return String.format("'%s' is is %.2f minutes long and is a %s song.", songName, duration, genre);
    }
}
