package com.finalback.victoriasuarez.catalogservice.repository;

import com.finalback.victoriasuarez.catalogservice.client.SerieClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends MongoRepository<SerieClient.SerieDto, Long> {
}
