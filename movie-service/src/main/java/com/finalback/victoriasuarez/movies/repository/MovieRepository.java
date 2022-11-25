package com.finalback.victoriasuarez.movies.repository;

import com.finalback.victoriasuarez.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByGenre(String genre);

}
