package com.finalback.victoriasuarez.movies.controller;

import com.finalback.victoriasuarez.movies.model.Movie;
import com.finalback.victoriasuarez.movies.service.MovieService;
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
@RequestMapping("/movies")
public class MovieController {

	private final MovieService service;

	public static final Logger log = LoggerFactory.getLogger(MovieController.class);

	public MovieController(MovieService service) {
		this.service = service;
	}

	@GetMapping("/{genre}")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
		log.info("Loading movies by genre...");
		return ResponseEntity.ok(service.findByGenre(genre));
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<List<Movie>> getAll() {
		log.info("Loading all movies...");
		return ResponseEntity.ok(service.getAll());
	}

	@PostMapping("/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<String> saveMovie(@RequestBody Movie movie) {
		log.info("Saving movie...");
		service.save(movie);
		return ResponseEntity.ok().body("Movie created");
	}

}
