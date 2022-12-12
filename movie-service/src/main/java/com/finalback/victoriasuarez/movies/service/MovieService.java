package com.finalback.victoriasuarez.movies.service;

import com.finalback.victoriasuarez.movies.event.NewMovieCatalogProducer;
import com.finalback.victoriasuarez.movies.model.Movie;
import com.finalback.victoriasuarez.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

	private final NewMovieCatalogProducer newMovieCatalogProducer;
	private final MovieRepository repository;

	public MovieService(NewMovieCatalogProducer newMovieCatalogProducer, MovieRepository movieRepository) {
		this.newMovieCatalogProducer = newMovieCatalogProducer;
		this.repository = movieRepository;
	}

	public List<Movie> findByGenre(String genre) {
		return repository.findByGenre(genre);
	}

	public void save(Movie movie) {
		repository.save(movie);
		newMovieCatalogProducer.execute(movie);
	}

	public List<Movie> getAll() {
		return repository.findAll();
	}


}
