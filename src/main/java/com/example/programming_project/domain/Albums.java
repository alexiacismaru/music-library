package com.example.programming_project.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "album")
public class Albums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column(name = "amountofsongs", nullable = false, length = 50)
    private int amountOfSongs;
    @Column(name = "releasedate", nullable = false, length = 50)
    private LocalDate releaseDate;
    @ManyToMany
    @JoinTable(
            name = "songsonthealbum",
            joinColumns = @JoinColumn(name = "albumid"),
            inverseJoinColumns = @JoinColumn(name = "songid")
    )
    private List<Songs> songs = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "artistid")
    private Artist artist;

    public Albums(int id, String title, int amountOfSongs, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.amountOfSongs = amountOfSongs;
        this.releaseDate = releaseDate;
    }

    public Albums(String title, int amountOfSongs, LocalDate releaseDate) {
        this.title = title;
        this.amountOfSongs = amountOfSongs;
        this.releaseDate = releaseDate;
    }

    public Albums(){}

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addSong(Songs song){
        this.songs.add(song);
    }

    public String getTitle() {
        return title;
    }

    public int getAmountOfSongs() {
        return amountOfSongs;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Songs> getSongs() {
        return songs;
    }

    public void setSongs(List<Songs> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return String.format("'%s' has %d songs and was released on %s." , title, amountOfSongs, releaseDate);
    }
}
