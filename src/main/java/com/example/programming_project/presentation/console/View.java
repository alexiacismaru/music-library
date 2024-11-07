package com.example.programming_project.presentation.console;

import com.example.programming_project.domain.Albums;
import com.example.programming_project.domain.Artist;
import com.example.programming_project.domain.SongGenres;
import com.example.programming_project.domain.Songs;
import com.example.programming_project.repository.extra.JsonWriter;
import com.example.programming_project.repository.hardcoded.HardcodedArtistRepository;
import com.example.programming_project.repository.hardcoded.HardcodedSongRepository;
import com.example.programming_project.service.AlbumService;
import com.example.programming_project.service.ArtistService;
import com.example.programming_project.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.programming_project.repository.hardcoded.HardcodedSongRepository.songs;
import static com.example.programming_project.repository.hardcoded.HardcodedAlbumRepository.albums;
import static com.example.programming_project.repository.hardcoded.HardcodedArtistRepository.artists;

@Component
@Profile("console")
public class View {
    private final Scanner keyboard = new Scanner(System.in);
    private final JsonWriter jsonWriter = new JsonWriter();
    private ArtistService artistService;
    private SongService songService;
    private AlbumService albumService;
    @Autowired
    public View(ArtistService artistService, SongService songService, AlbumService albumService) {
        this.artistService = artistService;
        this.songService = songService;
        this.albumService = albumService;
    }

    public void show() {
        while (true) {
            System.out.println("""
                    What would you like to do?
                    --------------------------
                    0) Quit
                    1) Show all albums
                    2) Show all artists
                    3) Show all songs
                    4) Show albums by artist
                    5) Show songs by their length or genre
                    6) Create a new artist
                    7) Create a new album
                    8) Create a new song""");
            System.out.println("Choice: ");
            int choice = keyboard.nextInt();

            switch (choice) {
                case 0 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                case 1 -> showAllAlbums();
                case 2 -> showAllArtists();
                case 3 -> showAllSongs();
                case 4 -> showAlbumByArtist();
                case 5 -> showSongsByDurationAndGenre();
                case 6 -> addArtist();
                case 7 -> addAlbum();
                case 8 -> addSong();
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void showAllAlbums() {
        System.out.println("All of the albums:");
        System.out.println("--------------------");
        albums.forEach(System.out::println);
        System.out.println("--------------------");
        System.out.println("Writing to json...");
        System.out.println("\n");
        jsonWriter.writeAlbums(albums);
    }

    private void showAllArtists() {
        System.out.println("All of the artists:");
        System.out.println("--------------------");
        artists.forEach(System.out::println);
        System.out.println("--------------------");
        System.out.println("Writing to json...");
        System.out.println("\n");
        jsonWriter.writeArtists(HardcodedArtistRepository.artists);
    }

    private void showAllSongs() {
        System.out.println("All of the songs:");
        System.out.println("------------------");
        songs.forEach(System.out::println);
        System.out.println("------------------");
        System.out.println("Writing to json...");
        System.out.println("\n");
        jsonWriter.writeSongs(HardcodedSongRepository.songs);
    }

    private void showAlbumByArtist() {
        System.out.println("Choose a artist:");
        List<Artist> artists = albums.stream().map(Albums::getArtist).distinct().toList();
        AtomicInteger i = new AtomicInteger(1);
        artists.forEach(artist -> System.out.println(i.getAndIncrement() + ") " + artist));
        int choice = keyboard.nextInt();
        if (choice <= 0 || choice > artists.size()) {
            System.out.println("Invalid choice");
        } else {
            System.out.println("Album/s made by " + artists.get(choice - 1));
            List<Albums> selection = albums.stream().filter(observation -> observation.getArtist().equals(artists.get(choice - 1))).toList();
            selection.forEach(System.out::println);
            System.out.println("Writing to json...");
            System.out.println("\n");
            jsonWriter.writeAlbums(selection);
        }
    }

    private void showSongsByDurationAndGenre() {
        keyboard.nextLine();
        System.out.println("Enter a song length (can be left empty):");
        String input = keyboard.nextLine();
        List<Songs> objects = songs;
        if (input.length() != 0) {
            int duration = Integer.parseInt(input);
            objects = objects.stream().filter(object -> object.getDuration() < duration).collect(Collectors.toList());
        }
        System.out.println("Choose a genre (can be left empty):");
        AtomicInteger i = new AtomicInteger(1);
        Stream.of(SongGenres.values()).forEach(objectType -> System.out.println(i.getAndIncrement() + ") " + objectType.toString()));
        input = keyboard.nextLine();
        if (input.length() != 0) {
            int choice = Integer.parseInt(input);
            if (choice <= 0 || choice > SongGenres.values().length) {
                System.out.println("Invalid choice");
            } else {
                objects = objects.stream().filter(object -> object.getGenre() == SongGenres.values()[choice - 1]).collect(Collectors.toList());
            }
        }
        objects.forEach(System.out::println);
        System.out.println("Writing to json...");
        System.out.println("\n");
        jsonWriter.writeSongs(objects);
    }

    private void addArtist() {
        keyboard.nextLine();
        System.out.println("Adding a artist:");
        System.out.print("\tName:");
        String name = keyboard.nextLine();
        System.out.print("\tGender:");
        String gender = keyboard.nextLine();
        System.out.print("\tDebut year:");
        int debut = keyboard.nextInt();

        Artist artist = new Artist(name, gender, debut);
        artistService.addArtist(artist);
    }

    private void addAlbum() {
        keyboard.nextLine();
        System.out.println("Adding an album:");
        System.out.print("\tTitle:");
        String title = keyboard.nextLine();
        System.out.print("\tAmount of songs:");
        int amount = keyboard.nextInt();
        System.out.print("\tRelease year:");
        int year = keyboard.nextInt();
        System.out.print("\tRelease month:");
        int month = keyboard.nextInt();
        System.out.print("\tRelease day:");
        int day = keyboard.nextInt();

        Albums album = new Albums(title, amount, LocalDate.of(year, month, day));
        albumService.addAlbum(album);
    }

    private void addSong() {
        keyboard.nextLine();
        System.out.println("Adding a song:");
        System.out.print("\tSong name:");
        String name = keyboard.nextLine();
        System.out.print("\tDuration of the song:");
        double duration = keyboard.nextDouble();
        System.out.println("\tSong genre:");
        SongGenres genre = SongGenres.valueOf(keyboard.next());

        Songs song = new Songs(name, duration, genre);
        songService.addSong(song);
    }
}