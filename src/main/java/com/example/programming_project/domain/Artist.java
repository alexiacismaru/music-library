package com.example.programming_project.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    @Column
    private String gender;
    @Column(name = "debutyear", nullable = false, length = 50)
    private int debutYear;
    @OneToMany(mappedBy = "artist", fetch=FetchType.EAGER)
    private List<Albums> albums = new ArrayList<>();

    public Artist(int id, String name, String gender, int debutYear) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.debutYear = debutYear;
    }

    public Artist(String name, String gender, int debutYear) {
        this.name = name;
        this.gender = gender;
        this.debutYear = debutYear;
    }

    public Artist(){}

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getDebutYear() {
        return debutYear;
    }

    public void addAlbum(Albums album){
        this.albums.add(album);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDebutYear(int debutYear) {
        this.debutYear = debutYear;
    }

    public void setAlbums(List<Albums> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return String.format("%s is a %s, who debuted in %d.", name, gender, debutYear);
    }

    public List<Albums> getAlbums() {
        return albums;
    }
}
