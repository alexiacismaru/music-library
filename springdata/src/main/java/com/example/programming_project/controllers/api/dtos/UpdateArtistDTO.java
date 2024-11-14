package com.example.programming_project.controllers.api.dtos;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArtistDTO {
    private String name;
    private String profile;
}
