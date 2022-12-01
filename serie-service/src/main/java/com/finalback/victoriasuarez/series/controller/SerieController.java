package com.finalback.victoriasuarez.series.controller;

import com.finalback.victoriasuarez.series.event.MetricSerieCatalogProducer;
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
import java.util.Map;

import static com.finalback.victoriasuarez.series.config.RabbitMQConfig.EXCHANGE_NAME;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService service;

    public static final Logger log = LoggerFactory.getLogger(SerieController.class);
    public SerieController(SerieService service) {
        this.service = service;
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{genre}")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre){
        log.info("Loading series by genre...");
        restTemplate.exchange("http://localhost:8080/series/" + genre, HttpMethod.GET, new HttpEntity<>(genre), String.class);
        return ResponseEntity.ok(service.findByGenre(genre));
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<Serie>> getAll() {
        log.info("Loading all series...");
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/metricCatalog/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<Map<String, String>> getMetrictsCatalog(@PathVariable Long id) {
        log.info("Loading the metrics id...");
        restTemplate.exchange("http://localhost:8080/series/metricCatalog/" + id, HttpMethod.GET, new HttpEntity<>(id), String.class);
        return ResponseEntity.ok(Map.of("operationId", service.getMetricCatalog(id)));
    }

    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<Serie> saveSerie (@RequestBody Serie serie) {
        log.info("Saving series...");
        return ResponseEntity.ok().body(service.save(serie));
    }
}
