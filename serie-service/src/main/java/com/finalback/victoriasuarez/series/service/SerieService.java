package com.finalback.victoriasuarez.series.service;

import com.finalback.victoriasuarez.series.model.Serie;
import com.finalback.victoriasuarez.series.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repository;


    public SerieService(SerieRepository repository) {
        this.repository = repository;
    }

    public List<Serie> findByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public Serie save(Serie serie) {
        return repository.save(serie);
    }

    public List<Serie> getAll() {
        return repository.findAll();
    }
}
