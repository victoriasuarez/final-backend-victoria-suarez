package com.finalback.victoriasuarez.series.repository;

import com.finalback.victoriasuarez.series.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends MongoRepository<Serie, Long> {
    List<Serie> findByGenre(String genre);
}
