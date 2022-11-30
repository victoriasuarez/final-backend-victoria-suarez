package com.finalback.victoriasuarez.series.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.finalback.victoriasuarez.series.event.MetricSerieCatalogProducer;
import com.finalback.victoriasuarez.series.model.Serie;
import com.finalback.victoriasuarez.series.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SerieService {

    private final MetricSerieCatalogProducer metricSerieCatalogProducer;

    private final SerieRepository repository;

    public SerieService(MetricSerieCatalogProducer metricSerieCatalogProducer, SerieRepository repository) {
        this.metricSerieCatalogProducer = metricSerieCatalogProducer;
        this.repository = repository;
    }

    public List<Serie> findByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public Serie save(Serie serie) {
        Serie save = repository.save(serie);
        metricSerieCatalogProducer.sendMesagge(new MetricSerieCatalogProducer.MetricSerieCatalogData(serie.getId(), "Series created successfully."));
        return save;
    }

    public List<Serie> getAll() {
        return repository.findAll();
    }

    public String getMetricCatalog(Long id) {
        String operationId = UUID.randomUUID().toString();
        metricSerieCatalogProducer.sendMesagge(new MetricSerieCatalogProducer.MetricSerieCatalogData(id, operationId));
        return operationId;
    }

}
