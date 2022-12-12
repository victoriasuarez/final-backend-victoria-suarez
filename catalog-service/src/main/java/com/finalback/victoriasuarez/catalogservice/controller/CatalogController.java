package com.finalback.victoriasuarez.catalogservice.controller;

import com.finalback.victoriasuarez.catalogservice.model.Catalog;
import com.finalback.victoriasuarez.catalogservice.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final CatalogService service;

    public static final Logger log = LoggerFactory.getLogger(CatalogController.class);

    public CatalogController(CatalogService service) {
        this.service = service;
    }

    @GetMapping("/online/{genre}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Catalog> getCatalogByGenreOnline(@PathVariable String genre) {
        return ResponseEntity.ok(service.getCatalogByGenreOnline(genre));
    }

    @GetMapping("/offline/{genre}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Catalog> getCatalogByGenreOffline(@PathVariable String genre) {
        return ResponseEntity.ok(service.getCatalogByGenreOffline(genre));
    }

}
