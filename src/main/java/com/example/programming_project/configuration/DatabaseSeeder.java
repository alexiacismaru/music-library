package com.example.programming_project.configuration;

import com.example.programming_project.domain.SongGenres;
import com.example.programming_project.domain.Songs;
import com.example.programming_project.domain.Artist;
import com.example.programming_project.domain.Albums;
import com.example.programming_project.repository.AlbumRepository;
import com.example.programming_project.repository.ArtistRepository;
import com.example.programming_project.repository.SongRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.example.programming_project.repository.hardcoded.HardcodedAlbumRepository.albums;
import static com.example.programming_project.repository.hardcoded.HardcodedArtistRepository.artists;
import static com.example.programming_project.repository.hardcoded.HardcodedSongRepository.songs;

@Component
@Profile("hardcoded")
public class DatabaseSeeder implements CommandLineRunner {
    private SongRepository songRepository;
    private ArtistRepository artistRepository;
    private AlbumRepository albumRepository;

    public DatabaseSeeder(SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /* The Strokes */
        Songs song1_newAbnormal = new Songs("The Adults Are Talking", 5.07, SongGenres.alternative);
        Songs song2_newAbnormal = new Songs("Selfless", 3.41, SongGenres.alternative);
        Songs song3_newAbnormal = new Songs("Brooklyn Bridge To Chorus", 3.55, SongGenres.alternative);
        Songs song4_newAbnormal = new Songs("Bad Decisions", 4.53, SongGenres.alternative);
        Songs song5_newAbnormal = new Songs("Eternal Summer", 6.14, SongGenres.alternative);
        Songs song6_newAbnormal = new Songs("At The Door", 5.10, SongGenres.alternative);
        Songs song7_newAbnormal = new Songs("Why Are Sundays So Depressing", 4.35, SongGenres.alternative);
        Songs song8_newAbnormal = new Songs("Not The Same Anymore", 5.37, SongGenres.alternative);
        Songs song9_newAbnormal = new Songs("Ode To The Mets", 5.51, SongGenres.alternative);

        Songs song1_isThisIt = new Songs("Is this it", 2.31, SongGenres.rock);
        Songs song2_isThisIt = new Songs("The Modern Age", 3.28, SongGenres.rock);
        Songs song3_isThisIt = new Songs("Alone, Together", 3.08, SongGenres.rock);
        Songs song4_isThisIt = new Songs("Soma", 2.33, SongGenres.rock);
        Songs song5_isThisIt = new Songs("Hard To Explain", 3.44, SongGenres.rock);
        Songs song6_isThisIt = new Songs("Barely Legal", 3.54, SongGenres.rock);
        Songs song7_isThisIt = new Songs("Last Nite", 3.13, SongGenres.rock);
        Songs song8_isThisIt = new Songs("Someday", 3.03, SongGenres.rock);
        Songs song9_isThisIt = new Songs("Trying Your Luck", 3.22, SongGenres.rock);
        Songs song10_isThisIt = new Songs("New York City Cops", 3.30, SongGenres.rock);
        Songs song11_isThisIt = new Songs("Take It Or Leave It", 3.15, SongGenres.rock);

        songs.add(song1_newAbnormal);
        songs.add(song2_newAbnormal);
        songs.add(song3_newAbnormal);
        songs.add(song4_newAbnormal);
        songs.add(song5_newAbnormal);
        songs.add(song6_newAbnormal);
        songs.add(song7_newAbnormal);
        songs.add(song8_newAbnormal);
        songs.add(song9_newAbnormal);

        songs.add(song1_isThisIt);
        songs.add(song2_isThisIt);
        songs.add(song3_isThisIt);
        songs.add(song4_isThisIt);
        songs.add(song5_isThisIt);
        songs.add(song6_isThisIt);
        songs.add(song7_isThisIt);
        songs.add(song8_isThisIt);
        songs.add(song9_isThisIt);
        songs.add(song10_isThisIt);
        songs.add(song11_isThisIt);

        Albums album1_theStrokes = new Albums("The New Abnormal", 10, LocalDate.of(2020,4,10));
        Albums album2_theStrokes = new Albums("Is This It", 11, LocalDate.of(2001, 7, 30));

        albums.add(album1_theStrokes);
        albums.add(album2_theStrokes);

        album1_theStrokes.addSong(song1_newAbnormal);
        album1_theStrokes.addSong(song2_newAbnormal);
        album1_theStrokes.addSong(song3_newAbnormal);
        album1_theStrokes.addSong(song4_newAbnormal);
        album1_theStrokes.addSong(song5_newAbnormal);
        album1_theStrokes.addSong(song6_newAbnormal);
        album1_theStrokes.addSong(song7_newAbnormal);
        album1_theStrokes.addSong(song8_newAbnormal);
        album1_theStrokes.addSong(song9_newAbnormal);

        album2_theStrokes.addSong(song1_isThisIt);
        album2_theStrokes.addSong(song2_isThisIt);
        album2_theStrokes.addSong(song3_isThisIt);
        album2_theStrokes.addSong(song4_isThisIt);
        album2_theStrokes.addSong(song5_isThisIt);
        album2_theStrokes.addSong(song6_isThisIt);
        album2_theStrokes.addSong(song7_isThisIt);
        album2_theStrokes.addSong(song8_isThisIt);
        album2_theStrokes.addSong(song9_isThisIt);
        album2_theStrokes.addSong(song10_isThisIt);
        album2_theStrokes.addSong(song11_isThisIt);

        song1_newAbnormal.setAlbum(album1_theStrokes);
        song2_newAbnormal.setAlbum(album1_theStrokes);
        song3_newAbnormal.setAlbum(album1_theStrokes);
        song4_newAbnormal.setAlbum(album1_theStrokes);
        song5_newAbnormal.setAlbum(album1_theStrokes);
        song6_newAbnormal.setAlbum(album1_theStrokes);
        song7_newAbnormal.setAlbum(album1_theStrokes);
        song8_newAbnormal.setAlbum(album1_theStrokes);
        song9_newAbnormal.setAlbum(album1_theStrokes);

        song1_isThisIt.setAlbum(album2_theStrokes);
        song2_isThisIt.setAlbum(album2_theStrokes);
        song3_isThisIt.setAlbum(album2_theStrokes);
        song4_isThisIt.setAlbum(album2_theStrokes);
        song5_isThisIt.setAlbum(album2_theStrokes);
        song6_isThisIt.setAlbum(album2_theStrokes);
        song7_isThisIt.setAlbum(album2_theStrokes);
        song8_isThisIt.setAlbum(album2_theStrokes);
        song9_isThisIt.setAlbum(album2_theStrokes);
        song10_isThisIt.setAlbum(album2_theStrokes);
        song11_isThisIt.setAlbum(album2_theStrokes);

        Artist theStrokes = new Artist("The Strokes", "male group", 1998);

        artists.add(theStrokes);

        album1_theStrokes.setArtist(theStrokes);
        album2_theStrokes.setArtist(theStrokes);
        theStrokes.addAlbum(album1_theStrokes);
        theStrokes.addAlbum(album2_theStrokes);

        /* TV Girl */
        Songs song1_frenchExit = new Songs("Pantyhose", 2.55, SongGenres.pop);
        Songs song2_frenchExit = new Songs("Louise", 3.13, SongGenres.pop);
        Songs song3_frenchExit = new Songs("Lovers Rock", 3.33, SongGenres.pop);
        Songs song4_frenchExit = new Songs("Anjela", 3.43, SongGenres.pop);
        Songs song5_frenchExit = new Songs("Talk To Strangers", 2.57, SongGenres.pop);
        Songs song6_frenchExit = new Songs("Daughter of a Cop", 2.33, SongGenres.pop);
        Songs song7_frenchExit = new Songs("Hate Yourself", 3.33, SongGenres.pop);
        Songs song8_frenchExit = new Songs("Her and Her Friend", 3.28, SongGenres.pop);
        Songs song9_frenchExit = new Songs("The Blonde", 3.47, SongGenres.pop);
        Songs song10_frenchExit = new Songs("Come When You Call", 3.38, SongGenres.pop);
        Songs song11_frenchExit = new Songs("The Gateway", 3.43, SongGenres.pop);
        Songs song12_frenchExit = new Songs("Birds Dont Sing", 3.28, SongGenres.pop);

        songs.add(song1_frenchExit);
        songs.add(song2_frenchExit);
        songs.add(song3_frenchExit);
        songs.add(song4_frenchExit);
        songs.add(song5_frenchExit);
        songs.add(song6_frenchExit);
        songs.add(song7_frenchExit);
        songs.add(song8_frenchExit);
        songs.add(song9_frenchExit);
        songs.add(song10_frenchExit);
        songs.add(song11_frenchExit);
        songs.add(song12_frenchExit);

        Albums album3_TVGirl = new Albums("French Exit", 12, LocalDate.of(2014, 6, 5));

        albums.add(album3_TVGirl);

        album3_TVGirl.addSong(song1_frenchExit);
        album3_TVGirl.addSong(song2_frenchExit);
        album3_TVGirl.addSong(song3_frenchExit);
        album3_TVGirl.addSong(song4_frenchExit);
        album3_TVGirl.addSong(song5_frenchExit);
        album3_TVGirl.addSong(song6_frenchExit);
        album3_TVGirl.addSong(song7_frenchExit);
        album3_TVGirl.addSong(song8_frenchExit);
        album3_TVGirl.addSong(song9_frenchExit);
        album3_TVGirl.addSong(song10_frenchExit);
        album3_TVGirl.addSong(song11_frenchExit);
        album3_TVGirl.addSong(song12_frenchExit);

        song1_frenchExit.setAlbum(album3_TVGirl);
        song2_frenchExit.setAlbum(album3_TVGirl);
        song3_frenchExit.setAlbum(album3_TVGirl);
        song4_frenchExit.setAlbum(album3_TVGirl);
        song5_frenchExit.setAlbum(album3_TVGirl);
        song6_frenchExit.setAlbum(album3_TVGirl);
        song7_frenchExit.setAlbum(album3_TVGirl);
        song8_frenchExit.setAlbum(album3_TVGirl);
        song9_frenchExit.setAlbum(album3_TVGirl);
        song10_frenchExit.setAlbum(album3_TVGirl);
        song11_frenchExit.setAlbum(album3_TVGirl);
        song12_frenchExit.setAlbum(album3_TVGirl);

        Artist TVGirl = new Artist("TV Girl", "mixed group", 2010);

        artists.add(TVGirl);

        album3_TVGirl.setArtist(TVGirl);
        TVGirl.addAlbum(album3_TVGirl);

        /* Radiohead */
        Songs song1_theBends = new Songs("Planet Telex", 4.18, SongGenres.rock);
        Songs song2_theBends = new Songs("My Iron Lung", 4.36, SongGenres.rock);
        Songs song3_theBends = new Songs("The Bends", 4.05, SongGenres.rock);
        Songs song4_theBends = new Songs("Bullet Proof...I Wish I Was", 3.28, SongGenres.rock);
        Songs song5_theBends = new Songs("Street Spirit (Fade Out)", 4.13, SongGenres.rock);
        Songs song6_theBends = new Songs("(Nice Dream)", 3.53, SongGenres.rock);
        Songs song7_theBends = new Songs("Just", 3.54, SongGenres.rock);
        Songs song8_theBends = new Songs("Black Star", 4.06, SongGenres.rock);
        Songs song9_theBends = new Songs("Fake Plastic Trees", 4.50, SongGenres.rock);
        Songs song10_theBends = new Songs("Sulk", 3.42, SongGenres.rock);
        Songs song11_theBends = new Songs("High and Dry", 4.17, SongGenres.rock);
        Songs song12_theBends = new Songs("Bones", 3.08, SongGenres.rock);

        Songs song1_OKComputer = new Songs("Airbag", 4.47, SongGenres.rock);
        Songs song2_OKComputer = new Songs("Karma Police", 4.24, SongGenres.rock);
        Songs song3_OKComputer = new Songs("Subterranean Homesick Alien", 4.27, SongGenres.rock);
        Songs song4_OKComputer = new Songs("Lucky", 4.18, SongGenres.rock);
        Songs song5_OKComputer = new Songs("Exit Music (For A Film)", 4.27, SongGenres.rock);
        Songs song6_OKComputer = new Songs("Electioneering", 3.50, SongGenres.rock);
        Songs song7_OKComputer = new Songs("Just", 3.54, SongGenres.rock);
        Songs song8_OKComputer = new Songs("The Tourist", 5.26, SongGenres.rock);
        Songs song9_OKComputer = new Songs("Paranoid Android", 6.27, SongGenres.rock);
        Songs song10_OKComputer = new Songs("Fitter Happier", 1.57, SongGenres.rock);
        Songs song11_OKComputer = new Songs("Climbing Up the Walls", 4.45, SongGenres.rock);
        Songs song12_OKComputer = new Songs("Let Down", 4.59, SongGenres.rock);
        Songs song13_OKComputer = new Songs("No Surprises", 3.48, SongGenres.rock);

        Songs song1_inRainbows = new Songs("15 Step", 3.56, SongGenres.alternative);
        Songs song2_inRainbows = new Songs("Reckoner", 4.49, SongGenres.alternative);
        Songs song3_inRainbows = new Songs("Bodysnatchers", 4.01, SongGenres.alternative);
        Songs song4_inRainbows = new Songs("Nude", 4.15, SongGenres.alternative);
        Songs song5_inRainbows = new Songs("Weird Fishes/Arpeggi", 5.18, SongGenres.alternative);
        Songs song6_inRainbows = new Songs("Faust Arp", 2.09, SongGenres.alternative);
        Songs song7_inRainbows = new Songs("House Of Cards", 5.27, SongGenres.alternative);
        Songs song8_inRainbows = new Songs("All I Need", 3.48, SongGenres.alternative);
        Songs song9_inRainbows = new Songs("Videotape", 4.39, SongGenres.alternative);
        Songs song10_inRainbows = new Songs("Jigsaw Falling Into Place", 4.08, SongGenres.alternative);

        songs.add(song1_theBends);
        songs.add(song2_theBends);
        songs.add(song3_theBends);
        songs.add(song4_theBends);
        songs.add(song5_theBends);
        songs.add(song6_theBends);
        songs.add(song7_theBends);
        songs.add(song8_theBends);
        songs.add(song9_theBends);
        songs.add(song10_theBends);
        songs.add(song11_theBends);
        songs.add(song12_theBends);

        songs.add(song1_OKComputer);
        songs.add(song2_OKComputer);
        songs.add(song3_OKComputer);
        songs.add(song4_OKComputer);
        songs.add(song5_OKComputer);
        songs.add(song6_OKComputer);
        songs.add(song7_OKComputer);
        songs.add(song8_OKComputer);
        songs.add(song9_OKComputer);
        songs.add(song10_OKComputer);
        songs.add(song11_OKComputer);
        songs.add(song12_OKComputer);
        songs.add(song13_OKComputer);

        songs.add(song1_inRainbows);
        songs.add(song2_inRainbows);
        songs.add(song3_inRainbows);
        songs.add(song4_inRainbows);
        songs.add(song5_inRainbows);
        songs.add(song6_inRainbows);
        songs.add(song7_inRainbows);
        songs.add(song8_inRainbows);
        songs.add(song9_inRainbows);
        songs.add(song10_inRainbows);

        Albums album4_radiohead = new Albums("The Bends", 12, LocalDate. of(1995, 3, 13));
        Albums album5_radiohead = new Albums("OK Computer", 13, LocalDate.of(1997, 5, 28));
        Albums album6_radiohead = new Albums("In Rainbows", 10, LocalDate.of(2007, 12, 28));

        albums.add(album4_radiohead);
        albums.add(album5_radiohead);
        albums.add(album6_radiohead);

        album4_radiohead.addSong(song1_theBends);
        album4_radiohead.addSong(song2_theBends);
        album4_radiohead.addSong(song3_theBends);
        album4_radiohead.addSong(song4_theBends);
        album4_radiohead.addSong(song5_theBends);
        album4_radiohead.addSong(song6_theBends);
        album4_radiohead.addSong(song7_theBends);
        album4_radiohead.addSong(song8_theBends);
        album4_radiohead.addSong(song9_theBends);
        album4_radiohead.addSong(song10_theBends);
        album4_radiohead.addSong(song11_theBends);
        album4_radiohead.addSong(song12_theBends);

        album5_radiohead.addSong(song1_OKComputer);
        album5_radiohead.addSong(song2_OKComputer);
        album5_radiohead.addSong(song3_OKComputer);
        album5_radiohead.addSong(song4_OKComputer);
        album5_radiohead.addSong(song5_OKComputer);
        album5_radiohead.addSong(song6_OKComputer);
        album5_radiohead.addSong(song7_OKComputer);
        album5_radiohead.addSong(song8_OKComputer);
        album5_radiohead.addSong(song9_OKComputer);
        album5_radiohead.addSong(song10_OKComputer);
        album5_radiohead.addSong(song11_OKComputer);
        album5_radiohead.addSong(song12_OKComputer);
        album5_radiohead.addSong(song13_OKComputer);

        album6_radiohead.addSong(song1_inRainbows);
        album6_radiohead.addSong(song2_inRainbows);
        album6_radiohead.addSong(song3_inRainbows);
        album6_radiohead.addSong(song4_inRainbows);
        album6_radiohead.addSong(song5_inRainbows);
        album6_radiohead.addSong(song6_inRainbows);
        album6_radiohead.addSong(song7_inRainbows);
        album6_radiohead.addSong(song8_inRainbows);
        album6_radiohead.addSong(song9_inRainbows);
        album6_radiohead.addSong(song10_inRainbows);

        song1_theBends.setAlbum(album4_radiohead);
        song2_theBends.setAlbum(album4_radiohead);
        song3_theBends.setAlbum(album4_radiohead);
        song4_theBends.setAlbum(album4_radiohead);
        song5_theBends.setAlbum(album4_radiohead);
        song6_theBends.setAlbum(album4_radiohead);
        song7_theBends.setAlbum(album4_radiohead);
        song8_theBends.setAlbum(album4_radiohead);
        song9_theBends.setAlbum(album4_radiohead);
        song10_theBends.setAlbum(album4_radiohead);
        song11_theBends.setAlbum(album4_radiohead);
        song12_theBends.setAlbum(album4_radiohead);

        song1_OKComputer.setAlbum(album5_radiohead);
        song2_OKComputer.setAlbum(album5_radiohead);
        song3_OKComputer.setAlbum(album5_radiohead);
        song4_OKComputer.setAlbum(album5_radiohead);
        song5_OKComputer.setAlbum(album5_radiohead);
        song6_OKComputer.setAlbum(album5_radiohead);
        song7_OKComputer.setAlbum(album5_radiohead);
        song8_OKComputer.setAlbum(album5_radiohead);
        song9_OKComputer.setAlbum(album5_radiohead);
        song10_OKComputer.setAlbum(album5_radiohead);
        song11_OKComputer.setAlbum(album5_radiohead);
        song12_OKComputer.setAlbum(album5_radiohead);
        song13_OKComputer.setAlbum(album5_radiohead);

        song1_inRainbows.setAlbum(album6_radiohead);
        song2_inRainbows.setAlbum(album6_radiohead);
        song3_inRainbows.setAlbum(album6_radiohead);
        song4_inRainbows.setAlbum(album6_radiohead);
        song5_inRainbows.setAlbum(album6_radiohead);
        song6_inRainbows.setAlbum(album6_radiohead);
        song7_inRainbows.setAlbum(album6_radiohead);
        song8_inRainbows.setAlbum(album6_radiohead);
        song9_inRainbows.setAlbum(album6_radiohead);
        song10_inRainbows.setAlbum(album6_radiohead);

        Artist radiohead = new Artist("Radiohead", "male group", 1985);

        artists.add(radiohead);

        album4_radiohead.setArtist(radiohead);
        album5_radiohead.setArtist(radiohead);
        album6_radiohead.setArtist(radiohead);
        radiohead.addAlbum(album4_radiohead);
        radiohead.addAlbum(album5_radiohead);
        radiohead.addAlbum(album6_radiohead);

        /* Brockhampton */
        Songs song1_saturation = new Songs("HEAT", 4.31, SongGenres.rap);
        Songs song2_saturation = new Songs("FACE", 4.18, SongGenres.rap);
        Songs song3_saturation = new Songs("BOYS", 4.37, SongGenres.rap);
        Songs song4_saturation = new Songs("STAR", 2.40, SongGenres.rap);
        Songs song5_saturation = new Songs("2PAC", 1.02, SongGenres.rap);
        Songs song6_saturation = new Songs("SWIM", 3.33, SongGenres.rap);
        Songs song7_saturation = new Songs("BUMP", 2.37, SongGenres.rap);
        Songs song8_saturation = new Songs("SKIT 3", 0.38, SongGenres.rap);
        Songs song9_saturation = new Songs("MILK", 4.54, SongGenres.rap);
        Songs song10_saturation = new Songs("WASTE", 2.34, SongGenres.rap);
        Songs song11_saturation = new Songs("CASH", 3.14, SongGenres.rap);
        Songs song12_saturation = new Songs("BANK", 3.15, SongGenres.rap);
        Songs song13_saturation = new Songs("TRIP", 0.38, SongGenres.rap);
        Songs song14_saturation = new Songs("SKIT 2", 0.15, SongGenres.rap);
        Songs song15_saturation = new Songs("FAKE", 4.35, SongGenres.rap);
        Songs song16_saturation = new Songs("GOLD", 4.25, SongGenres.rap);
        Songs song17_saturation = new Songs("SKIT 1", 0.19, SongGenres.rap);

        songs.add(song1_saturation);
        songs.add(song2_saturation);
        songs.add(song3_saturation);
        songs.add(song4_saturation);
        songs.add(song5_saturation);
        songs.add(song6_saturation);
        songs.add(song7_saturation);
        songs.add(song8_saturation);
        songs.add(song9_saturation);
        songs.add(song10_saturation);
        songs.add(song11_saturation);
        songs.add(song12_saturation);
        songs.add(song13_saturation);
        songs.add(song14_saturation);
        songs.add(song15_saturation);
        songs.add(song16_saturation);
        songs.add(song17_saturation);

        Albums album7_brockhampton = new Albums("SATURATION", 17, LocalDate.of(2017, 6, 9));

        albums.add(album7_brockhampton);

        album7_brockhampton.addSong(song1_saturation);
        album7_brockhampton.addSong(song2_saturation);
        album7_brockhampton.addSong(song3_saturation);
        album7_brockhampton.addSong(song4_saturation);
        album7_brockhampton.addSong(song5_saturation);
        album7_brockhampton.addSong(song6_saturation);
        album7_brockhampton.addSong(song7_saturation);
        album7_brockhampton.addSong(song8_saturation);
        album7_brockhampton.addSong(song9_saturation);
        album7_brockhampton.addSong(song10_saturation);
        album7_brockhampton.addSong(song11_saturation);
        album7_brockhampton.addSong(song12_saturation);
        album7_brockhampton.addSong(song13_saturation);
        album7_brockhampton.addSong(song14_saturation);
        album7_brockhampton.addSong(song15_saturation);
        album7_brockhampton.addSong(song16_saturation);
        album7_brockhampton.addSong(song17_saturation);

        song1_saturation.setAlbum(album7_brockhampton);
        song2_saturation.setAlbum(album7_brockhampton);
        song3_saturation.setAlbum(album7_brockhampton);
        song4_saturation.setAlbum(album7_brockhampton);
        song5_saturation.setAlbum(album7_brockhampton);
        song6_saturation.setAlbum(album7_brockhampton);
        song7_saturation.setAlbum(album7_brockhampton);
        song8_saturation.setAlbum(album7_brockhampton);
        song9_saturation.setAlbum(album7_brockhampton);
        song10_saturation.setAlbum(album7_brockhampton);
        song11_saturation.setAlbum(album7_brockhampton);
        song12_saturation.setAlbum(album7_brockhampton);
        song13_saturation.setAlbum(album7_brockhampton);
        song14_saturation.setAlbum(album7_brockhampton);
        song15_saturation.setAlbum(album7_brockhampton);
        song16_saturation.setAlbum(album7_brockhampton);
        song17_saturation.setAlbum(album7_brockhampton);

        Artist brockhampton = new Artist("Brockhampton", "male group", 2010);

        artists.add(brockhampton);

        album7_brockhampton.setArtist(brockhampton);
        brockhampton.addAlbum(album7_brockhampton);

        /* Regina Spektor */
        Songs song1_sovietKitsch = new Songs("Ode to Divorce", 3.42, SongGenres.pop);
        Songs song2_sovietKitsch = new Songs("Poor Little Rick Boy", 2.27, SongGenres.pop);
        Songs song3_sovietKitsch = new Songs("Carbon Monoxide", 4.59, SongGenres.pop);
        Songs song4_sovietKitsch = new Songs("The Flowers", 3.54, SongGenres.pop);
        Songs song5_sovietKitsch = new Songs("Us", 4.51, SongGenres.pop);
        Songs song6_sovietKitsch = new Songs("Sailor Song", 3.15, SongGenres.pop);
        Songs song7_sovietKitsch = new Songs("Whisper", 0.44, SongGenres.pop);
        Songs song8_sovietKitsch = new Songs("Your Honour", 2.09, SongGenres.pop);
        Songs song9_sovietKitsch = new Songs("Ghost of Corporate Future", 3.21, SongGenres.pop);
        Songs song10_sovietKitsch = new Songs("Chemo Limo", 6.04, SongGenres.pop);
        Songs song11_sovietKitsch = new Songs("Somedays", 3.21, SongGenres.pop);

        Songs song1_whatWeSaw = new Songs("Small Town Moon", 2.58, SongGenres.pop);
        Songs song2_whatWeSaw = new Songs("Oh Marcello", 2.36, SongGenres.pop);
        Songs song3_whatWeSaw = new Songs("Don't Leave Me (Ne me quitte pas)", 3.36, SongGenres.pop);
        Songs song4_whatWeSaw = new Songs("Firewood", 4.51, SongGenres.pop);
        Songs song5_whatWeSaw = new Songs("Patron Saint", 3.38, SongGenres.pop);
        Songs song6_whatWeSaw = new Songs("How", 4.44, SongGenres.pop);
        Songs song7_whatWeSaw = new Songs("All the Rowboats", 3.33, SongGenres.pop);
        Songs song8_whatWeSaw = new Songs("Ballad of a Politician", 2.12, SongGenres.pop);
        Songs song9_whatWeSaw = new Songs("Open", 4.27, SongGenres.pop);
        Songs song10_whatWeSaw = new Songs("The Party", 2.25, SongGenres.pop);
        Songs song11_whatWeSaw = new Songs("Jessica", 1.44, SongGenres.pop);

        songs.add(song1_sovietKitsch);
        songs.add(song2_sovietKitsch);
        songs.add(song3_sovietKitsch);
        songs.add(song4_sovietKitsch);
        songs.add(song5_sovietKitsch);
        songs.add(song6_sovietKitsch);
        songs.add(song7_sovietKitsch);
        songs.add(song8_sovietKitsch);
        songs.add(song9_sovietKitsch);
        songs.add(song10_sovietKitsch);
        songs.add(song11_sovietKitsch);

        songs.add(song1_whatWeSaw);
        songs.add(song2_whatWeSaw);
        songs.add(song3_whatWeSaw);
        songs.add(song4_whatWeSaw);
        songs.add(song5_whatWeSaw);
        songs.add(song6_whatWeSaw);
        songs.add(song7_whatWeSaw);
        songs.add(song8_whatWeSaw);
        songs.add(song9_whatWeSaw);
        songs.add(song10_whatWeSaw);
        songs.add(song11_whatWeSaw);

        Albums album8_reginaSpektor = new Albums("Soviet Kitsch", 11, LocalDate.of(2004, 9, 17));
        Albums album9_reginaSpektor = new Albums("What We Saw from the Cheap Seats", 11, LocalDate.of(2012, 5, 25));

        albums.add(album8_reginaSpektor);

        album8_reginaSpektor.addSong(song1_sovietKitsch);
        album8_reginaSpektor.addSong(song2_sovietKitsch);
        album8_reginaSpektor.addSong(song3_sovietKitsch);
        album8_reginaSpektor.addSong(song4_sovietKitsch);
        album8_reginaSpektor.addSong(song5_sovietKitsch);
        album8_reginaSpektor.addSong(song6_sovietKitsch);
        album8_reginaSpektor.addSong(song7_sovietKitsch);
        album8_reginaSpektor.addSong(song8_sovietKitsch);
        album8_reginaSpektor.addSong(song9_sovietKitsch);
        album8_reginaSpektor.addSong(song10_sovietKitsch);
        album8_reginaSpektor.addSong(song11_sovietKitsch);

        album9_reginaSpektor.addSong(song1_whatWeSaw);
        album9_reginaSpektor.addSong(song2_whatWeSaw);
        album9_reginaSpektor.addSong(song3_whatWeSaw);
        album9_reginaSpektor.addSong(song4_whatWeSaw);
        album9_reginaSpektor.addSong(song5_whatWeSaw);
        album9_reginaSpektor.addSong(song6_whatWeSaw);
        album9_reginaSpektor.addSong(song7_whatWeSaw);
        album9_reginaSpektor.addSong(song8_whatWeSaw);
        album9_reginaSpektor.addSong(song9_whatWeSaw);
        album9_reginaSpektor.addSong(song10_whatWeSaw);
        album9_reginaSpektor.addSong(song11_whatWeSaw);

        song1_sovietKitsch.setAlbum(album8_reginaSpektor);
        song2_sovietKitsch.setAlbum(album8_reginaSpektor);
        song3_sovietKitsch.setAlbum(album8_reginaSpektor);
        song4_sovietKitsch.setAlbum(album8_reginaSpektor);
        song5_sovietKitsch.setAlbum(album8_reginaSpektor);
        song6_sovietKitsch.setAlbum(album8_reginaSpektor);
        song7_sovietKitsch.setAlbum(album8_reginaSpektor);
        song8_sovietKitsch.setAlbum(album8_reginaSpektor);
        song9_sovietKitsch.setAlbum(album8_reginaSpektor);
        song10_sovietKitsch.setAlbum(album8_reginaSpektor);
        song11_sovietKitsch.setAlbum(album8_reginaSpektor);

        song1_whatWeSaw.setAlbum(album9_reginaSpektor);
        song2_whatWeSaw.setAlbum(album9_reginaSpektor);
        song3_whatWeSaw.setAlbum(album9_reginaSpektor);
        song4_whatWeSaw.setAlbum(album9_reginaSpektor);
        song5_whatWeSaw.setAlbum(album9_reginaSpektor);
        song6_whatWeSaw.setAlbum(album9_reginaSpektor);
        song7_whatWeSaw.setAlbum(album9_reginaSpektor);
        song8_whatWeSaw.setAlbum(album9_reginaSpektor);
        song9_whatWeSaw.setAlbum(album9_reginaSpektor);
        song10_whatWeSaw.setAlbum(album9_reginaSpektor);
        song11_whatWeSaw.setAlbum(album9_reginaSpektor);

        Artist reginaSpektor = new Artist("Regina Spektor", "female artist", 1997);

        artists.add(reginaSpektor);

        album8_reginaSpektor.setArtist(reginaSpektor);
        album9_reginaSpektor.setArtist(reginaSpektor);
        reginaSpektor.addAlbum(album8_reginaSpektor);
        reginaSpektor.addAlbum(album9_reginaSpektor);

        /* Elliot Smith */
        Songs song1_eitherOr = new Songs("Speed Trials", 3.02, SongGenres.alternative);
        Songs song2_eitherOr = new Songs("Alameda", 3.42, SongGenres.alternative);
        Songs song3_eitherOr = new Songs("Ballad Of Big Nothing", 2.48, SongGenres.alternative);
        Songs song4_eitherOr = new Songs("Between The Bars", 2.21, SongGenres.alternative);
        Songs song5_eitherOr = new Songs("Pictures Of Me", 3.46, SongGenres.alternative);
        Songs song6_eitherOr = new Songs("No Name No.5", 3.42, SongGenres.alternative);
        Songs song7_eitherOr = new Songs("Rose Parade", 3.28, SongGenres.alternative);
        Songs song8_eitherOr = new Songs("Punch And Judy", 2.25, SongGenres.alternative);
        Songs song9_eitherOr = new Songs("Angeles", 2.56, SongGenres.alternative);
        Songs song10_eitherOr = new Songs("Cupid's Trick", 3.04, SongGenres.alternative);
        Songs song11_eitherOr = new Songs("2:45 AM", 3.18, SongGenres.alternative);
        Songs song12_eitherOr = new Songs("Say Yes", 2.19, SongGenres.alternative);

        songs.add(song1_eitherOr);
        songs.add(song2_eitherOr);
        songs.add(song3_eitherOr);
        songs.add(song4_eitherOr);
        songs.add(song5_eitherOr);
        songs.add(song6_eitherOr);
        songs.add(song7_eitherOr);
        songs.add(song8_eitherOr);
        songs.add(song9_eitherOr);
        songs.add(song10_eitherOr);
        songs.add(song11_eitherOr);
        songs.add(song12_eitherOr);

        Albums album10_elliottSmith = new Albums("Either/Or", 12, LocalDate.of(1997, 2, 25));

        albums.add(album10_elliottSmith);

        album10_elliottSmith.addSong(song1_eitherOr);
        album10_elliottSmith.addSong(song2_eitherOr);
        album10_elliottSmith.addSong(song3_eitherOr);
        album10_elliottSmith.addSong(song4_eitherOr);
        album10_elliottSmith.addSong(song5_eitherOr);
        album10_elliottSmith.addSong(song6_eitherOr);
        album10_elliottSmith.addSong(song7_eitherOr);
        album10_elliottSmith.addSong(song8_eitherOr);
        album10_elliottSmith.addSong(song9_eitherOr);
        album10_elliottSmith.addSong(song10_eitherOr);
        album10_elliottSmith.addSong(song11_eitherOr);
        album10_elliottSmith.addSong(song12_eitherOr);

        song1_eitherOr.setAlbum(album10_elliottSmith);
        song2_eitherOr.setAlbum(album10_elliottSmith);
        song3_eitherOr.setAlbum(album10_elliottSmith);
        song4_eitherOr.setAlbum(album10_elliottSmith);
        song5_eitherOr.setAlbum(album10_elliottSmith);
        song6_eitherOr.setAlbum(album10_elliottSmith);
        song7_eitherOr.setAlbum(album10_elliottSmith);
        song8_eitherOr.setAlbum(album10_elliottSmith);
        song9_eitherOr.setAlbum(album10_elliottSmith);
        song10_eitherOr.setAlbum(album10_elliottSmith);
        song11_eitherOr.setAlbum(album10_elliottSmith);
        song12_eitherOr.setAlbum(album10_elliottSmith);

        Artist elliottSmith = new Artist("Elliott Smith", "male artist", 1994);

        artists.add(elliottSmith);

        album10_elliottSmith.setArtist(elliottSmith);
        elliottSmith.addAlbum(album10_elliottSmith);
    }
}