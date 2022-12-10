package com.finalback.victoriasuarez.movies.event;

import com.finalback.victoriasuarez.movies.config.RabbitMQConfig;
import com.finalback.victoriasuarez.movies.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class MetricMovieCatalogProducer {

//    public static final Logger log = LoggerFactory.getLogger(MetricMovieCatalogProducer.class);

    private final RabbitTemplate rabbitTemplate;


    public MetricMovieCatalogProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void execute(Movie newMovie) {
        MetricMovieCatalogProducer.Data data = new MetricMovieCatalogProducer.Data();
        BeanUtils.copyProperties(newMovie, data.getMovies());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_MOVIE, data);
    }

//    public void sendMessage(MetricMovieData dataMovie) {
//        log.info("Sending message desde movie...");
//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_MOVIE, dataMovie);
//    }

    // Corregir
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data implements Serializable {
        private Data.MetricMovieData movies = new MetricMovieData();
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class MetricMovieData {
            private Long id;
            private String name;
            private String genre;
            private String urlStream;
        }
    }

}
