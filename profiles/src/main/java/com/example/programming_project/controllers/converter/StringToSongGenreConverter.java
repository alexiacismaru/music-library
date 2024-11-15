package com.example.programming_project.controllers.converter;

import com.example.programming_project.domain.SongGenres;
import org.springframework.core.convert.converter.Converter;

public class StringToSongGenreConverter implements Converter<String, SongGenres> {
    @Override
    public SongGenres convert(String source) {
        if(source.toUpperCase().startsWith("ROCK")) return SongGenres.rock;
        if(source.toUpperCase().startsWith("ALTERNATIVE")) return SongGenres.alternative;
        if(source.toUpperCase().startsWith("RAP")) return SongGenres.rap;
        if(source.toUpperCase().startsWith("POP")) return SongGenres.pop;
        if(source.toUpperCase().startsWith("DISCO")) return SongGenres.disco;
        if(source.toUpperCase().startsWith("PUNK")) return SongGenres.punk;
        if(source.toUpperCase().startsWith("METAL")) return SongGenres.metal;
        return SongGenres.unknown;
    }
}
