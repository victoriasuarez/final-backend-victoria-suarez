package com.parcial.victoriasuarez.movies.service;

import com.parcial.victoriasuarez.movies.model.Movie;
import com.parcial.victoriasuarez.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

	private final MovieRepository repository;

	public MovieService(MovieRepository movieRepository) {
		this.repository = movieRepository;
	}

	public List<Movie> findByGenre(String genre) {
		return repository.findByGenre(genre);
	}

	public Movie save(Movie movie) {
		return repository.save(movie);
	}

	public List<Movie> getAll() {
		return repository.findAll();
	}

}
