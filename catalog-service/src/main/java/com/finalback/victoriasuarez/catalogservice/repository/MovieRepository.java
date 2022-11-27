package com.finalback.victoriasuarez.catalogservice.repository;

import com.finalback.victoriasuarez.catalogservice.client.MovieClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<MovieClient.MovieDto, Long> {
}
