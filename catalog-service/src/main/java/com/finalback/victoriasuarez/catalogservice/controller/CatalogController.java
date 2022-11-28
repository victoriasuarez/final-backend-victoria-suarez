package com.finalback.victoriasuarez.catalogservice.controller;

import com.finalback.victoriasuarez.catalogservice.client.MovieClient;
import com.finalback.victoriasuarez.catalogservice.client.SerieClient;
import com.finalback.victoriasuarez.catalogservice.model.Catalog;
import com.finalback.victoriasuarez.catalogservice.model.MovieDto;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
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


    @GetMapping("/{genre}")
    public Catalog getByGenre(@PathVariable String genre) {
        List<MovieDto> movies = serviceMovie.getByGenreMovie(genre);
        List<SerieDto> series = serviceSerie.getByGenreSerie(genre);
        return new Catalog(movies, series);
    }
}
