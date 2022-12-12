package com.finalback.victoriasuarez.movies.event;

import com.finalback.victoriasuarez.movies.config.RabbitMQConfig;
import com.finalback.victoriasuarez.movies.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NewMovieCatalogProducer {

    private final RabbitTemplate rabbitTemplate;


    public NewMovieCatalogProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void execute(Movie newMovie) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_MOVIE, newMovie);
    }

}
