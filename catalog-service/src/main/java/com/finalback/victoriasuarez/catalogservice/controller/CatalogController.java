package com.finalback.victoriasuarez.catalogservice.controller;

import com.finalback.victoriasuarez.catalogservice.client.MovieClient;
import com.finalback.victoriasuarez.catalogservice.client.SerieClient;
import com.finalback.victoriasuarez.catalogservice.model.Catalog;
import com.finalback.victoriasuarez.catalogservice.model.MovieDto;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
import com.finalback.victoriasuarez.catalogservice.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final MovieClient serviceMovie;

    private final SerieClient serviceSerie;

    private final CatalogService service;

    public static final Logger log = LoggerFactory.getLogger(CatalogController.class);

    public CatalogController(MovieClient serviceMovie, SerieClient serviceSerie, CatalogService service) {
        this.serviceMovie = serviceMovie;
        this.serviceSerie = serviceSerie;
        this.service = service;
    }

    @GetMapping("/{genre}")
    @ResponseStatus(code = HttpStatus.OK)
    public Catalog getByGenre(@PathVariable String genre) {
        log.info("Loading movies and series by genre...");
        List<MovieDto> movies = serviceMovie.getByGenreMovie(genre);
        List<SerieDto> series = serviceSerie.getByGenreSerie(genre);
        return service.save(new Catalog(movies,series));
    }
}
