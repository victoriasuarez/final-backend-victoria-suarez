package com.finalback.victoriasuarez.series.controller;

import com.finalback.victoriasuarez.series.model.Serie;
import com.finalback.victoriasuarez.series.service.SerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService service;

    public static final Logger log = LoggerFactory.getLogger(SerieController.class);
    public SerieController(SerieService service) {
        this.service = service;
    }

    @GetMapping("/{genre}")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre){
        log.info("Loading series by genre...");
        return ResponseEntity.ok(service.findByGenre(genre));
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<Serie>> getAll() {
        log.info("Loading all series...");
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> saveSerie (@RequestBody Serie serie) {
        log.info("Saving series...");
        service.save(serie);
        return ResponseEntity.ok().body("Serie created");
    }
}
