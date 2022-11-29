package com.finalback.victoriasuarez.series;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableRabbit
@AutoConfiguration
public class SerieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SerieServiceApplication.class, args);
	}

}
