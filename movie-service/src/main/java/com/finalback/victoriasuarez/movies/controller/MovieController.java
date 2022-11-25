package com.finalback.victoriasuarez.movies.controller;

import com.finalback.victoriasuarez.movies.model.Movie;
import com.finalback.victoriasuarez.movies.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService service;

	public MovieController(MovieService service) {
		this.service = service;
	}

	@GetMapping("/{genre}")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
		return ResponseEntity.ok(service.findByGenre(genre));
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<List<Movie>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@PostMapping("/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
		return ResponseEntity.ok().body(service.save(movie));
	}

}
