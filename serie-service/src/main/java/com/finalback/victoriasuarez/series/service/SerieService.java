package com.finalback.victoriasuarez.series.service;

import com.finalback.victoriasuarez.series.event.NewSerieCatalogProducer;
import com.finalback.victoriasuarez.series.model.Serie;
import com.finalback.victoriasuarez.series.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final NewSerieCatalogProducer newSerieCatalogProducer;

    private final SerieRepository repository;

    public SerieService(NewSerieCatalogProducer newSerieCatalogProducer, SerieRepository repository) {
        this.newSerieCatalogProducer = newSerieCatalogProducer;
        this.repository = repository;
    }

    public List<Serie> findByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public void save(Serie serie) {
        repository.save(serie);
        newSerieCatalogProducer.execute(serie);
    }

    public List<Serie> getAll() {
        return repository.findAll();
    }


}
