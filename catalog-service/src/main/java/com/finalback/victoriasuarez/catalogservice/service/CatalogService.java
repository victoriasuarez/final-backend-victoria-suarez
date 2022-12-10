package com.finalback.victoriasuarez.catalogservice.service;

import com.finalback.victoriasuarez.catalogservice.client.MovieClient;
import com.finalback.victoriasuarez.catalogservice.client.SerieClient;
import com.finalback.victoriasuarez.catalogservice.model.Catalog;
import com.finalback.victoriasuarez.catalogservice.model.MovieDto;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
import com.finalback.victoriasuarez.catalogservice.repository.MovieRepository;
import com.finalback.victoriasuarez.catalogservice.repository.SerieRepository;
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

    private void findAllMoviesByGenre(String genre, Catalog response) {
        List<MovieDto> save = movieClient.getAll().stream().filter(movie -> movie.getGenre().equals(genre)).toList();
        response.setMovies(save.stream().map(movie -> {
            MovieDto movieResponse = new MovieDto();
            BeanUtils.copyProperties(movie, movieResponse);
            movieRepository.save(movieResponse);
            return movieResponse;
        }).collect(toList()));
    }

    private void findAllSeriesByGenre(String genre, Catalog response) {
        List<SerieDto> save = serieClient.getAll().stream().filter(serie -> serie.getGenre().equals(genre)).toList();
        response.setSeries(save.stream().map(serie -> {
            SerieDto serieResponse = new SerieDto();
            BeanUtils.copyProperties(serie, serieResponse);
            serieRepository.save(serieResponse);
            return serieResponse;
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
