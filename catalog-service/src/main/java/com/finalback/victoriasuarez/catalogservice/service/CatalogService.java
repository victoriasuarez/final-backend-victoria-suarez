package com.finalback.victoriasuarez.catalogservice.service;

import com.finalback.victoriasuarez.catalogservice.client.MovieClient;
import com.finalback.victoriasuarez.catalogservice.client.SerieClient;
import com.finalback.victoriasuarez.catalogservice.model.Catalog;
import com.finalback.victoriasuarez.catalogservice.model.MovieDto;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
import com.finalback.victoriasuarez.catalogservice.repository.MovieRepository;
import com.finalback.victoriasuarez.catalogservice.repository.SerieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {

//    private final CatalogRepository repository;
//
//    public CatalogService(CatalogRepository repository) {
//        this.repository = repository;
//    }
//
//    public Catalog save(Catalog catalogs) {
//        return repository.save(catalogs);
//    }

    // Lógica nueva
    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final SerieRepository serieRepository;
    @Autowired
    private final MovieClient movieClient;
    @Autowired
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

    //Lógica del profesor


    // Corregir la lógica de los FindAll
    private void findAllMoviesByGenre(String genre, Catalog response) {
        List<MovieDto> save = movieClient.getByGenreMovie(genre).stream().collect(Collectors.toList());
        response.setMovies(save.stream().map(movie -> {
            Catalog.Movies movieResponse = new Catalog.Movies();
            BeanUtils.copyProperties(movie, movieResponse);
            return movieResponse;
        }).collect(Collectors.toList()));
    }

    private void findAllSeriesByGenre(String genre, Catalog response) {
        List<SerieDto> save = serieClient.getByGenreSerie(genre).stream().collect(Collectors.toList());
        response.setSeries(save.stream().map(serie -> {
            Catalog.Series serieResponse = new Catalog.Series();
            BeanUtils.copyProperties(serie, serieResponse);
            return serieResponse;
        }).collect(Collectors.toList()));
    }

    public Catalog getCatalogByGenreOffline(String genre) {
        Catalog response = new Catalog();
        List<MovieDto> saveMovie = movieRepository.getMovieByGenre(genre);
        response.setMovies(saveMovie.stream().map(movies -> {
            Catalog.Movies movieRsp = new Catalog.Movies();
            BeanUtils.copyProperties(movies, movieRsp);
            return movieRsp;
        }).collect(Collectors.toList()));

        List<SerieDto> saveSerie = serieRepository.getSerieByGenre(genre);
        response.setSeries(saveSerie.stream().map(series -> {
            Catalog.Series serieRsp = new Catalog.Series();
            BeanUtils.copyProperties(series, serieRsp);
            return serieRsp;
        }).collect(Collectors.toList()));
        return response;
    }
}
