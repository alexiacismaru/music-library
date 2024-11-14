package com.example.programming_project.domain;

import lombok.*;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(orphanRemoval = true, mappedBy = "artist")
    private List<Album> albums;
    private String profile;
    private String description;

    public Artist(String name, String profile, String description) {
        this.name = name;
        this.profile = profile;
        this.description = description;
    }

    public Artist(long id, String name, String profile, String description) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.description = description;
    }
}
