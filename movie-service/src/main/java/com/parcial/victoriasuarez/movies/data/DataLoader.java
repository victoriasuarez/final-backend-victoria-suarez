package com.parcial.victoriasuarez.movies.data;

import com.parcial.victoriasuarez.movies.model.Movie;
import com.parcial.victoriasuarez.movies.repository.MovieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

	private MovieRepository repository;

	public DataLoader (MovieRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run (ApplicationArguments args) throws Exception {
		repository.save(new Movie(1L, "101 Dalmatians", "Adventure", "www.disneyplus.com"));
		repository.save(new Movie(2L, "Aladdin", "Adventure", "www.disneyplus.com"));
		repository.save(new Movie(3L, "Alice in wonderland", "Adventure", "www.disneyplus.com"));
		repository.save(new Movie(4L, "Spider Man 2", "Action", "www.disneyplus.com"));
		repository.save(new Movie(5L, "Deadpool", "Comedy", "www.disneyplus.com"));
		repository.save(new Movie(6L, "Dr. Dolittle", "Comedy", "www.disneyplus.com"));
		repository.save(new Movie(7L, "Tinker bell", "Animation", "www.disneyplus.com"));
		repository.save(new Movie(8L, "Bambi", "Animation", "www.disneyplus.com"));
		repository.save(new Movie(9L, "Winnie Pooh", "Animation", "www.disneyplus.com"));
		repository.save(new Movie(10L, "High school musical", "Romance", "www.disneyplus.com"));
		repository.save(new Movie(11L, "Black panther", "Action", "www.disneyplus.com"));
		repository.save(new Movie(12L, "Captain america", "Action", "www.disneyplus.com"));
	}
}
