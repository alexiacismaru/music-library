package com.example.programming_project.controllers.viewModel;

import com.example.programming_project.domain.SongGenres;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Profile;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Profile("web")
public class SongViewModel {
    private int id;
    @NotNull(message = "Song name is mandatory!")
    private String songName;
    private int duration;
    private SongGenres genre;
    private String audio;

    public SongViewModel() {
        songName = "";
    }
}
