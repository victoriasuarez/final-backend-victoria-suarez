package com.finalback.victoriasuarez.series.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalback.victoriasuarez.series.event.MetricSerieCatalogProducer;
import com.finalback.victoriasuarez.series.model.ChapterDto;
import com.finalback.victoriasuarez.series.model.SeasonDto;
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

    public void save(Serie serie) {
        repository.save(serie);
        metricSerieCatalogProducer.execute(serie);
    }

    public List<Serie> getAll() {
        return repository.findAll();
    }

    // NO SÉ

//    public String getMetricCatalog(Long id) {
//        MetricSerieCatalogProducer.MetricSerieData serie = new MetricSerieCatalogProducer.MetricSerieData();
//        MetricSerieCatalogProducer.MetricSerieData.SeasonDto season = new MetricSerieCatalogProducer.MetricSerieData.SeasonDto();
//        MetricSerieCatalogProducer.MetricSerieData.ChaptersDto chapters = new MetricSerieCatalogProducer.MetricSerieData.ChaptersDto();
//        String idString = id.toString();
//        metricSerieCatalogProducer.sendMessage(new MetricSerieCatalogProducer.MetricSerieData(serie.getId(), serie.getName(), serie.getGenre(), new MetricSerieCatalogProducer.MetricSerieData.SeasonDto(season.getId(), season.getSeasonNumber(), new MetricSerieCatalogProducer.MetricSerieData.ChaptersDto(chapters.getId(),chapters.getName(),chapters.getNumber(), chapters.getUrlStream()))));
//        return idString;
//    }

}
