package com.example.programming_project.presentation.viewModel;

import com.example.programming_project.domain.SongGenres;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Profile;

@Profile("web")
public class SongViewModel {
    private int id;
    @NotNull(message = "Song name is mandatory!")
    private String songName;
    private int duration;
    private SongGenres genre;

    public SongViewModel() {
        songName = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public SongGenres getGenre() {
        return genre;
    }

    public void setGenre(SongGenres genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "SongViewModel{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", duration=" + duration +
                ", genre=" + genre +
                '}';
    }
}
