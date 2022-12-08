package com.finalback.victoriasuarez.catalogservice.repository;

import com.finalback.victoriasuarez.catalogservice.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends MongoRepository<Catalog.Series, Long> {

    List<Catalog.Series> getSerieByGenre(String genre);
}
