package com.example.programming_project.controllers.viewModel;

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
public class ArtistViewModel {
    private int id;
    @NotNull(message = "Name is mandatory!")
    private String name;

    @NotNull(message = "Profile picture is mandatory!")
    private String profile;
    private String description;

    public ArtistViewModel() {
        name = "";
        profile = "";
    }
}
