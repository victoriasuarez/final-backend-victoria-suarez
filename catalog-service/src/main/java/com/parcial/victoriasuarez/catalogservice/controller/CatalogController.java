package com.parcial.victoriasuarez.catalogservice.controller;

import com.parcial.victoriasuarez.catalogservice.client.MovieClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final MovieClient service;

    public CatalogController(MovieClient service) {
        this.service = service;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<MovieClient.MovieDto>> getGenre(@PathVariable String genre) {
        return ResponseEntity.ok(service.getByGenre(genre));
    }

}
