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

	public void save(Movie movie) {
		repository.save(movie);
		metricMovieCatalogProducer.execute(movie);
	}

	public List<Movie> getAll() {
		return repository.findAll();
	}

	// NO SÉ
//	public String getMetricCatalog(Long id) {
//		MetricMovieCatalogProducer.MetricMovieData movie = new MetricMovieCatalogProducer.MetricMovieData();
//		String idString = id.toString();
//		metricMovieCatalogProducer.sendMessage(new MetricMovieCatalogProducer.MetricMovieData(movie.getId(),movie.getName(),movie.getGenre(),movie.getUrlStream()));
//		return idString;
//	}

}
