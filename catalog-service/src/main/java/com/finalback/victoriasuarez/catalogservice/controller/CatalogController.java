package com.finalback.victoriasuarez.catalogservice.controller;

import com.finalback.victoriasuarez.catalogservice.client.MovieClient;
import com.finalback.victoriasuarez.catalogservice.client.SerieClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final MovieClient serviceMovie;

    private final SerieClient serviceSerie;

    public CatalogController(MovieClient service, SerieClient serviceSerie) {
        this.serviceMovie = service;
        this.serviceSerie = serviceSerie;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class Genre {
        List<MovieClient.MovieDto> movies;
        List<SerieClient.SerieDto> series;
    }

    @GetMapping("/{genre}")
    public Genre getByGenre(@PathVariable String genre) {
        List<MovieClient.MovieDto> movies = serviceMovie.getByGenreMovie(genre);
        List<SerieClient.SerieDto> series = serviceSerie.getByGenreSerie(genre);
        return new Genre(movies, series);
    }
}
