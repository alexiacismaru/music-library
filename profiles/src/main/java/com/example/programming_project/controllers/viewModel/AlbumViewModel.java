package com.example.programming_project.controllers.viewModel;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Profile("web")
public class AlbumViewModel {
    private int id;
    @NotNull(message="Title is mandatory!")
    private String title;
    private @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate releaseDate;
    private String cover;

    public AlbumViewModel() {
        title = "";
    }
}
