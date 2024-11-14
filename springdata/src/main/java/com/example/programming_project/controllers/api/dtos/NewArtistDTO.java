package com.example.programming_project.controllers.api.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewArtistDTO {
    @NotBlank
    private String name;
    private String profile;
    private String description;
}
