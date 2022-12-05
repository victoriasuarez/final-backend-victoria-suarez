package com.finalback.victoriasuarez.movies.event;

import com.finalback.victoriasuarez.movies.config.RabbitMQConfig;
import com.finalback.victoriasuarez.movies.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MetricMovieCatalogProducer {

    public static final Logger log = LoggerFactory.getLogger(MetricMovieCatalogProducer.class);

    private final RabbitTemplate rabbitTemplate;


    public MetricMovieCatalogProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void execute(Movie newMovie) {
        log.info("Sending message desde movie...");
        MetricMovieCatalogProducer.MetricMovieCatalogData data = new MetricMovieCatalogProducer.MetricMovieCatalogData();
        BeanUtils.copyProperties(newMovie, data.getClass());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_MOVIE, data);
    }

    // Corregir
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MetricMovieCatalogData {
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
    }


}
