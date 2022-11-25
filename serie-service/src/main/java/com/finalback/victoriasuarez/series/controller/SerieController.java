package com.finalback.victoriasuarez.series.controller;

import com.finalback.victoriasuarez.series.model.Serie;
import com.finalback.victoriasuarez.series.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService service;

    public SerieController(SerieService service) {
        this.service = service;
    }

    @GetMapping("/{genre}")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre){
        return ResponseEntity.ok(service.findByGenre(genre));
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<Serie>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<Serie> saveSerie (@RequestBody Serie serie) {
        return ResponseEntity.ok().body(service.save(serie));
    }
}
