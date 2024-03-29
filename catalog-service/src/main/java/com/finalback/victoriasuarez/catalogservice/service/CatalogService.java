package com.finalback.victoriasuarez.catalogservice.service;

import com.finalback.victoriasuarez.catalogservice.client.MovieClient;
import com.finalback.victoriasuarez.catalogservice.client.SerieClient;
import com.finalback.victoriasuarez.catalogservice.model.Catalog;
import com.finalback.victoriasuarez.catalogservice.model.MovieDto;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
import com.finalback.victoriasuarez.catalogservice.repository.MovieRepository;
import com.finalback.victoriasuarez.catalogservice.repository.SerieRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CatalogService {

    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;
    private final MovieClient movieClient;
    private final SerieClient serieClient;

    public CatalogService(MovieRepository movieRepository, SerieRepository serieRepository, MovieClient movieClient, SerieClient serieClient) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
        this.movieClient = movieClient;
        this.serieClient = serieClient;
    }

    public Catalog getCatalogByGenreOnline(String genre) {
        Catalog response = new Catalog();
        findAllMoviesByGenre(genre, response);
        findAllSeriesByGenre(genre,response);
        return response;
    }

    // Esquema de resiliencia -> Se eligió colocar ambos microservicios en el esquema ya que son los dos igual de importantes.
    // El sistema central tiene como fin consumir los dos microservicios.
    // Para ambos casos, eligió para el Circuit Breaker:
    //   - Que se active mediante un contador de tiempo.
    //   - Con una tolerancia de 10 segundos, si no pasa a estado OPEN.
    //   - Con un porcentaje de llamadas fallidas de un 50%.
    //   - Con 3 llamadas de tolerancia en HALF-OPEN.
    //   - Con 50 segundos de espera en el estado OPEN para pasar a HALF-OPEN.

    // Para ambos casos, eligió para el Retry:
    //   - 3 reintentos como máximo para tomar como fallida la solicitud enviada
    //   - 10 segundos para realizar un nuevo reintento
    //   - Lista que desencadenan los reintentos

    // - Se definió que, en caso de fallback, se invoca el método Offline, el cuál posee los datos de los microservicios almacenados
    // en su propia base de datos.

    @Retry(name = "retryMovies")
    @CircuitBreaker(name = "MovieClient", fallbackMethod = "findAllMoviesFallBack")
    private void findAllMoviesByGenre(String genre, Catalog response) {
        List<MovieDto> save = movieClient.getByGenreMovie(genre);
        response.setMovies(save.stream().map(movie -> {
            MovieDto movieResponse = new MovieDto();
            BeanUtils.copyProperties(movie, movieResponse);
            return movieResponse;
        }).collect(toList()));
    }

    public void findAllMoviesFallBack(String genre, Catalog response, Throwable t) {
        List<MovieDto> saveMovie = movieRepository.getMovieByGenre(genre);
        response.setMovies(saveMovie.stream().map(movies -> {
            MovieDto movieRsp = new MovieDto();
            BeanUtils.copyProperties(movies, movieRsp);
            return movieRsp;
        }).collect(toList()));
    }

    @Retry(name = "retrySeries")
    @CircuitBreaker(name = "SerieClient", fallbackMethod = "findAllSeriesFallBack")
    private void findAllSeriesByGenre(String genre, Catalog response) {
        List<SerieDto> save = serieClient.getByGenreSerie(genre);
        response.setSeries(save.stream().map(serie -> {
            SerieDto serieResponse = new SerieDto();
            BeanUtils.copyProperties(serie, serieResponse);
            return serieResponse;
        }).collect(toList()));
    }

    public void findAllSeriesFallBack(String genre, Catalog response, Throwable t) {
        List<SerieDto> saveSerie = serieRepository.getSerieByGenre(genre);
        response.setSeries(saveSerie.stream().map(series -> {
            SerieDto serieRsp = new SerieDto();
            BeanUtils.copyProperties(series, serieRsp);
            return serieRsp;
        }).collect(toList()));
    }

    public Catalog getCatalogByGenreOffline(String genre) {
        Catalog response = new Catalog();
        List<MovieDto> saveMovie = movieRepository.getMovieByGenre(genre);
        response.setMovies(saveMovie.stream().map(movies -> {
            MovieDto movieRsp = new MovieDto();
            BeanUtils.copyProperties(movies, movieRsp);
            return movieRsp;
        }).collect(toList()));

        List<SerieDto> saveSerie = serieRepository.getSerieByGenre(genre);
        response.setSeries(saveSerie.stream().map(series -> {
            SerieDto serieRsp = new SerieDto();
            BeanUtils.copyProperties(series, serieRsp);
            return serieRsp;
        }).collect(toList()));
        return response;
    }
}
