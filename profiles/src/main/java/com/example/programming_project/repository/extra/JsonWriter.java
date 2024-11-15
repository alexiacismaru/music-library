package com.example.programming_project.repository.extra;

import com.example.programming_project.domain.Album;
import com.example.programming_project.domain.Artist;
import com.example.programming_project.domain.Song;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class JsonWriter {
    private static final String ALBUMS_JSON = "albums.json";
    private static final String SONGS_JSON = "songs.json";
    private static  final String ARTISTS_JSON = "artists.json";
    private final Gson gson;

    public JsonWriter() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateTimeSerializer());
        gson = builder.create();
    }

    public void writeAlbum(List<Album> albums){
        String json = gson.toJson(albums);
        try (FileWriter writer = new FileWriter(ALBUMS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save albums to JSON", e);
        }
    }

    public void writeSong(List<Song> songs){
        String json = gson.toJson(songs);
        try (FileWriter writer = new FileWriter(SONGS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save songs to JSON", e);
        }
    }

    public void writeArtists(List<Artist> artists){
        String json = gson.toJson(artists);
        try (FileWriter writer = new FileWriter(ARTISTS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save artists to JSON", e);
        }
    }
}
