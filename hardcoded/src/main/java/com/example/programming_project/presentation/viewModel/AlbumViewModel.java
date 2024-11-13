package com.example.programming_project.presentation.viewModel;

import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Profile("web")
public class AlbumViewModel {
    private int id;

    @NotNull(message="Title is mandatory!")
    private String title;
    private int amountOfSongs;
    private @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate releaseDate;

    public AlbumViewModel() {
        title = "";
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmountOfSongs(int amountOfSongs) {
        this.amountOfSongs = amountOfSongs;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "AlbumViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amountOfSongs=" + amountOfSongs +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
