package com.finalback.victoriasuarez.catalogservice.event;

import com.finalback.victoriasuarez.catalogservice.configuration.RabbitMQConfig;
import com.finalback.victoriasuarez.catalogservice.model.MovieDto;
import com.finalback.victoriasuarez.catalogservice.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class NewMovieConsumer {

    private final MovieRepository repository;
    private static final Logger log = LoggerFactory.getLogger(NewMovieConsumer.class);

    public NewMovieConsumer(MovieRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE)
    public void execute(MovieDto data) {
        log.info("new Movie");
        repository.deleteById(data.getId());
        repository.save(data);
    }

}
