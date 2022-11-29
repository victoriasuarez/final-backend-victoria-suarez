package com.finalback.victoriasuarez.movies;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EnableRabbit
@AutoConfiguration
public class MovieServiceApplication {

	public static void main (String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}


}
