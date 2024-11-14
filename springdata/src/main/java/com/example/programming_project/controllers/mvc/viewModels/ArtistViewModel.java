package com.example.programming_project.controllers.mvc.viewModels;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistViewModel {
    private long id;
    @NotBlank
    private String name;
    private String profile;
    private String description;
}
