package com.finalback.victoriasuarez.catalogservice.repository;

import com.finalback.victoriasuarez.catalogservice.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Catalog.Movies, Long> {

    List<Catalog.Movies> getMovieByGenre(String genre);
}
