package com.example.programming_project.presentation.viewModel;

import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Profile;

@Profile("web")
public class ArtistViewModel {
    private int id;
    @NotNull(message = "Name is mandatory!")
    private String name;

    @NotNull(message = "Gender is mandatory!")
    private String gender;
    private int debutYear;

    public ArtistViewModel() {
        name = "";
        gender = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDebutYear() {
        return debutYear;
    }

    public void setDebutYear(int debutYear) {
        this.debutYear = debutYear;
    }

    @Override
    public String toString() {
        return "ArtistViewModel{" +
                "id" + id + '\'' +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", debutYear=" + debutYear +
                '}';
    }
}
