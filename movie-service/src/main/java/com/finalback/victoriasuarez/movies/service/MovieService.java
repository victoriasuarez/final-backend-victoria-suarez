package com.finalback.victoriasuarez.movies.service;

import com.finalback.victoriasuarez.movies.event.MetricMovieCatalogProducer;
import com.finalback.victoriasuarez.movies.model.Movie;
import com.finalback.victoriasuarez.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

	private final MetricMovieCatalogProducer metricMovieCatalogProducer;
	private final MovieRepository repository;

	public MovieService(MetricMovieCatalogProducer metricMovieCatalogProducer, MovieRepository movieRepository) {
		this.metricMovieCatalogProducer = metricMovieCatalogProducer;
		this.repository = movieRepository;
	}

	public List<Movie> findByGenre(String genre) {
		return repository.findByGenre(genre);
	}

	public Movie save(Movie movie) {
		Movie save = repository.save(movie);
		metricMovieCatalogProducer.sendMesagge(new MetricMovieCatalogProducer.MetricMovieCatalogData(movie.getId(), "Movie created successfully."));
		return save;
	}

	public List<Movie> getAll() {
		return repository.findAll();
	}

	public String getMetricCatalog(Long id) {
		String operationId = UUID.randomUUID().toString();
		metricMovieCatalogProducer.sendMesagge(new MetricMovieCatalogProducer.MetricMovieCatalogData(id, operationId));
		return operationId;
	}

}
